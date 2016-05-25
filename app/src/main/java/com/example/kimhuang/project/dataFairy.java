package com.example.kimhuang.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Spaky on 24/5/2559.
 */
public class dataFairy extends SQLiteOpenHelper {

    // Name table
    private static final String DbName = "DBFairy";
    private static final int DB_version = 1;

    //Table name
    public static final String Table_Racha = "Racha";
    public static final String Table_Samnon = "Samnon";
    public static final String Table_Kaphong = "Kaphong";

    // Common column Word
    public static final String Col_Word_Racha = "wordRacha";
    public static final String Col_Word_Samnon = "wordSamnon";
    public static final String Col_Word_Kaphong = "wordKaphong";

    //Common column mean
    public static final String Col_Mean_Racha = "mRacha";
    public static final String Col_Mean_Samon_T = "mSamnonT";
    public static final String Col_Mean_Kaphong = "mKaphong";

    //Common column mean False
    public static final String Col_Mean_Samon_F = "mSamnonF";

    //Common column Picture
    public static final String Col_Pic_Samon = "pSamon";

    //Common column Status
    public static final String Col_Stat_Racha = "sRacha";
    public static final String Col_Stat_Samon = "sSamon";
    public static final String Col_Stat_Kaphong = "sKaphong";

    //Common column Sound
    public static final String Col_Sound_Racha = "soRacha";
    public static final String Col_Sound_Samon = "soSamon";
    public static final String Col_Sound_Kaphong = "soKaphong";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_RACHA = "CREATE TABLE " + Table_Racha +
            "( " + Col_Word_Racha + " TEXT PRIMARY KEY, " +
            Col_Mean_Racha + " TEXT, " +
            Col_Stat_Racha + " TEXT ," +
            Col_Sound_Racha + " TEXT );";

    private static final String CREATE_TABLE_Kaphong = "CREATE TABLE " + Table_Kaphong +
            "( " + Col_Word_Kaphong + " TEXT PRIMARY KEY, " +
            Col_Mean_Kaphong + " TEXT, " +
            Col_Stat_Kaphong + " TEXT ," +
            Col_Sound_Kaphong + " TEXT );";

    private static final String CREATE_TABLE_SUPAR = "CREATE TABLE " + Table_Samnon +
            " ( " + Col_Word_Samnon + " TEXT PRIMARY KEY, " +
            Col_Mean_Samon_T + " TEXT, " +
            Col_Mean_Samon_F + " TEXT, " +
            Col_Pic_Samon + " INTEGER, " +
            Col_Stat_Samon + " TEXT , " +
            Col_Sound_Samon + " TEXT);";

    //data in table Racha
    String[] wRach = {"พระชนนี ", "รองพระบาท ", "สังวาล ", "ชฏา", "พระสนับเพลา"
            , "พระอุทร", "พระนาสิก", "พระกรรณ", "ข้อพระบาท",
            "พระโอษฐ์", "พระมังสา", "พระเนตร", "พระชนก", "พระเชษฐา",
            "พระสัสสุระ", "พระสวามี", "ฉลองพระองค์", "พระสุณิสา", "พระเทวัน", "พระชามาดา"};

    String[] mRacha = {"แม่", "รองเท้า", "สร้อย", "มงกุฎ", "กางเกง",
            "ท้อง", "จมูก", "หู", "ข้อเท้า",
            "ปาก", "เนื้อ", "ผิวหน้า", "พ่อ", "พี่ชาย",
            "พ่อตา", "สามี", "เสื้อ", "ลูกสะใภ้", "พี่เขย", "ลูกเขย"};

    String[] stRacha = {"uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect",
            "uncorrect", "uncorrects", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect"};

    int[] soRacha = {R.raw.janta, R.raw.janta, R.raw.soi, R.raw.jantawee, R.raw.kangkeng,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};

    //data in table kahong
    String[] wKahong = {"สุริยัน", "มัจฉา", "ดนัย", "บุตรี", "ผกา", "รามสูร", "บรรพต", "ขัตติยะ", "กุญชร", "กระสินธิ์ ",
            "ปฐพี", "อมรินทร์", "อาชาไนย", "บุหลัน", "พนาดร", "กุมาร", "พารา", "กัลยาณี", "สุราลัย", "ปทุม"};

    String[] mKaphong = {"พระอาทิตย์", "ปลา", "ลูกชาย", "ลูกสาว", "ดอกไม้", "ยักษ์", "ภูเขา", "กษัตริย์", "ช้าง", "น้ำ",
            "แผ่นดิน", "พระอินทร์", "ม้า", "พระจันทร์", "ป่า", "เด็ก", "เมือง", "ผู้หญิง", "สวรรค์", "ดอกบัว",};

    String[] stKaphong = {"uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect",
            "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect"};

    int[] soKaphong = {R.raw.janta, R.raw.firsh, R.raw.yotsuwimon, R.raw.jantawee, R.raw.jantawee,
            R.raw.yuk, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};

    //data in table Samon
    String[] wSamon = {"กระต่ายหมายจันทร์", "กาคาบพริก", "กิ่งทองใบหยก", "ไก่เห็นตีนงู งูเห็นนมไก่", "เข้าด้ายเข้าเข็ม",
            "เข้าเมืองตาหลิ่ว ต้องหลิ่วตาตาม", "เข็นครกขึ้นภูเขา", "เขียนเสือให้วัวกลัว", "คนโง่ย่อมตกเป็นเหยื่อคนฉลาด",
            "จับปลาสองมือ", "จับปูใส่กระด้ง", "ช้างตายทั้งตัว เอาใบบัวปิดไม่มิด", "ดินพอกหางหมู", "น้ำมาปลากินมด น้ำลดมดกินปลา",
            "หนูตกถังข้าวสาร"};

    String[] mSamonT = {"ชายที่หมายปองหญิงที่มีฐานะดีกว่าตัวเอง", "คนผิวดำที่ชอบแต่งตัวด้วยเสื้อผ้าสีแดงๆ ",
            "ชายหญิงเหมาะสมกันดีที่จะแต่งงาน", "ต่างฝ่ายรู้ความลับของกันและกัน", "เวลาสำคัญ จะทำพลาดไม่ได้",
            "ทำตัวให้เหมาะกับสภาพแวดล้อมที่คนในสังคมนั้นทำกัน", "การทำงานที่ลำบาก เกินความสามารถของตน",
            "หลอก ขู่ ให้ฝ่ายตรงข้ามเสียขวัญ", "คนโง่มักถูกคนที่ฉลาดกว่าใช้กลอุบายหลอกล่อให้ ยอมทำตามเสมอ",
            "ทำอะไรหลายอย่างในเวลาเดียวกันอาจไม่สำเร็จซักอย่าง", "การดูแลเด็กเล็กๆ โดยพยายามให้อยู่นิ่งๆ แต่เด็กก็ซุกซนไม่อยู่นิ่ง",
            "ความผิดร้ายแรงที่รู้กันทั่ว จะปิดอย่างไรก็ไม่มิด", "งานทีปล่อยไว้ให้สะสมคั่งค้าง จนทำให้ลำบากเดือดร้อน ",
            "ใครมีจังหวะดีของใคร ฝ่ายนั้นก็ย่อมชนะ", "ผู้ทีมีฐานะยากจนได้แต่งงานกับคนร่ำรวย"};

    String[] mSamonF = {"กระต่ายที่อยากไปอยู่บนดวงจันทร์", "คนผิวดำที่ชอบแต่งตัวเหมือนกา", "ชื่อพันธุ์ไม้ชนิดนึง",
            "ต่างฝ่ายต่างมีความรักให้แก่กันและกัน", "เวลารีบร้อนจึงทำผิดพลาด", "นำประเพณีใหม่มาเผยแพร่ให้คนในสังคมนั้น",
            "ทำงานง่ายๆที่พี่พอดีกับงานของตน", "ให้กำลังใจฝ่ายตรงข้าม", "คนฉลาดมักช่วยเหลือคนโง่", "ทำทีละอย่างแต่ไม่สำเร็จซักอย่าง",
            "พาเด็กจับปูใส่กระด้ง", "ความดีที่อยากให้คนรู้กันทั่ว", "งานที่ทำเสร็จทันเวลา ตามกำหนด", "เมื่อมีโอกาสใครช้าก็ย่อมพลาด",
            "ผู้ที่มีฐานะเดียวกันแต่งงานกัน"};

    String[] stSamon = {"uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect",
            "uncorrect", "uncorrect", "correct", "uncorrect", "correct", "uncorrect", "uncorrect", "uncorrect", "uncorrect", "uncorrect"};

    int[] pSamon = {R.drawable.rabbit, R.drawable.crow, R.drawable.green, R.drawable.snake, R.drawable.needle,
            R.drawable.men, R.drawable.mountain, R.drawable.tiger, R.drawable.stupid, R.drawable.twofish,
            R.drawable.crab, R.drawable.chang, R.drawable.pig, R.drawable.ant, R.drawable.rat};

    int[] soSamon = {R.raw.janta, R.raw.janta, R.raw.soi, R.raw.jantawee, R.raw.kangkeng,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee,
            R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee, R.raw.jantawee};


    public dataFairy(Context context) {
        super(context, DbName, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RACHA);
        db.execSQL(CREATE_TABLE_Kaphong);
        db.execSQL(CREATE_TABLE_SUPAR);

        //insert data in table Racha
        InsertTableRacha(db);

        //insert data in table Kaphong
        InsertTableKaphong(db);

        //insert data in table Samon
        InsertTableSamom(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Racha);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Samnon);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Kaphong);
        onCreate(db);
    }


    public void InsertTableRacha(SQLiteDatabase db) {
//        db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        for (int i = 0; i < wRach.length; i++) {
            content.put(Col_Word_Racha, wRach[i]);
            content.put(Col_Mean_Racha, mRacha[i]);
            content.put(Col_Stat_Racha, stRacha[i]);
            content.put(Col_Sound_Racha, soRacha[i]);

            db.insert(Table_Racha, null, content);
        }
    }

    public void InsertTableSamom(SQLiteDatabase db) {
//        db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        for (int i = 0; i < wSamon.length; i++) {
            content.put(Col_Word_Samnon, wSamon[i]);
            content.put(Col_Mean_Samon_T, mSamonT[i]);
            content.put(Col_Mean_Samon_F, mSamonF[i]);
            content.put(Col_Pic_Samon, pSamon[i]);
            content.put(Col_Stat_Samon, stSamon[i]);
            content.put(Col_Sound_Samon, soSamon[i]);

            db.insert(Table_Samnon, null, content);
        }
    }

    public void InsertTableKaphong(SQLiteDatabase db) {
//        db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        for (int i = 0; i < wKahong.length; i++) {
            content.put(Col_Word_Kaphong, wKahong[i]);
            content.put(Col_Mean_Kaphong, mKaphong[i]);
            content.put(Col_Stat_Kaphong, stKaphong[i]);
            content.put(Col_Sound_Kaphong, soKaphong[i]);

            db.insert(Table_Kaphong, null, content);
        }
    }
}
