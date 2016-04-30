package com.example.kimhuang.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by วัชรัตน์ on 21/12/2558.
 */
public class database extends SQLiteOpenHelper {
    private static final String DbName = "DBFairy";
    private static final int DB_version = 1;
    public static final String TableName = "vocabulary";
    public static final String ColWord = "word";
    public static final String ColMean = "Mean";
    public static final String ColStatus = "Status";
    //type = 1 คือ คำราชาศัพท์ , 2 คือ คำพ้อง รูป พ้องเสียง , 3 คือ สำนวน
    public static final String ColType = "Type";
    public static final String ColPicture = "pPicture";

    public database(Context context) {
        super(context, DbName, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " ( " + ColType + " TEXT, " + ColWord + " TEXT, " + ColMean + " TEXT, " + ColStatus + " TEXT);");

        String[] word = {"พระชนนี ", "รองพระบาท ", "สังวาล ", "ชฏา", "พระสนับเพลา"
                , "พระอุทร", "พระโมฬี", "พระนาสิก", "พระกรรณ", "ข้อพระบาท", "พระโอษฐ์", "พระมังสา", "น้ำพระเนตร", "พระชนก", "พระเชษฐา", "พระสัสสุระ", "พระสวามี", "พระชนนี", "พระสุณิสา", "พระเทวัน", "พระชามาดา"};

        String[] mean = {"แม่", "รองเท้า", "สร้อย", "มงกุฎ", "กางเกง", "ท้อง", "จุก", "จมูก", "หู", "ข้อเท้า"
                , "ปาก", "เนื้อ", "ผิวหน้า", "พ่อ", "พี่สาว", "พ่อตา", "สามี", "แม่", "ลูกสะใภ้", "พี่เขย", "ลูกเขย"};


        ContentValues content = new ContentValues();
        for (int i = 0; i < word.length; i++) {
            content.put(ColType, "1");
            content.put(ColWord, word[i]);
            content.put(ColMean, mean[i]);

            db.insert(TableName, null, content);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

}