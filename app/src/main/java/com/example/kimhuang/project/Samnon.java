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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by วัชรัตน์ on 30/4/2559.
 */
public class Samnon extends Activity {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> expandableListTile;
    //    HashMap<String, List<String>> expandableListDetail;
    List<String> Mean;
    HashMap<String, List<String>> expandableListDetail;

    SQLiteDatabase mDb;
    dataFairy mHelper;
    Cursor mCursor;
    String mQuery;

    int i = 0;
    String[] status;
    String[] key;
    int[] sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new dataFairy(this);
        mQuery = "SELECT * FROM " + mHelper.Table_Samnon;
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();

        status = new String[mCursor.getCount()];
        key = new String[mCursor.getCount()];
        sound = new int[mCursor.getCount()];

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
                    status[i] = mCursor.getString(4);
                    sound[i] = mCursor.getInt(5);
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
