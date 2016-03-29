package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity implements View.OnClickListener {
    Button btn_play1, btn_setting1, btn_con1, btn_close;
    Intent i;
    AlertDialog.Builder builder;

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
                i = new Intent(this, scene1_1.class);
                startActivity(i);
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
}
