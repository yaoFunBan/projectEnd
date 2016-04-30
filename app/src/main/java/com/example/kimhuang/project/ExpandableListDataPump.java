package com.example.kimhuang.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExpandableListDataPump {
    private static database mHelper;
    private static SQLiteDatabase msql;
    private static Cursor mCursor;

    public ExpandableListDataPump(Context ctx) {
        mHelper = new database(ctx);
    }

    public static HashMap<String, List<String>> getData() {

//        msql = mHelper.getWritableDatabase();
//        mCursor = msql.rawQuery("SELECT * FROM " + mHelper.TableName, null);
//        mCursor.moveToFirst();
//
//        while (mCursor.isAfterLast() {
//            Toast.makeText(Exp, "" + mCursor.getCount(), Toast.LENGTH_LONG).show;
//        }

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> cricket = new ArrayList<String>();
        cricket.add("คนโง่มักถูกคนที่ฉลาดกว่าใช้กลอุบายหลอกล่อให้ ยอมทำตามเสมอ");

        List<String> football = new ArrayList<String>();
        football.add("แม่");

        List<String> basketball = new ArrayList<String>();
        basketball.add("สุริยัน");

        expandableListDetail.put("คนโง่ย่อมตกเป็นเหยื่อคนฉลาด", cricket);
        expandableListDetail.put("พระชนนี", football);
        expandableListDetail.put("พระอาทิตย์", basketball);
        return expandableListDetail;
    }
}
