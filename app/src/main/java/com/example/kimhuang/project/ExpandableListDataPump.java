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
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }
}
