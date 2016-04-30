package com.example.kimhuang.project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by วัชรัตน์ on 21/3/2559.
 */
public class summary extends Activity implements View.OnClickListener {

    String[] Word = {"พระชนนี ", "รองพระบาท ", "สังวาล ", "ชฏา", "พระสนับเพลา", "พระกระยาเสวย", "รัดพระองค์", "ฉลองพระองค์", "พระทวาร"};
    String[] Mean = {"แม่", "รองเท้า", "สร้อย", "มงกุฎ", "กางเกง", "ข้าว", "เข็มขัด", "เสื้อ", "ประตู",};
    String[] Status = {"correct", "correct", "correct", "correct", "uncorrent", "uncorrent", "uncorrent", "uncorrent", "uncorrent"};
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> expandableListTile;
    HashMap<String, List<String>> expandableListDetail;
    Intent intent;

    Button btnBack;

    SQLiteDatabase mDb;
    database mHelper;
    Cursor mCursor;
    //    String cor = "corrent";
//    String unCor = "uncorrent";
    int i = 0;

    TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_summary);

        mHelper = new database(this);
        mDb = mHelper.getWritableDatabase();
        mHelper.onUpgrade(mDb, 1, 1);
        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);

        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);


        expandableListView = (ExpandableListView) findViewById(R.id.list_summary);
        expandableListDetail = ExpandableListDataPump.getData();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }

    }


//    public void selectDatabase() {
//        expandableListTile = new ArrayList<String>();
//        expandableListDetail = new HashMap<String, List<String>>();
//
//    }


}
