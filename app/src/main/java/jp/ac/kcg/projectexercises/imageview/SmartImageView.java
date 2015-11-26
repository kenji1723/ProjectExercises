package jp.ac.kcg.projectexercises.imageview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.ac.kcg.projectexercises.R;
import jp.ac.kcg.projectexercises.activites.ApplicationActivity;
import jp.ac.kcg.projectexercises.activites.ThreadPoolHolder;
import jp.ac.kcg.projectexercises.imageview.cache.ImageDownloader;
import kotlin.Unit;

/**
 * ImageView
 */
public class SmartImageView extends ImageView {
    protected static final String PNG_EXTENSION = "[pP][nN][gG]";
    protected static final String JPEG_EXTENSION = "[jJ][pP][eE]?[gG]";
    private static final String URL = "^(https?|ftp)(://[-_.!~*'()a-zA-Z0-9;/?:@&=+\\$,%#]+)$";
    private OnLoadCompletedListener onLoadCompletedListener;
    private ImageDownloader.ImageData imageData = null;
    private String imageName = "";
    private String imageExtension = "";
    private String imageUrl = "";
    private boolean imageReleased = false;
    private static String savePath = "numeri";

    public SmartImageView(Context context) {
        super(context);
        checkContext(context);
    }

    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        checkContext(context);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        checkContext(context);
    }

    private void checkContext(Context context) {
        if (!(context instanceof ThreadPoolHolder)) {
            throw new IllegalStateException("与えられたContextがThreadPoolHolderを継承していない");
        }
    }

    /**
     * 画像を保存するパスをセットします。
     * デフォルトでは"numeri"です。
     *
     * @param savePath ExternalStorageDirectoryをルートとした相対パス
     */
    public static void setSavePath(String savePath) {
        SmartImageView.savePath = savePath;
    }

    @Override
    protected void onAttachedToWindow() {
        Context context = getContext();
        if (context instanceof ApplicationActivity)
            ((ApplicationActivity) context).addOnFinishCallback(() -> {
                releaseImage();
                return Unit.INSTANCE;
            });
        super.onAttachedToWindow();
    }

    /**
     * 画像のロードが終了した際のリスナをセットする
     *
     * @param listener 画像のロードが終了した際のリスナ
     */
    public final void setOnLoadCompletedListener(OnLoadCompletedListener listener) {
        onLoadCompletedListener = listener;
    }

    /**
     * 画像をurlからセットします。
     * <br>キャッシュはされません。
     *
     * @param url セットする画像のurl
     */
    public final void setImage(String url) {
        setImage(false, ProgressType.NONE, url);
    }

    /**
     * @param cache falseを指定するとキャッシュされません、またキャッシュから読み込むこともありません
     * @param type  ProgressType
     * @param url   ロードする画像のurl
     */
    public final void setImage(boolean cache, ProgressType type, String url) {
        if (url == null || url.equals("")) {
            imageUrl = "";
            setSaveImageFunctionEnabled(false, null);
            setImageDrawable(null);
            imageExtension = "";
            imageName = "";
            if (imageData != null) imageData.setQuantity(false);
            imageData = null;
            return;
        }
        if (!judgeUrl(url)) throw new IllegalArgumentException("urlでない文字列が渡されました。");
        ImageDownloader imageDownloader = new ImageDownloader((ThreadPoolHolder) getContext());
        imageName = Uri.parse(url).getLastPathSegment();
        imageUrl = url;
        imageExtension = "";
        char[] charArray = url.toCharArray();
        for (int length = charArray.length - 1; length >= 0; length--) {
            if (String.valueOf(charArray[length]).equals(".")) {
                break;
            }
            imageExtension = String.valueOf(charArray[length]) + imageExtension;
        }

        imageDownloader.setOnStartDownloadListener(key -> {
            if (key.equals(imageUrl)) {
                switch (type) {
                    case LOAD_ICON:
                        setImageDrawable(null);
                        break;
                    case LOAD_MEDIA:
                        setImageDrawable(null);
                        break;
                    case NONE:
                        break;
                }
            }
        }).loadImage(cache, url, (imageData, key) -> {
            if ((imageData != null && imageData.getImage() != null) && imageUrl.equals(key)) {
                if (this.imageData != null) this.imageData.setQuantity(false);
                this.imageData = imageData;
                imageData.setQuantity(true);
                if (!imageData.getImage().isRecycled()) this.setImageBitmap(imageData.getImage());
                if (onLoadCompletedListener != null) {
                    onLoadCompletedListener.onLoadCompleted(imageData.getImage());
                }
            }
        });
    }

    private boolean judgeUrl(String s) {
        return s.matches(URL);
    }


    /**
     * 長押しでの画像の保存の機能が有効か否かを切り替える
     *
     * @param enabled                         true : 有効 false : 無効
     * @param onOperatingSaveFunctionListener このメソッドにtrueを与えた状態で長押し時に発生するイベントのリスナー
     */
    public void setSaveImageFunctionEnabled(boolean enabled, OnOperatingSaveFunctionListener onOperatingSaveFunctionListener) {
        if (enabled) {
            setOnLongClickListener(v -> {
                if (imageData != null) {
                    Resources resources = getContext().getResources();
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setMessage(resources.getString(R.string.dialog_message_confirmation_save_image))
                            .setPositiveButton(resources.getString(R.string.yes), (dialog, which) -> {
                                ((ThreadPoolHolder) getContext()).execute(() -> {
                                    if (executeSaveImage()) {
                                        sendToast(imageName + "を保存しました");
                                    } else {
                                        sendToast("保存に失敗しました");
                                    }
                                });
                            })
                            .setNegativeButton(resources.getString(R.string.cancel), (dialog, which) -> {
                            })
                            .create();
                    if (onOperatingSaveFunctionListener != null)
                        onOperatingSaveFunctionListener.onOperate(alertDialog);
                    return true;
                }
                return false;
            });
        } else {
            setOnLongClickListener(v -> false);
        }
    }

    /**
     * 画像の保存を実行
     *
     * @return 成功したか否か true : 成功 , false : 失敗
     */
    private boolean executeSaveImage() {
        boolean success = false;
        FileOutputStream outputStream = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath()
                    + "/" + savePath);
            if ((file.exists() || file.mkdir()) && getImageData() != null) {
                String path = file.getAbsolutePath() + "/" + imageName;
                outputStream = new FileOutputStream(path);
                saveImage(outputStream, path);
                success = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }


    /**
     * @param outputStream OutputStream
     */
    protected void saveImage(FileOutputStream outputStream, String path) {
        ContentValues values = new ContentValues();
        ContentResolver resolver = getContext().getContentResolver();
        if (getImageExtension().matches(PNG_EXTENSION)) {
            getImageData().getImage().compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        } else if (getImageExtension().matches(JPEG_EXTENSION)) {
            getImageData().getImage().compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        } else {
            getImageData().getImage().compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        }
        values.put("data_", path);
        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    /**
     * ImageDataを取得
     *
     * @return 持っているImageData<br> nullを返す可能性があります。
     */
    protected final ImageDownloader.ImageData getImageData() {
        return imageData;
    }

    /**
     * 表示されている画像の拡張子を取得
     *
     * @return 拡張子
     */
    protected final String getImageExtension() {
        return imageExtension;
    }


    @Override
    protected void onDetachedFromWindow() {
        releaseImage();
        super.onDetachedFromWindow();
    }

    public final void sendToast(String text) {
        ((Activity) getContext()).runOnUiThread(() -> Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show());
    }

    private void releaseImage() {
        if (imageData != null && !imageReleased) {
            imageReleased = true;
            imageData.setQuantity(false);
            imageData.recycle();
        }
    }


    public enum ProgressType {
        LOAD_ICON,
        LOAD_MEDIA,
        NONE;

        ProgressType() {

        }
    }
}
