package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class game3 extends AppCompatActivity {
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    TextView tvTimer, wordQue, ansLeft, ansRight;
    ImageView Picture, box1, box2;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;

    //Database
    SQLiteDatabase gameDb;
    dataidioms game3;
    Cursor mCursor, wCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordQue = (TextView) findViewById(R.id.quustion);
        ansLeft = (TextView) findViewById(R.id.AnsLeft);
        ansRight = (TextView) findViewById(R.id.AnsRight);
        Picture = (ImageView) findViewById(R.id.picture);

        //decaler database
        game3 = new dataidioms(this);
        gameDb = game3.getWritableDatabase();
        game3.onUpgrade(gameDb, 1, 1);

        //เป็นการอ่านค่าในตาราง database ว่าจะให้อ่านค่าเป็นคอลัมถ์ไปเรื่อยๆ
        mCursor = gameDb.rawQuery("SELECT * FROM " + game3.TableName, null);
        mCursor.moveToFirst();

        wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
        ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
        ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
        Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));

        //คลิก กล่องซ้ายมือ
        box1 = (ImageView) findViewById(R.id.boxmess1);
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToNext();
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
            }
        });

        //คลิก กล่องขวามือ
        box2 = (ImageView) findViewById(R.id.boxmess2);
        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToNext();
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
            }
        });

        //button_pause
        btn_pause = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.btndialog);

                //TODO findViewBy
                dialogexit = (Button) dialog.findViewById(R.id.btn_exit);
                dialoghome = (Button) dialog.findViewById(R.id.btn_home);
                dialogset = (Button) dialog.findViewById(R.id.btn_setting);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);

                //button_exit
                dialogexit.setOnClickListener(new View.OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });

                //button_home
                dialoghome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), map3.class);
                        startActivity(i);
                    }
                });

                //button_setting
                dialogset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayDiaglogSetting();
                    }
                });

//                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        CountDownTimer cdt = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                // Finish
            }
        }.start();
    }

    //Dialogsetting
    private void displayDiaglogSetting() {
        final Dialog dsetting = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dsetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dsetting.setContentView(R.layout.setting_dialog);

        btnClose = (Button) dsetting.findViewById(R.id.btn_close);
        swMusic = (ToggleButton) dsetting.findViewById(R.id.sw_music);
        swEffect = (ToggleButton) dsetting.findViewById(R.id.sw_effect);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsetting.cancel();
            }
        });

        swMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        swEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Window window = dsetting.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dsetting.show();
    }
}
