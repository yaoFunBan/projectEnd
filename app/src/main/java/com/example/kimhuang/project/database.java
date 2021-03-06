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
    public static final String TableName = "Racha";
    public static final String ColWord = "word";
    public static final String ColMean = "Mean";
    public static final String ColStatus = "Status";
    //type = 1 คือ คำราชาศัพท์ , 2 คือ คำพ้อง รูป พ้องเสียง , 3 คือ สำนวน
    public static final String ColSound = "Sound";

    public database(Context context) {
        super(context, DbName, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " ( " + ColWord + " TEXT PRIMARY KEY, " + ColMean + " TEXT, " + ColStatus + " TEXT ," + ColSound + " TEXT );");

        String[] word = {"พระชนนี ", "รองพระบาท ", "สังวาล ", "ชฏา", "พระสนับเพลา"
                , "พระอุทร", "พระนาสิก", "พระกรรณ", "ข้อพระบาท",
                "พระโอษฐ์", "พระมังสา", "พระเนตร", "พระชนก", "พระเชษฐา",
                "พระสัสสุระ", "พระสวามี", "ฉลองพระองค์", "พระสุณิสา", "พระเทวัน", "พระชามาดา"};

        String[] mean = {"แม่", "รองเท้า", "สร้อย", "มงกุฎ", "กางเกง",
                "ท้อง", "จมูก", "หู", "ข้อเท้า",
                "ปาก", "เนื้อ", "ผิวหน้า", "พ่อ", "พี่ชาย",
                "พ่อตา", "สามี", "เสื้อ", "ลูกสะใภ้", "พี่เขย", "ลูกเขย"};

        String[] status = {"uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect",
                "uncorrect", "uncorrects", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect"};

        int[] sound = {R.raw.janta, R.raw.janta, R.raw.soi, R.raw.jantawee, R.raw.kangkeng,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};

        ContentValues content = new ContentValues();
        for (int i = 0; i < word.length; i++) {
            content.put(ColWord, word[i]);
            content.put(ColMean, mean[i]);
            content.put(ColStatus, status[i]);
            content.put(ColSound, sound[i]);

            db.insert(TableName, null, content);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

}