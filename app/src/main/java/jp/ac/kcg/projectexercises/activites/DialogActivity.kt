package jp.ac.kcg.projectexercises.activites

import android.support.v7.app.AppCompatDialog


/**
 * DialogActivity
 */
open class DialogActivity : ThreadPoolActivity(), DialogOwner {
    private var currentShowDialog: AppCompatDialog? = null

    override fun onPause() {
        hideDialog()
        super.onPause()
    }


    override fun onResume() {
        showDialog()
        super.onResume()
    }

    /**
     * ダイアログを表示する
     * @param dialog 表示されるダイアログ
     */
    override fun showDialog(dialog: AppCompatDialog) {
        if ((currentShowDialog != null && !currentShowDialog!!.isShowing) || currentShowDialog == null) {
            runOnUiThread { dialog.show() }
            currentShowDialog = dialog
        }
    }

    private fun hideDialog() {
        if (currentShowDialog != null) {
            if (currentShowDialog!!.isShowing) {
                currentShowDialog!!.hide()
            } else {
                currentShowDialog = null
            }
        }
    }

    private fun showDialog() {
        if (currentShowDialog != null) {
            currentShowDialog!!.show()
        }
    }

    override fun onDestroy() {
        if (currentShowDialog != null) {
            currentShowDialog!!.dismiss()
        }
        currentShowDialog = null
        super.onDestroy()
    }
}