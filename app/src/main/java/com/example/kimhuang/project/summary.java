package com.example.kimhuang.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by วัชรัตน์ on 21/3/2559.
 */
public class summary extends Activity {

    String[] Word = {"พระชนนี ", "รองพระบาท ", "สังวาล ", "ชฏา", "พระสนับเพลา", "พระกระยาเสวย", "รัดพระองค์", "ฉลองพระองค์", "พระทวาร"};
    String[] Mean = {"แม่", "รองเท้า", "สร้อย", "มงกุฎ", "กางเกง", "ข้าว", "เข็มขัด", "เสื้อ", "ประตู",};
    String[] Status = {"correct", "correct", "correct", "correct", "uncorrent", "uncorrent", "uncorrent", "uncorrent", "uncorrent"};
    CustomAdater adater;
    ListView listSummary;

    SQLiteDatabase mDb;
    database mHelper;
    Cursor mCursor;
    //
//    String cor = "corrent";
//    String unCor = "uncorrent";
    int i = 0;

    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_summary);

//        mHelper = new database(this);
//        mDb = mHelper.getWritableDatabase();
//        mHelper.onUpgrade(mDb, 1, 1);
//
////        tx = (TextView) findViewById(R.id.test);
////        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);
////        mCursor.moveToPosition(0);
////
////        tx.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColStatus)));
//
//
//        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName
//                + " WHERE " + mHelper.ColStatus + " = 'corrent' OR "
//                + mHelper.ColStatus + " = 'uncorrent' ", null);
//
//        int count = mCursor.getCount();
//        mCursor.moveToFirst();
//
//        Word = new String[count];
//        Mean = new String[count];
//        Status = new String[count];
//
//        while (!mCursor.isAfterLast()) {
//            Word[i] = mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord));
//            Mean[i] = mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean));
//            Status[i] = mCursor.getString(mCursor.getColumnIndex(mHelper.ColStatus));
//            i++;
//            mCursor.moveToNext();
//        }

        listSummary = (ListView) findViewById(R.id.list_summary);
        adater = new CustomAdater(this, Word, Mean, Status);


        listSummary.setAdapter(adater);


    }
}
