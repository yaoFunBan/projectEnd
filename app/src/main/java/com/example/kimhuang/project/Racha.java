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
    database mHelper;
    Cursor mCursor;
    String mQuery;
    List<String> Mean;
    int i = 0;
    String[] status;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> expandableListTile;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new database(this);
        mQuery = "SELECT * FROM " + mHelper.TableName;
        mDb = mHelper.getWritableDatabase();
//        mHelper.onUpgrade(mDb, 1, 1);
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();
        status = new String[20];


        expandableListDetail = new HashMap<String, List<String>>();

        while (!mCursor.isAfterLast()) {
            Mean = new ArrayList<String>(Arrays.asList(mCursor.getString(1)));
            expandableListDetail.put(mCursor.getString(0), Mean);
            status[i] = mCursor.getString(2);
            mCursor.moveToNext();
            i++;
        }

        expandableListView = (ExpandableListView) findViewById(R.id.list_summary);
//        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTile = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomAdater(getApplicationContext(), expandableListTile, expandableListDetail, status);
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
