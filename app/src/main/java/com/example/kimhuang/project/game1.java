package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ToggleButton;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

public class game1 extends AppCompatActivity implements View.OnClickListener {
    private TextView wordAns, tvTimer, str1, str2, str3, score;
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    MediaPlayer mediaPlayer;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogexit, dialogagain, dialogclose;
    RelativeLayout ball1, ball2, ball3;

    //Databas
    SQLiteDatabase gameDb;
    datahomony game1;
    Cursor mCursor, wCursor;
    static int i = 0;

    //position ที่ random
    String[] posiLeft = {"20,500,1250", "1250,500,20"};

    //time
    int time = 50000, tempTime = 0;
    String[] randPos;
    int randPosi;
    RelativeLayout.LayoutParams params, params1, params2, paramsBaseR;

    //Ansrandom
    int a[] = {0, 2, 8, 15, 1, 5, 10, 7, 13, 4, 3, 11, 12, 16, 18, 6, 9, 17, 14, 19};
    int b[] = {12, 1, 19, 3, 14, 6, 2, 4, 8, 9, 16, 0, 5, 13, 17, 15, 7, 10, 18, 11};
    int c[] = {18, 5, 2, 17, 4, 9, 6, 3, 0, 12, 10, 1, 13, 15, 14, 19, 16, 7, 11, 8};
    int index = 0;
    int chAns = 0;
    int t = 100000;

    //score
    int twScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordAns = (TextView) findViewById(R.id.quustion);
        str1 = (TextView) findViewById(R.id.str1);
        str2 = (TextView) findViewById(R.id.str2);
        str3 = (TextView) findViewById(R.id.str3);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        ball1 = (RelativeLayout) findViewById(R.id.ball1);
        ball2 = (RelativeLayout) findViewById(R.id.ball2);
        ball3 = (RelativeLayout) findViewById(R.id.ball3);
        score = (TextView) findViewById(R.id.score);

        //decaler database
        game1 = new datahomony(this);
        gameDb = game1.getWritableDatabase();
        game1.onUpgrade(gameDb, 1, 1);


        //animation ball
        YoYo.with(Techniques.Pulse)
                .duration(5000)
                .playOn(findViewById(R.id.ball2));


        //READ DATA (เป็นการอ่านค่าในตาราง database โดยกำหนดให้ mCursor เลื่อนอ่านข้อมูลในแต่ละคอลัมไปเรื่อยๆ)
        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.TableName, null);

        mCursor.moveToFirst();
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
        //Upgrade


        //เป็นการอ่านค่าในตาราง database ว่าจะให้อ่านค่าเป็นคอลัมไปเรื่อยๆ
//        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.TableName, null);
//        mCursor.moveToFirst();

        countTime(t);
        //CountDownTimer (โดยจะลดลงครั้งละ 1 วินาที)

        //เรียกใช้ฟังก์ชัน index
        addindex(index);

        //params
        params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        paramsBaseR = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        //random ตำแหน่งคำ
        randPosi = getRandomPosition();
        // ตัดให้อยู่ใน array randPos[0] = 20; randPos[1] = 500; randPos[2] = 1280;
        //ตัดคำเมื่อเจอเครื่องหมาย (,)
        randPos = posiLeft[randPosi].split(",");

        // set margin ระยะห่างของกรอบของ ball1 (ตำแหน่ง ซ้าย บน ขวา ล่าง)
        params.setMargins(100, 500, 0, 50);
        ball1.setLayoutParams(params);
        ball1.getLayoutParams().height = 450;
        ball1.getLayoutParams().width = 450;

        // set magin ball2 (ตำแหน่ง ซ้าย บน ขวา ล่าง)
        params1.setMargins(1000, 20, 0, 50);
        ball2.setLayoutParams(params1);
        ball2.getLayoutParams().height = 450;
        ball2.getLayoutParams().width = 450;

        // set magin ball2 (ตำแหน่ง ซ้าย บน ขวา ล่าง)
        params2.setMargins(2100, 300, 0, 50);
        ball3.setLayoutParams(params2);
        ball3.getLayoutParams().height = 450;
        ball3.getLayoutParams().width = 450;


        //button_pause
        btn_pause = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.pausegame);
                //TODO findViewBy
                dialogexit = (Button) dialog.findViewById(R.id.btn_exit);
                dialogagain = (Button) dialog.findViewById(R.id.btn_again);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);

                //button_exit
                dialogexit.setOnClickListener(new View.OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });

                //button_again
                dialogagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
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


        //event click (เรียกใช้ method onClick)
        ball1.setOnClickListener(this);
        ball2.setOnClickListener(this);
        ball3.setOnClickListener(this);

    }

    //OnClick Ball
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (R.id.ball1):
                Ansch(a[index]);
                index++;
                //เพิ่มค่าขึ้นมาทีละชุด
                addindex(index);
                incrementQuestion();
                break;
            case (R.id.ball2):
                Ansch(b[index]);
                index++;
                addindex(index);
                incrementQuestion();
                break;
            case (R.id.ball3):
                Ansch(c[index]);

                index++;
                addindex(index);
                incrementQuestion();
                break;
        }
    }

    // random ตำแหน่ง
    Random rand = new Random();

    public int getRandomPosition() {
        int r = rand.nextInt(posiLeft.length);
        return r;
    }

    public void addindex(int index) {

        mCursor.moveToPosition(a[index]);
        str1.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
        mCursor.moveToPosition(b[index]);
        str2.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
        mCursor.moveToPosition(c[index]);
        str3.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));

    }
    //wordAns
    public void incrementQuestion(){
        mCursor.moveToPosition(chAns);
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
    }

    public void Ansch(int index) {
        if (chAns == index) {
            //กรณีที่ตอบถูก
            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            twScore += 100;
            score.setText("" + twScore);
            mediaPlayer = MediaPlayer.create(this, R.raw.correct);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "ผิด", Toast.LENGTH_SHORT).show();
            cdt.cancel();
            tempTime -= 5000;
            countTime(tempTime);
            mediaPlayer = MediaPlayer.create(this, R.raw.wrong);
            mediaPlayer.start();
        }

        chAns++;

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
                if (tempTime == 0 ){

                }
            }
        };
        cdt.start();
    }

}
