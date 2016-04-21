package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;

public class map extends AppCompatActivity implements View.OnClickListener {
    Button btn_scene1, btn_scene2, btn_scene3, btn_scene4;
    Button btn_back;
    Intent i;

//    chUnlock unlock11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        //btn_scene1
        btn_scene1 = (Button) findViewById(R.id.btn_scene1);
        btn_scene1.setOnClickListener(this);

        //btn_scene2
        btn_scene2 = (Button) findViewById(R.id.btn_scene2);
        btn_scene2.setOnClickListener(this);

        //btn_scene3
        btn_scene3 = (Button) findViewById(R.id.btn_scene3);
        btn_scene3.setOnClickListener(this);

        //btn_scene4
        btn_scene4 = (Button) findViewById(R.id.btn_scene4);
        btn_scene4.setOnClickListener(this);


        //button_back
        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, home.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (R.id.btn_scene1):
                i = new Intent(this, map1.class);
                startActivity(i);
                break;
            case (R.id.btn_scene2):
                i = new Intent(this, map2.class);
                startActivity(i);
                break;
            case (R.id.btn_scene3):
                i = new Intent(this, map3.class);
                startActivity(i);
                break;
            case (R.id.btn_scene4):
                i = new Intent(this, map4.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

