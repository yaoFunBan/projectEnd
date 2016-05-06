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
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class game3 extends AppCompatActivity {
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    private TextView tvTimer, wordQue, ansLeft, ansRight;
    ImageView Picture;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button    dialogexit, dialoghome, dialogclose,dialogset;
    RelativeLayout box1, box2;

    //Database
    SQLiteDatabase gameDb;
    dataidioms game3;
    Cursor mCursor, wCursor;
    static int i = 0;
    //time
    int time = 50000, tempTime = 0;

    int randPosi, randPosi2;

    RelativeLayout.LayoutParams params1, params2, paramsBaseR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordQue = (TextView) findViewById(R.id.quustion);
        ansLeft = (TextView) findViewById(R.id.AnsLeft);
        ansRight = (TextView) findViewById(R.id.AnsRight);
        Picture = (ImageView) findViewById(R.id.picture);
        box1 = (RelativeLayout) findViewById(R.id.boxmess1);
        box2 = (RelativeLayout) findViewById(R.id.boxmess2);

        //decaler database
        game3 = new dataidioms(this);
        gameDb = game3.getWritableDatabase();
        game3.onUpgrade(gameDb, 1, 1);

        //เป็นการอ่านค่าในตาราง database ว่าจะให้อ่านค่าเป็นคอลัมถ์ไปเรื่อยๆ
        mCursor = gameDb.rawQuery("SELECT * FROM " + game3.TableName, null);
        mCursor.moveToFirst();
        wCursor = gameDb.rawQuery("SELECT * FROM " + game3.TableName, null);
        wCursor.moveToFirst();

        //ให้ cursor ชี้ไปที่ สำนวน ความหมายถูก รูปภาพ
        wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
        ansLeft.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
        ansRight.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
        Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));

        //
        params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        paramsBaseR = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        // random ตำแหน่งคำ
        randPosi = getRandomPosition();
        randPosi2 = getRandomPosition();

        // set margin ระยะห่างของกรอบ
        params1.setMargins(randPosi2, 1100, 0, 50);
        box1.setLayoutParams(params1);
        box1.getLayoutParams().height = 330;
        box1.getLayoutParams().width = 1250;

        params2.setMargins(randPosi, 1100, 0, 50);
        box2.setLayoutParams(params2);
        box2.getLayoutParams().height = 330;
        box2.getLayoutParams().width = 1270;


        //คลิก กล่องซ้ายมือ
        box1 = (RelativeLayout) findViewById(R.id.boxmess1);
        box1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCursor.moveToNext();
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
                randPosi2 = getRandomPosition();
                params1.setMargins(randPosi2, 1100, 0, 50);
                box1.setLayoutParams(params1);
            }
        });

        ansLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
            }
        }) ;

        // คลิกกล่องขวามือ
        ansRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToNext();
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
                randPosi = getRandomPosition();
                params2.setMargins(randPosi, 1100, 0, 50);
                box2.setLayoutParams(params2);
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

        //CountDownTimer (โดยจะลดลงครั้งละ 1 วินาที)
        CountDownTimer cdt = new CountDownTimer(100000, 1000) {
            public void onTick(long millisUntilFinished) {

                //ให้วลานับถอยหลังทีละ 1 วินาที
                tempTime = (int) millisUntilFinished;
                tvTimer.setText(String.valueOf(tempTime));
                String strTime = String.format("%1.0f", (double) millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            public void onFinish() {
                // Finish
            }
        }.start();
    }

    // random ตำแหน่ง
    private int getRandomPosition() {
        float b = getApplicationContext().getResources().getDisplayMetrics().density;
        int[] posiLeft = {20, 650};
        int num = (int) (Math.random() * posiLeft.length);
        return (int) (posiLeft[num] * b);
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
