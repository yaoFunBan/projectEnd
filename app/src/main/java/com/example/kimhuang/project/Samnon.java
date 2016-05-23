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
    dataidioms mHelper;
    Cursor mCursor;
    String mQuery;
    int i = 0;
    String[] status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new dataidioms(this);
        mQuery = "SELECT * FROM " + mHelper.TableName;
        mDb = mHelper.getWritableDatabase();
//        mHelper.onUpgrade(mDb, 1, 1);
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();
        status = new String[20];

        expandableListDetail = new HashMap<String, List<String>>();

        while (!mCursor.isAfterLast()) {
            Mean = new ArrayList<String>(Arrays.asList(mCursor.getString(0)));
            expandableListDetail.put(mCursor.getString(1), Mean);
            status[i] = mCursor.getString(4);
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
}
