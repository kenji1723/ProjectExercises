package jp.ac.kcg.projectexercises.activites

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.Window
import android.widget.Toast


import java.util.ArrayList

/**
 * Activityが継承すべきクラス
 */
open class ApplicationActivity : DialogActivity() {
    private val onFinishCallbacks = ArrayList<() -> Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(toString(), "onCreate()")
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        super.onCreate(savedInstanceState)
        supportActionBar.setDisplayHomeAsUpEnabled(true)
    }

    override final fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onResume() {
        Log.v(toString(), "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.v(toString(), "onPause()")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.v(toString(), "onSavedInstanceState()")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.v(toString(), "onSavedInstanceState()")
        super.onRestoreInstanceState(savedInstanceState)
    }

    /**
     * 指定したActivityに遷移します

     * @param activityClass 遷移するActivityのクラス
     *
     * @param finish        現在のActivityを終了するか否かtrue:終了するfalse:終了しない
     */
    fun startActivity(activityClass: Class<*>, finish: Boolean) {
        val intent = Intent(this, activityClass)
        super.startActivity(intent)
        if (finish) {
            finish()
        }
    }

    /**
     * トーストを表示
     * @param text
     */
    fun sendToast(text: String) {
        sendToast(text, Toast.LENGTH_LONG)
    }

    /**
     * トーストを表示
     * @param text
     * @param length 表示する長さ
     */

    fun sendToast(text: String, length: Int) {
        if (!isFinishing)
            runOnUiThread { Toast.makeText(this, text, length).show() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_HOME -> return moveTaskToBack(true)
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * Activityがfinishした際に呼ばれるコールバックを追加する
     *
     * @param finishCallback フィニッシュした際のCallback
     */
    fun addOnFinishCallback(finishCallback: () -> Unit) {
        onFinishCallbacks.add(finishCallback)
    }

    override fun finish() {
        onFinishCallbacks.forEach {
            it()
        }
        super.finish()
    }

    override fun onDestroy() {
        Log.v(toString(), "onDestroy()")
        super.onDestroy()
    }

}
