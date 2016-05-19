package com.example.kimhuang.project;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class game3 extends AppCompatActivity {
    private TextView tvTimer, wordQue, ansTrue, ansFalse;
    TextView Score, final_score;
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    CountDownTimer cdt;
    ImageView Picture, mark;

    //เช็คค่าไม่ให้เกิน
    static int called = 0;

    //random
    List<Integer> random = new ArrayList<Integer>();

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialoghome, dialogsetting, dialogclose, dialogreplay, dialogsummary;
    RelativeLayout box1, box2;

    //position ที่ F
    String[] posiLeft = {"20,1250", "1250,20"};
    int tempWordRand;

    //Database
    SQLiteDatabase gameDb;
    dataidioms game3;
    Cursor wCursor;
    static int i = 0;
    int count = 0;

    //time
    int time = 50000, tempTime = 0;
    String[] randPos;

    //random
    int randPosi;
    RelativeLayout.LayoutParams params1, params2, paramsBaseR;

    //time
    int twscore = 0;

    //เสียง
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game3);


        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordQue = (TextView) findViewById(R.id.quustion);
        ansTrue = (TextView) findViewById(R.id.AnsTrue);
        ansFalse = (TextView) findViewById(R.id.AnsFalse);
        Picture = (ImageView) findViewById(R.id.picture);
        box1 = (RelativeLayout) findViewById(R.id.boxmess1);
        box2 = (RelativeLayout) findViewById(R.id.boxmess2);
        Score = (TextView) findViewById(R.id.score);
//        mark = (ImageView) findViewById(R.id.mark);

        //decaler database
        game3 = new dataidioms(this);
        gameDb = game3.getWritableDatabase();
        game3.onUpgrade(gameDb, 1, 1);

        //เป็นการอ่านค่าในตาราง database ว่าจะให้อ่านค่าเป็นคอลัมถ์ไปเรื่อยๆ
        wCursor = gameDb.rawQuery("SELECT * FROM " + game3.TableName, null);
        Addarray();
        wCursor.moveToFirst();
        tempWordRand = randomInStorage();
        wCursor.moveToPosition(tempWordRand);

        //ให้ cursor ชี้ไปที่ สำนวน ความหมายถูก รูปภาพ
        wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
        ansTrue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
        ansFalse.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
        Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));


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

        // กดคำตอบถูก
        box1 = (RelativeLayout) findViewById(R.id.boxmess1);
        ansTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //random index ใน array randPosi
                if (called != 15) {
                    randPosi = getRandomPosition();
                    randBox(randPosi);
                    tempWordRand = randomInStorage();
                    wCursor.moveToPosition(tempWordRand);
                    wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
                    ansTrue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
                    ansFalse.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
                    Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));
                    twscore += 100;
                    Score.setText("" + twscore);
                    mediaPlayer = MediaPlayer.create(game3.this, R.raw.correct);
                    mediaPlayer.start();
                } else {
                    dialogfinish();
                    cdt.cancel();
                }
            }
        });

        // กดคำตอบผิด
        ansFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (called != 15) {
                    randPosi = getRandomPosition();
                    randBox(randPosi);
                    tempWordRand = randomInStorage();
                    wCursor.moveToPosition(tempWordRand);
                    wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
                    ansTrue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
                    ansFalse.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
                    Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));
                    cdt.cancel();
                    tempTime -= 5000;
                    countTime(tempTime);
                    mediaPlayer = MediaPlayer.create(game3.this, R.raw.wrong);
                    mediaPlayer.start();
                } else {
                    dialogfinish();
                    cdt.cancel();
                }
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
                cdt.cancel();
                dialog.setContentView(R.layout.pausegame);
                //TODO findViewBy

                dialoghome = (Button) dialog.findViewById(R.id.btn_home);
                dialogsetting = (Button) dialog.findViewById(R.id.btn_setting);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);

                //button_home
                dialoghome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), map3.class);
                        startActivity(i);
                    }
                });


                //button_again
//                dialogagain.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        wCursor.moveToFirst();
//                        tempWordRand = randomInStorage();
//                        wCursor.moveToPosition(tempWordRand);
//                        wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
//                        ansTrue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
//                        ansFalse.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
//                        Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));
////                        ansTrue.setClickable(false);
//                        dialog.cancel();
//                        new CountDownTimer(1000, 50) {
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                twscore = 0;
//                                Score.setText("" + twscore);
//                                countTime(100000);
//                                random.clear();
//                                Addarray();
//                                randomInStorage();
//                            }
//                        }.start();
//                    }
//                });

                //button_setting
                dialogsetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogSetting setting = new DialogSetting(game3.this);
                        setting.show();

                        Window window = setting.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        window.setGravity(Gravity.CENTER);
                    }
                });


                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        countTime(tempTime);
                    }
                });
                dialog.show();
            }
        });
    }

    // random ตำแหน่ง
    Random rand = new Random();
//    String[] posiLeft = {"20,1250", "1250,20"};
    public int getRandomPosition() {
        int r = rand.nextInt(posiLeft.length);
        return r;
    }
    //ตัด string แล้วเก็บใน array
    //20,1025
//    String[] posiLeft = {"20,1250", "1250,20"};
    public void randBox(int rand) {
        //ranPos[0] = 20
        //ranPos[1] = 1250
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
                dialogfinish();
            }
        };
        cdt.start();
    }
    //dialog สรุปคะแนน ขึ้นมาหลังเวลาหมดหรือเล่นเกมจบ
    public void dialogfinish() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false); //แตะแล้วไม่ออกจากหน้าจอ
        dialog.setContentView(R.layout.finishgame);
        final_score = (TextView) dialog.findViewById(R.id.final_score);
        final_score.setText("" + twscore);
        dialog.show();

        dialogreplay = (Button) dialog.findViewById(R.id.btn_replay);
        dialogsummary = (Button) dialog.findViewById(R.id.btn_summary);
        dialoghome = (Button) dialog.findViewById(R.id.btn_home);

        //Button replay
        dialogreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                wCursor.moveToFirst();
                wordQue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLIdiom)));
                ansTrue.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesTrue)));
                ansFalse.setText(wCursor.getString(wCursor.getColumnIndex(game3.CoLMesFalse)));
                Picture.setBackgroundResource(wCursor.getInt(wCursor.getColumnIndex(game3.CoLPicture)));
//                        ansTrue.setClickable(false);
                dialog.cancel();
                new CountDownTimer(1000, 50) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        twscore = 0;
                        Score.setText("" + twscore);
                        countTime(100000);
                        random.clear();
                        Addarray();
                        randomInStorage();
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
                Intent i = new Intent(getApplicationContext(), map3.class);
                startActivity(i);
            }
        });
    }

    //เพิ่มสมาชิกให้กับอาร์เรย์ลิสจากข้อมูลในดาต้าเบสทั้งหมด
    public void Addarray() {
        for (int i = 0; i < wCursor.getCount(); i++) {
            random.add(i);
        }
    }

    //แรนด้อมแถวในดาต้าเบส
    public int randomInStorage() {
        rand = new Random();
        called += 1;
        int n;
        n = rand.nextInt(random.size()); //แรนด้อมตั้งแต่ 0 หรือ 14
//        random.remove(n);
        int val = random.get(n);
        random.remove(random.indexOf(val));
        Log.d("Size", "" + random.size());
        return val;

    }
}
