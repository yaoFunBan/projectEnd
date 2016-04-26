package com.example.kimhuang.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Donnapa on 25-Apr-16.
 */
public class dataidioms extends SQLiteOpenHelper{
    private static final String Dbname = "DBFairy";
    private static final int DB_version = 1;
    public static final String TableName = "vocabulary";
    public static final String CoLWord = "word";
    public static final String CoLMeaning = "meaning";
    

    public dataidioms(Context context) {
        super(context, Dbname, null, DB_version);
    }

    //การ create,insert, delete ข้อมูลใน database
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
