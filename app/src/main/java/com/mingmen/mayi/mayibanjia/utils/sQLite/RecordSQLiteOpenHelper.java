package com.mingmen.mayi.mayibanjia.utils.sQLite;

/**
 * Created by 刘佳 on 2017/11/20.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 搜索记录帮助类
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "temp.db";
    private final static int DB_VERSION = 1;

    public RecordSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dianpu = "CREATE TABLE IF NOT EXISTS dianpu (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(dianpu);
        String shangpin = "CREATE TABLE IF NOT EXISTS shangpin (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(shangpin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
