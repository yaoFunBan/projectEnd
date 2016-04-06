package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class home extends AppCompatActivity implements View.OnClickListener {
    Button btn_play1, btn_setting1, btn_con1, btn_close, btnClose;
    Switch swMusic, swEffect;
    Intent i;
    AlertDialog.Builder builder;
    MediaPlayer player;
    Intent svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        svc = new Intent(this, BackgroundSound.class);
        startService(svc);

        player = MediaPlayer.create(this, R.raw.bensound);
        player.setLooping(true);
        player.setVolume(50, 50);
        player.start();

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
                displayDiaglogSetting();
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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void displayDiaglogSetting() {
        final Dialog dsetting = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dsetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dsetting.setContentView(R.layout.setting_dialog);

        btnClose = (Button) dsetting.findViewById(R.id.btn_closes);
        swMusic = (Switch) dsetting.findViewById(R.id.sw_music);
        swEffect = (Switch) dsetting.findViewById(R.id.sw_effect);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsetting.cancel();
            }
        });


        Window window = dsetting.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dsetting.show();
    }
}
