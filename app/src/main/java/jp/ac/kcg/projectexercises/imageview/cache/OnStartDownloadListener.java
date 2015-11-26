package jp.ac.kcg.projectexercises.imageview.cache;

/**
 * OnDownLoadStartListener
 */
public interface OnStartDownloadListener {
    /**
     * 画像のダウンロードがスタートした際のイベントハンドラ
     *
     * @param key ダウンロードを開始した画像のURL
     */
    void onDownLoadStart(String key);
}