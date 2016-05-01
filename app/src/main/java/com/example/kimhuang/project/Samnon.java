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
    HashMap<String, String> expandableListDetail;

    SQLiteDatabase mDb;
    database mHelper;
    Cursor mCursor;
    String mQuery;
    int i = 0;
    String Titlt;
    String Detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        mHelper = new database(this);
        mQuery = "SELECT * FROM " + mHelper.TableName;
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery(mQuery, null);
        mCursor.moveToFirst();

        expandableListDetail = new HashMap<String, String>();

        while (!mCursor.isAfterLast()) {
            expandableListDetail.put(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)), getString(mCursor.getColumnIndex(mHelper.ColMean)));
            mCursor.moveToNext();
        }


        expandableListView = (ExpandableListView) findViewById(R.id.list_summary);
//        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTile = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomAdater(getApplicationContext(), expandableListTile, expandableListDetail);
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
