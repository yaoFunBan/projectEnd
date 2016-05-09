package com.example.kimhuang.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String ColType = "Type";
    public static final String ColPicture = "pPicture";

    public datahomony(Context context) {
        super(context, DbName, null, DB_version);
    }

    //การ create, inset, delete ข้อมูลใน database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " ( " + ColType + " TEXT, " + ColHomony + " TEXT, " + ColSemantic + " TEXT, " + ColStatus + " TEXT);");

        String[] homony = {"สุริยัน", "มัจฉา", "ดนัย", "บุตรี", "ผกา", "รามสูร", "บรรพต", "ขัตติยะ", "กุญชร", "กระสินธิ์ ","ปฐพี", "อมรินทร์", "อาชาไนย", "บุหลัน"
                , "พนาดร", "กุมาร", "พารา", "กัลยาณี", "สุราลัย", "ปทุม"};

        String[] semantic = {"พระอาทิตย์", "ปลา", "ลูกชาย", "ลูกสาว", "ดอกไม้", "ยักษ์", "ภูเขา", "กษัตริย์", "ช้าง", "น้ำ", "แผ่นดิน", "พระอินทร์", "ม้า", "พระจันทร์",
                "ป่า", "เด็ก", "เมือง", "ผู้หญิง", "สวรรค์", "ดอกบัว",};

        //เพิ่มข้อมูลลงตารางเรื่อยๆ
        ContentValues content = new ContentValues();
        for (int i = 0; i < homony.length; i++) {
            content.put(ColType, "1");
            content.put(ColHomony, homony[i]);
            content.put(ColSemantic, semantic[i]);

            db.insert(TableName, null, content);
        }
    }

    //การ update ข้อมูลใหม่ให้ database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);

    }
}