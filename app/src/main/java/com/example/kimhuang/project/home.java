package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class home extends AppCompatActivity implements View.OnClickListener {
    Button btn_play1, btn_setting1, btn_con1, btn_close, btnClose, btn_summary;
    ToggleButton swMusic, swEffect;
    Intent i;
    AlertDialog.Builder builder;
    soundBG soundBG;
    Boolean effOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //button_play
        btn_play1 = (Button) findViewById(R.id.btn_play1);
        btn_play1.setOnClickListener(this);

        //button__setting
        btn_setting1 = (Button) findViewById(R.id.btn_setting1);
        btn_setting1.setOnClickListener(this);

        //button__con
        btn_con1 = (Button) findViewById(R.id.btn_con1);
        btn_con1.setOnClickListener(this);


        //button__close
        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        btn_summary = (Button) findViewById(R.id.btn_summary);
        btn_summary.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //class soundBG
//        soundBG = new soundBG(this);
//        soundBG.creatSound();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (R.id.btn_play1):
                i = new Intent(this, DisableSwipeIntro2.class);
                startActivity(i);
                break;
            case (R.id.btn_setting1):
                DialogSetting setDialog = new DialogSetting(home.this);
                setDialog.show();

                Window window = setDialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
                break;
            case (R.id.btn_con1):
                i = new Intent(this, contact.class);
                startActivity(i);
                break;
            case (R.id.btn_close):
                builder = new AlertDialog.Builder(home.this)
                        .setMessage("คุณต้องการออกจากเกม หรือไม่?")
                        .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                builder.setCancelable(false);
                builder.show();
                break;
            case (R.id.btn_summary):
                i = new Intent(this, summary.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        soundBG.stopBG();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        soundBG.pauseBG();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        soundBG.startBD();
    }
}
