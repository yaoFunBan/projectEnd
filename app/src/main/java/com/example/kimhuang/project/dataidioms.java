package com.example.kimhuang.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Donnapa on 25-Apr-16.
 */
public class dataidioms extends SQLiteOpenHelper {
    private static final String Dbname = "DBFairy";
    private static final int DB_version = 1;
    public static final String TableName = "vocIdioms";
    public static final String CoLIdiom = "idiom";
    public static final String CoLMesTrue = "mestrue";
    public static final String CoLMesFalse = "mesfalse";
    public static final String CoLStatus = "Status";
    public static final String CoLSound = "Sound";
    public static final String CoLPicture = "pPicture";

    public dataidioms(Context context) {
        super(context, Dbname, null, DB_version);
    }

    //การ create,insert, delete ข้อมูลใน database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " ( " + CoLIdiom + " TEXT, " + CoLMesTrue + " TEXT, "
                + CoLMesFalse + " TEXT, " + CoLPicture + " INTEGER, " + CoLStatus + " TEXT , " + CoLSound + " TEXT);");

        String[] idiom = {"กระต่ายหมายจันทร์", "กาคาบพริก", "กิ่งทองใบหยก", "ไก่เห็นตีนงู งูเห็นนมไก่", "เข้าด้ายเข้าเข็ม",
                "เข้าเมืองตาหลิ่ว ต้องหลิ่วตาตาม", "เข็นครกขึ้นภูเขา", "เขียนเสือให้วัวกลัว", "คนโง่ย่อมตกเป็นเหยื่อคนฉลาด",
                "จับปลาสองมือ", "จับปูใส่กระด้ง", "ช้างตายทั้งตัว เอาใบบัวปิดไม่มิด", "ดินพอกหางหมู", "น้ำมาปลากินมด น้ำลดมดกินปลา",
                "หนูตกถังข้าวสาร"};

        String[] mestrue = {"ชายที่หมายปองหญิงที่มีฐานะดีกว่าตัวเอง", "คนผิวดำที่ชอบแต่งตัวด้วยเสื้อผ้าสีแดงๆ ",
                "ชายหญิงเหมาะสมกันดีที่จะแต่งงาน", "ต่างฝ่ายรู้ความลับของกันและกัน", "เวลาสำคัญ จะทำพลาดไม่ได้",
                "ทำตัวให้เหมาะกับสภาพแวดล้อมที่คนในสังคมนั้นทำกัน", "การทำงานที่ลำบาก เกินความสามารถของตน",
                "หลอก ขู่ ให้ฝ่ายตรงข้ามเสียขวัญ", "คนโง่มักถูกคนที่ฉลาดกว่าใช้กลอุบายหลอกล่อให้ ยอมทำตามเสมอ",
                "ทำอะไรหลายอย่างในเวลาเดียวกันอาจไม่สำเร็จซักอย่าง", "การดูแลเด็กเล็กๆ โดยพยายามให้อยู่นิ่งๆ แต่เด็กก็ซุกซนไม่อยู่นิ่ง",
                "ความผิดร้ายแรงที่รู้กันทั่ว จะปิดอย่างไรก็ไม่มิด", "งานทีปล่อยไว้ให้สะสมคั่งค้าง จนทำให้ลำบากเดือดร้อน ",
                "ใครมีจังหวะดีของใคร ฝ่ายนั้นก็ย่อมชนะ", "ผู้ทีมีฐานะยากจนได้แต่งงานกับคนร่ำรวย"};

        String[] mesfalse = {"กระต่ายที่อยากไปอยู่บนดวงจันทร์", "คนผิวดำที่ชอบแต่งตัวเหมือนกา", "ชื่อพันธุ์ไม้ชนิดนึง",
                "ต่างฝ่ายต่างมีความรักให้แก่กันและกัน", "เวลารีบร้อนจึงทำผิดพลาด", "นำประเพณีใหม่มาเผยแพร่ให้คนในสังคมนั้น",
                "ทำงานง่ายๆที่พี่พอดีกับงานของตน", "ให้กำลังใจฝ่ายตรงข้าม", "คนฉลาดมักช่วยเหลือคนโง่", "ทำทีละอย่างแต่ไม่สำเร็จซักอย่าง",
                "พาเด็กจับปูใส่กระด้ง", "ความดีที่อยากให้คนรู้กันทั่ว", "งานที่ทำเสร็จทันเวลา ตามกำหนด", "เมื่อมีโอกาสใครช้าก็ย่อมพลาด",
                "ผู้ที่มีฐานะเดียวกันแต่งงานกัน"};

        String[] status = {"uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect",
                "uncorrect", "uncorrect", "correct", "uncorrect", "correct", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect"};

        int[] pPicture = {R.drawable.rabbit, R.drawable.crow, R.drawable.green, R.drawable.snake, R.drawable.needle,
                R.drawable.men, R.drawable.mountain, R.drawable.tiger, R.drawable.stupid, R.drawable.twofish,
                R.drawable.crab, R.drawable.chang, R.drawable.pig, R.drawable.ant, R.drawable.rat};

        int[] sound = {R.raw.janta, R.raw.janta, R.raw.soi, R.raw.jantawee, R.raw.kangkeng,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
                R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};

        //เพิ่มข้อมูลลงตารางเรื่อยๆ
        ContentValues content = new ContentValues();
        for (int i = 0; i < idiom.length; i++) {
            content.put(CoLIdiom, idiom[i]);
            content.put(CoLMesTrue, mestrue[i]);
            content.put(CoLMesFalse, mesfalse[i]);
            content.put(CoLPicture, pPicture[i]);
            content.put(CoLStatus, status[i]);
            content.put(CoLSound, sound[i]);

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
