package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class game3 extends AppCompatActivity {
    private TextView tvTimer, wordQue, ansLeft, ansRight;
    TextView Score;
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    ImageView Picture, mark;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogexit, dialoghome, dialogclose, dialogset;
    RelativeLayout box1, box2;

    //position ที่ F
    String[] posiLeft = {"20,1250", "1250,20"};

    //Database
    SQLiteDatabase gameDb;
    dataidioms game3;
    Cursor mCursor, wCursor;
    static int i = 0;

    //time
    int time = 50000, tempTime = 0;
    String[] randPos;

    //random
    int randPosi;
    RelativeLayout.LayoutParams params1, params2, paramsBaseR;

    //time
    int twscore = 0;


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
        Score = (TextView) findViewById(R.id.score);
        mark = (ImageView) findViewById(R.id.mark);

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
        // ตัดให้อยู่ใน array
        //randPos[0] = 20;
        //randPos[0] = 1050;
        //ตัดคำเมื่อเจอเครื่องหมาย (,)
        randPos = posiLeft[randPosi].split(",");

        // set margin ระยะห่างของกรอบ
        params1.setMargins(Integer.parseInt(randPos[0]), 1100, 0, 50);
        box1.setLayoutParams(params1);
        box1.getLayoutParams().height = 330;
        box1.getLayoutParams().width = 1250;

        // left , Top , Rigth, bottom
        params2.setMargins(Integer.parseInt(randPos[1]), 1100, 0, 50);
        box2.setLayoutParams(params2);
        box2.getLayoutParams().height = 330;
        box2.getLayoutParams().width = 1270;

        countTime(100000);

        //คลิก กล่องซ้ายมือ
        box1 = (RelativeLayout) findViewById(R.id.boxmess1);
//        box1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                randPos = posiLeft[randPosi].split(",");
//                mCursor.moveToNext();
//                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
//                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
//                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
//                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));
//                params1.setMargins(Integer.parseInt(randPos[0]), 1100, 0, 50);
//                box1.setLayoutParams(params1);
//            }
//        });

        ansLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //random index ใน array randPosi
                randPosi = getRandomPosition();
                randBox(randPosi);
                mCursor.moveToNext();
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));

                mark.setBackgroundResource(R.drawable.correct);
                twscore += 100;
                Score.setText("" + twscore);
            }
        });

        // คลิกกล่องขวามือ
        ansRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randPosi = getRandomPosition();
                randBox(randPosi);
                mCursor.moveToNext();
                wordQue.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLIdiom)));
                ansLeft.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesTrue)));
                ansRight.setText(mCursor.getString(mCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(mCursor.getInt(mCursor.getColumnIndex(game3.CoLPicture)));

                mark.setBackgroundResource(R.drawable.correct);
                cdt.cancel();
                tempTime -= 5000;
                countTime(tempTime);
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

    }

    // random ตำแหน่ง
    Random rand = new Random();

    public int getRandomPosition() {
        int r = rand.nextInt(posiLeft.length);
        return r;
    }


    //ตัด string แล้วเก็บใน array
    public void randBox(int rand) {
        randPos = posiLeft[rand].split(",");

        params2.setMargins(Integer.parseInt(randPos[0]), 1100, 0, 50);
        box2.setLayoutParams(params2);

        params1.setMargins(Integer.parseInt(randPos[1]), 1100, 0, 50);
        box1.setLayoutParams(params1);
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
    //ลดเวลา countTime
    public void countTime(int t) {
        cdt = new CountDownTimer(t, 50) {

            @Override
            public void onTick(long millisUntilFinished) {

                tempTime = (int) millisUntilFinished;
                tvTimer.setText(String.valueOf(tempTime));
                String strTime = String.format("%.1f"
                        , (double) millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
            }
        };
        cdt.start();
    }
}
