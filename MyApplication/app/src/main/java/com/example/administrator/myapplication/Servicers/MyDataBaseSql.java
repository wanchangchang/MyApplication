package com.example.administrator.myapplication.Servicers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wanchangchang on 2016/12/1.
 */

public class MyDataBaseSql extends SQLiteOpenHelper {
    public static final String TEXT_BASE = "create table TextBase( "
            +"id integer primary key autoincrement,"
            +"data,"
            +"type,"
            +"weather text,"
            + "motion text,"
            +"content text)";

    public MyDataBaseSql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TEXT_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
