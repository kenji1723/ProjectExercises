package jp.ac.kcg.projectexercises.activites

import java.util.concurrent.Callable
import java.util.concurrent.Future

/**
 * ThreadPoolHolder
 */
interface ThreadPoolHolder {
    /**
     * ThreadPoolに処理を投げる
     */
    fun execute(command: Runnable)

    /**
     * ThreadPoolに処理を投げる

     * @param task task
     */
    fun <T> submit(task: Callable<T>): Future<T>

    /**
     * ThreadPoolに処理を投げる

     * @param task   task
     * *
     * @param result 返ってくるリザルト
     * *
     * @return Future
     */
    fun <T> submit(task: Runnable, result: T): Future<T>

}
