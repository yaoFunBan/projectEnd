package com.example.kimhuang.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class Kampong extends Activity {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> expandableListTile;
    HashMap<String, List<String>> expandableListDetail;

    SQLiteDatabase mDb;
    dataFairy mHelper;
    Cursor mCursor;
    String mQuery;
    List<String> Mean;

    int i = 0;
    String[] status;
    String[] key;
    int[] sound;
    int sizeS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new dataFairy(this);
        mQuery = "SELECT * FROM " + mHelper.Table_Kaphong;
        mDb = mHelper.getWritableDatabase();
//        mHelper.onUpgrade(mDb, 1, 1);
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();

        status = new String[mCursor.getCount()];
        key = new String[mCursor.getCount()];
        sound = new int[mCursor.getCount()];
        sizeS = status.length;

        Mean = new ArrayList<String>();
        expandableListDetail = new HashMap<String, List<String>>();
        while (!mCursor.isAfterLast()) {
            Mean = new ArrayList<String>(Arrays.asList(mCursor.getString(1)));
            expandableListDetail.put(mCursor.getString(0), Mean);
//            Log.e("Value ", "of Titles : " + expandableListDetail);
            mCursor.moveToNext();
        }
        for (String k : expandableListDetail.keySet()) {
            key[i] = k;
            i++;
        }


        for (int i = 0; i < key.length; i++) {
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast()) {
                if (mCursor.getString(0).equals(key[i])) {
                    status[i] = mCursor.getString(2);
                    sound[i] = mCursor.getInt(3);
                }
                mCursor.moveToNext();
            }
        }


        expandableListView = (ExpandableListView) findViewById(R.id.list_summary);
        expandableListTile = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomAdater(getApplicationContext(), expandableListTile, expandableListDetail, status, sound);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
    }


}
