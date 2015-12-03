package jp.ac.kcg.projectexercises.main

/**
 * Application
 */
class Application() : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        Global.instance.applicationContext = this
    }
}
