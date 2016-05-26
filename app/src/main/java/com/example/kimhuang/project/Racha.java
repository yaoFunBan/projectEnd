package com.example.kimhuang.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Racha extends Activity {

    SQLiteDatabase mDb;
    dataFairy mHelper;
    Cursor mCursor;
    String mQuery;
    List<String> Mean;
    int i = 0;
    String[] status;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> expandableListTile;
    HashMap<String, List<String>> expandableListDetail;

    String[] key;
    int[] sound;
    int sizeS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new dataFairy(this);
        mQuery = "SELECT * FROM " + mHelper.Table_Racha;
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();


        status = new String[mCursor.getCount()];
        key = new String[mCursor.getCount()];
        sound = new int[mCursor.getCount()];
        sizeS = status.length;

        expandableListDetail = new HashMap<String, List<String>>();

        while (!mCursor.isAfterLast()) {
            Mean = new ArrayList<String>(Arrays.asList(mCursor.getString(1)));
            expandableListDetail.put(mCursor.getString(0), Mean);
//            status[i] = mCursor.getString(2);
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
//        expandableListDetail = ExpandableListDataPump.getData();
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

    public void prepareListData() {
        expandableListTile = new ArrayList<String>();
//        expandableListDetail =
    }
}
