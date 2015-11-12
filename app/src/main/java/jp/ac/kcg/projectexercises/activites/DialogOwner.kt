package jp.ac.kcg.projectexercises.activites

import android.support.v7.app.AppCompatDialog

/**
 * DialogOwner
 */
interface DialogOwner {
    /**
     * ダイアログを表示する

     * @param dialog 表示するダイアログ
     */
    fun showDialog(dialog: AppCompatDialog)
}
