package jp.ac.kcg.projectexercises.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import jp.ac.kcg.projectexercises.R
import jp.ac.kcg.projectexercises.activites.ApplicationActivity
import jp.ac.kcg.projectexercises.twitter.client.ClientUsers
import jp.ac.kcg.projectexercises.twitter.client.ClientUsersManagerActivity

import kotlinx.android.synthetic.activity_main.*

class MainActivity : ApplicationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Global.instance.mainActivityContext = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ClientUsers.instance.size() == 0) {
            startActivity(ClientUsersManagerActivity::class.java, true)
        }
        //仮実装、
        ClientUsers.instance.loadUsers { users, success ->
            if (success) {
                runOnUiThread { users[0].stream.addOnStatusListener { sendToast(it.user.name + ":" + it.text) } }
            } else {
                sendToast("ユーザーの認証に失敗しました。")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        ClientUsers.instance.loadUsers { clientUsers, success ->
            if (clientUsers.isEmpty()) {
                startActivity(ClientUsersManagerActivity::class.java, true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_account -> startActivity(ClientUsersManagerActivity::class.java, false)
        }
        return super.onOptionsItemSelected(item)
    }
}
