package com.johnmelodyme.foodie.Database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import androidx.annotation.NonNull
import com.johnmelodyme.foodie.Constant.ConstantValue
import retrofit2.http.DELETE

class DatabaseHelper(
    context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int
) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{

    var writeableDatabase: SQLiteDatabase = writableDatabase
    internal val readableDatabase: SQLiteDatabase = getReadableDatabase()

    override fun onCreate(db: SQLiteDatabase?)
    {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {

    }

    fun query(@NonNull sql: String)
    {
        writeableDatabase.execSQL(sql)
    }

    // * WARN DEPRECATED
    private fun insertData(@NonNull post: String, image: ByteArray)
    {
        val query: String = "INSERT INTO FOODIE VALUES (NULL, ?, ?, ?)"
        val statement: SQLiteStatement = writeableDatabase.compileStatement(query)
        statement.clearBindings()
        statement.bindString(1, post)
        statement.bindBlob(2, image)

        statement.executeInsert()
    }

    fun getData(@NonNull query: String): Cursor?
    {
        return readableDatabase.rawQuery(query, null, null)
    }

    companion object
    {
        const val DATABASE_VERSION = ConstantValue.DATABASE_VERSION
        const val DATABASE_NAME = ConstantValue.DATABASE_NAME
    }

}