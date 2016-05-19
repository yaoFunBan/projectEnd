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

import java.util.ArrayList;
import java.util.Random;

public class game1 extends AppCompatActivity implements View.OnClickListener {
    private TextView wordAns, tvTimer, str1, str2, str3, score, final_score;
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    MediaPlayer mediaPlayer;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialoghome, dialogagain, dialogclose, dialogsummary, dialogreplay;
    RelativeLayout ball1, ball2, ball3;

    //Databas
    SQLiteDatabase gameDb;
    datahomony game1;
    Cursor mCursor;
    static int i = 0;

    //time
    int time = 50000, tempTime = 0;
    RelativeLayout.LayoutParams params, params1, params2, paramsBaseR;
    int chAns = 0;
    int t = 100000;

    //score
    int twScore = 0;

    //fisher



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


        //READ DATA (เป็นการอ่านค่าในตาราง database โดยกำหนดให้ mCursor เลื่อนอ่านข้อมูลในแต่ละคอลัมไปเรื่อยๆ)
        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.TableName, null);
        mCursor.moveToFirst();
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));

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
                dialoghome = (Button) dialog.findViewById(R.id.btn_home);
                dialogagain = (Button) dialog.findViewById(R.id.btn_again);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);
                cdt.cancel();

                //button_exit
                dialoghome.setOnClickListener(new View.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
                    }
                });

                //button_again
                dialogagain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCursor.moveToFirst();
                        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
                        dialog.cancel();
                        ball1.setClickable(false);
                        ball2.setClickable(false);
                        ball3.setClickable(false);
                        chAns = 0;

                        new CountDownTimer(1000, 50) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                twScore = 0;
                                score.setText("" + twScore);
                                ball1.setClickable(true);
                                ball2.setClickable(true);
                                ball3.setClickable(true);
                            }
                        }.start();
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
                if (!mCursor.isLast()) {
                    incrementQuestion();
                } else {
                    dialogFinish();
                    cdt.cancel();
                }
                break;
            case (R.id.ball2):
                if (!mCursor.isLast()) {
                    incrementQuestion();
                } else {
                    dialogFinish();
                    cdt.cancel();
                }
                break;
            case (R.id.ball3):
                if (!mCursor.isLast()) {
                    incrementQuestion();
                } else {
                    dialogFinish();
                    cdt.cancel();
                }
                break;
        }
    }
    Random rand = new Random();



    //wordAns
    public void incrementQuestion() {
        mCursor.moveToPosition(chAns);
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
    }

    public void Ansch(int index) {
        if (chAns == index) {
            //กรณีที่ตอบถูก
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
            twScore += 100;
            score.setText("" + twScore);
            mediaPlayer = MediaPlayer.create(this, R.raw.correct);
            mediaPlayer.start();
        } else {
//            Toast.makeText(this, "ผิด", Toast.LENGTH_SHORT).show();
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
        cdt = new CountDownTimer(t, 1000) {

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
                dialogFinish();
            }
        };
        cdt.start();
    }

    public void dialogFinish() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.finishgame);
        final_score = (TextView) dialog.findViewById(R.id.final_score);
        final_score.setText("" + twScore);

        dialog.show();
        dialogreplay = (Button) dialog.findViewById(R.id.btn_replay);
        dialogsummary = (Button) dialog.findViewById(R.id.btn_summary);
        dialoghome = (Button) dialog.findViewById(R.id.btn_home);

        //Button replay
        dialogreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCursor.moveToFirst();
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
                dialog.cancel();
                ball1.setClickable(false);
                ball2.setClickable(false);
                ball3.setClickable(false);
                chAns = 0;

                new CountDownTimer(1000, 50) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        twScore = 0;
                        score.setText("" + twScore);
                        ball1.setClickable(true);
                        ball2.setClickable(true);
                        ball3.setClickable(true);
                        countTime(100000);
                    }
                }.start();
            }
        });
        //Button summary
        final Intent g = new Intent(this, summary.class);
        dialogsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(g);
            }
        });
        dialoghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map1.class);
                startActivity(i);
            }
        });


    }

}
