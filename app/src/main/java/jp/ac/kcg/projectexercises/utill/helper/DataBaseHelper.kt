package jp.ac.kcg.projectexercises.utill.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource

/**
 * DataBaseHelper
 */
class DataBaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DataBaseHelper.DB_NAME, null, DataBaseHelper.DB_VERSION) {
    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {

    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "numeri.db"
    }
}