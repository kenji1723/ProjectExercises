package jp.ac.kcg.projectexercises.activites

import android.support.v7.app.AppCompatActivity

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

/**
 * ThreadPoolActivity
 */
open class ThreadPoolActivity : AppCompatActivity(), ThreadPoolHolder {
    private var threadPool: ExecutorService? = null

    override fun onDestroy() {
        if (threadPool != null) {
            threadPool!!.shutdown()
        }
        super.onDestroy()
    }

    override fun execute(command: Runnable) {
        createThreadPool()
        if (!threadPool!!.isShutdown && !isFinishing)
            threadPool!!.execute(command)
    }

    override fun <T> submit(task: Callable<T>): Future<T> {
        createThreadPool()
        if (!threadPool!!.isShutdown && !isFinishing)
            return threadPool!!.submit(task)
        return FutureTask { null }
    }

    override fun <T> submit(task: Runnable, result: T): Future<T> {
        createThreadPool()
        if (!threadPool!!.isShutdown && !isFinishing)
            return threadPool!!.submit(task, result)
        return FutureTask { null }
    }

    private fun createThreadPool() {
        if (threadPool == null)
            threadPool = Executors.newCachedThreadPool()
    }

    override fun finish() {
        if (threadPool != null && !threadPool!!.isShutdown) {
            threadPool!!.shutdown()
        }
        super.finish()
    }
}