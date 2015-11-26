package jp.ac.kcg.projectexercises.imageview;

import android.support.v7.app.AlertDialog;

/**
 * 画像保存機能実行時に使用されるリスナー
 */
public interface OnOperatingSaveFunctionListener {
    /**
     * 画像保存機能実行時に呼ばれるイベントハンドラ
     *
     * @param dialog 保存するか否かの選択を促すダイアログ
     */
    void onOperate(AlertDialog dialog);
}
