package com.example.kimhuang.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class game3 extends AppCompatActivity {

    Button btn_pause , btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);


    }


    @Override
    public void attachBaseContext(Context ctx){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(ctx));
    }

}
