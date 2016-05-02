package com.example.kimhuang.project;

import android.app.Activity;
import android.app.LocalActivityManager;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by วัชรัตน์ on 21/3/2559.
 */
public class summary extends Activity implements View.OnClickListener {

    Intent intent;

    Button btnBack;

    LocalActivityManager localActivityManager;

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_summary);

        localActivityManager = new LocalActivityManager(this, false);
        localActivityManager.dispatchCreate(savedInstanceState);


        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup(localActivityManager);

        //1.defind name tab, 2.name title, 3.content display in each tab
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1").setIndicator("คำราชาศัพท์").setContent(new Intent(this, Racha.class));
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab2").setIndicator("คำพ้อง").setContent(new Intent(this, Kampong.class));
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tab3").setIndicator("สำนวน").setContent(new Intent(this, Samnon.class));



        tabHost.addTab(tabSpec);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);


        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);


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
