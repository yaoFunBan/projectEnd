package com.example.kimhuang.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kimhuang on 21/4/2559.
 */
public class datahomony extends SQLiteOpenHelper {
    private static final String DbName = "DBFairy";
    private static final int DB_version = 1;
    public static final String TableName = "vocabulary";
    public static final String ColHomony = "homony";
    public static final String ColSemantic = "semantic";
    public static final String ColStatus = "Status";
    public static final String ColSound = "Sound";
    public static final String ColPicture = "pPicture";

    public datahomony(Context context) {
        super(context, DbName, null, DB_version);
    }

    //การ create, inset, delete ข้อมูลใน database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " ( " + ColHomony + " TEXT, " + ColSemantic + " TEXT, " + ColStatus + " TEXT," + ColSound + " TEXT );");

        String[] homony = {"สุริยัน", "มัจฉา", "ดนัย", "บุตรี", "ผกา", "รามสูร", "บรรพต", "ขัตติยะ", "กุญชร", "กระสินธิ์ ", "ปฐพี", "อมรินทร์", "อาชาไนย", "บุหลัน"
                , "พนาดร", "กุมาร", "พารา", "กัลยาณี", "สุราลัย", "ปทุม"};

        String[] semantic = {"พระอาทิตย์", "ปลา", "ลูกชาย", "ลูกสาว", "ดอกไม้", "ยักษ์", "ภูเขา", "กษัตริย์", "ช้าง", "น้ำ", "แผ่นดิน", "พระอินทร์", "ม้า", "พระจันทร์",
                "ป่า", "เด็ก", "เมือง", "ผู้หญิง", "สวรรค์", "ดอกบัว",};

        String[] status = {"correct", "correct", "correct", "uncorrect", "correct", "uncorrect", "correct", "correct", "correct", "uncorrect",
                "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "correct"};

        int[] sound = {R.raw.janta, R.raw.firsh, R.raw.yotsuwimon, R.raw.jantawee, R.raw.jantawee,
                R.raw.yuk, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};

        //เพิ่มข้อมูลลงตารางเรื่อยๆ
        ContentValues content = new ContentValues();
        for (int i = 0; i < homony.length; i++) {
            content.put(ColHomony, homony[i]);
            content.put(ColSemantic, semantic[i]);
            content.put(ColStatus, status[i]);
            content.put(ColSound, sound[i]);
            db.insert(TableName, null, content);
        }
    }

    //การ update ข้อมูลใหม่ให้ database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }


    public boolean updateContact(String homony, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ColStatus, status);
        return db.update(TableName, args, ColHomony + " = " + homony, null) > 0;
    }

//    public int updateContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//        // updating row
//        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//    }
}