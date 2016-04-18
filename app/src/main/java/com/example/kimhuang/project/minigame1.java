package com.example.kimhuang.project;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class minigame1 extends AppCompatActivity {
    Button btn_back, btn_playgame, btn_explain;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame1);
        //button_playgame
        btn_playgame = (Button) findViewById(R.id.btn_playgame);
        btn_playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), game1.class);
                startActivity(i);
            }
        });

        //button_explain
        btn_explain = (Button) findViewById(R.id.btn_explain);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        btn_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.loggame);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);

                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        //button_back
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
