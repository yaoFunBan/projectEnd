package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;
import java.util.Random;

public class game1 extends AppCompatActivity implements View.OnClickListener {
    private TextView wordAns, tvTimer, str1, str2, str3, score, final_score;
    Button btn_pause, btnClose;
    ToggleButton swMusic, swEffect;
    MediaPlayer mediaPlayer;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialoghome, dialogsetting, dialogagain, dialogclose, dialogsummary, dialogreplay;
    RelativeLayout ball1, ball2, ball3;

    //Databas
    SQLiteDatabase gameDb;
    dataFairy game1;
    Cursor mCursor;
    static int i = 0;
    Random random = new Random();

    //time
    int twscore = 0;
    int time = 5000, tempTime = 0;
    CountDownTimer cdt;
    RelativeLayout.LayoutParams params, params1, params2, paramsBaseR;

    //random
    int teamQuestion1, teamQuestion2, teamQuestion3;
    ArrayList<Integer> listRow, listAns, teamRow;
    boolean[] chAns = {false, false, false};
    String cAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);

        countTime(100000);

        Random random = new Random();
        //เก็บค่าจำนวนแถวทั้งหมดใน array
        listRow = new ArrayList<Integer>();
        //เก็บชอยด์ของคำตอบ
        listAns = new ArrayList<Integer>();
        teamRow = new ArrayList<Integer>();


        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordAns = (TextView) findViewById(R.id.quustion);
        str1 = (TextView) findViewById(R.id.str1);
        str2 = (TextView) findViewById(R.id.str2);
        str3 = (TextView) findViewById(R.id.str3);
        score = (TextView) findViewById(R.id.score);
        ball1 = (RelativeLayout) findViewById(R.id.ball1);
        ball2 = (RelativeLayout) findViewById(R.id.ball2);
        ball3 = (RelativeLayout) findViewById(R.id.ball3);
        btn_pause = (Button) findViewById(R.id.btn_pause);

        //decaler database
        game1 = new dataFairy(this);
        gameDb = game1.getWritableDatabase();
//        game1.onUpgrade(gameDb, 1, 1);

        //READ DATA (เป็นการอ่านค่าในตาราง database โดยกำหนดให้ mCursor เลื่อนอ่านข้อมูลในแต่ละคอลัมไปเรื่อยๆ)
        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.Table_Kaphong, null);
        addToList();

//        mCursor.moveToFirst();

        //round1
        teamQuestion1 = randQuestion();
        //เมื่อทำการเรียกใช้คำก็จะทำการลบคำนั้นทิ้งไป
        cutWord(teamQuestion1);
        mCursor.moveToPosition(teamQuestion1);
        wordAns.setText(mCursor.getString(0));
        randAddBall();

        keepWord(teamQuestion1);


        //round2
        teamQuestion2 = randAns();
        mCursor.moveToPosition(teamQuestion2);
        textInAns();
        keepWord(teamQuestion2);

        //round3
        teamQuestion3 = randAns();
        mCursor.moveToPosition(teamQuestion3);
        textInAns();
        keepWord(teamQuestion3);

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


        //event click (เรียกใช้ method onClick)
        ball1.setOnClickListener(this);
        ball2.setOnClickListener(this);
        ball3.setOnClickListener(this);


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
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
                    }
                });

                //button_setting
                dialogsetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogSetting setting = new DialogSetting(game1.this);
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

    //OnClick Ball
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (R.id.ball1):
                cAns = str1.getText().toString();
                chCorrect(cAns);
                setBall();
                break;
            case (R.id.ball2):
                cAns = str2.getText().toString();
                chCorrect(cAns);
                setBall();
                break;
            case (R.id.ball3):
                cAns = str3.getText().toString();
                chCorrect(cAns);
                setBall();
                break;
        }
    }

    Random rand = new Random();


    public void setBall() {
        //เซตให้ค่าของลูกบอลเป็น false คือไม่มีค่าอยู่
        resetChAns();
        //reword
        reWord(teamQuestion1);
        reWord(teamQuestion2);
        reWord(teamQuestion3);

        //round1
        teamQuestion1 = randQuestion();
        //เมื่อทำการเรียกใช้คำก็จะทำการลบคำนั้นทิ้งไป
        cutWord(teamQuestion1);
        mCursor.moveToPosition(teamQuestion1);
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.Col_Word_Kaphong)));
        randAddBall();
        keepWord(teamQuestion1);

        //round2
        teamQuestion2 = randAns();
        mCursor.moveToPosition(teamQuestion2);
        textInAns();
        keepWord(teamQuestion2);

        //round3
        teamQuestion3 = randAns();
        mCursor.moveToPosition(teamQuestion3);
        textInAns();
        keepWord(teamQuestion3);
    }

    //random คำถาม
    public int randQuestion() {
        int rand = random.nextInt(listRow.size());
        Log.e("val", "random" + listRow.get(rand));
        return listRow.get(rand);
    }

    //fucntion check Answer
    public void chCorrect(String word) {
        mCursor.moveToPosition(teamQuestion1);
        String chWord = mCursor.getString(1);
        Log.e("1.word : " + word, "teamQuestion1 : " + teamQuestion1);
        if (word.equals(chWord)) {
            twscore += 100;
            score.setText(" " + twscore);
            mediaPlayer = MediaPlayer.create(game1.this, R.raw.correct);
            mediaPlayer.start();
            Log.e("2.word : " + word, "chWord : " + chWord);
            long row = UpdateData(word, "correct");
            if (row > 0) {
                Log.e("Log ", "Update Data Successfully");
            } else {
                Log.e("Log ", "Update Data Failed");
            }
        } else {
            cdt.cancel();
            tempTime -= 5000;
            countTime(tempTime);
            mediaPlayer = MediaPlayer.create(game1.this, R.raw.wrong);
            mediaPlayer.start();
//            Toast.makeText(this,"false", Toast.LENGTH_SHORT).show();
        }
    }

    public void randAddBall() {
        int randBall = random.nextInt(3);
        if (randBall == 0) {
            str1.setText(mCursor.getString(1));
            chAns[0] = true;
        } else if (randBall == 1) {
            str2.setText(mCursor.getString(1));
            chAns[1] = true;
        } else if (randBall == 2) {
            str3.setText(mCursor.getString(1));
            chAns[2] = true;
        }
    }


    //random ball of Ans
    public void textInAns() {
        if (chAns[0] == false) {
            str1.setText(mCursor.getString(1));
            chAns[0] = true;
        } else if (chAns[1] == false) {
            str2.setText(mCursor.getString(1));
            chAns[1] = true;
        } else if (chAns[2] == false) {
            str3.setText(mCursor.getString(1));
            chAns[2] = true;
        }
    }

    //เซตค่า chAns ให้กลับเป็น false ทั้งหมด
    public void resetChAns() {
        for (int i = 0; i < chAns.length; i++) {
            chAns[i] = false;
        }
    }

    private void addToList() {
        for (int i = 0; i < mCursor.getCount(); i++) {
            //เป็นคำถาม
            listRow.add(i);
            //เป็นชอยท์ของลูกบอล(array)
            listAns.add(i);
        }
    }

    //ลบค่าที่ใช้แล้วทิ้ง
    public void cutWord(int word) {
        int index = listRow.indexOf(word);
        listRow.remove(index);
        Log.e("vale ", "is" + listRow);

//        if(teamRow == null ){
//            listRow.add(i);
//        }

    }

    public int randAns() {
        int r = random.nextInt(listAns.size());
        return listAns.get(r);
    }

    public void keepWord(int teamWord) {
        teamRow.add(teamWord);
        int re = listAns.indexOf(teamWord);
        listAns.remove(re);
//        Log.e("KeepWord ", " =======================");
//        Log.e("val ", " of tempRow : " + teamRow);
//        Log.e("val ", " of listAns : " + listAns);

    }

    public void reWord(int teamWord) {
        int keep = teamRow.indexOf(teamWord);
        listAns.add(teamWord);
        teamRow.remove(keep);
//        Log.e("reWord ", "==========================");
//        Log.e("val ", " of tempRow : " + teamRow);
//        Log.e("val ", " of listAns : " + listAns);
    }

    //CountDownTimer (โดยจะลดลงครั้งละ 1 วินาที)
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
                finishDialog();
            }
        };
        cdt.start();
    }

    private void finishDialog() {
    }

    public long UpdateData(String word, String status) {
        try {
            String where = game1.Col_Mean_Kaphong + " = '" + word + "' ";
            Log.e("2.word : " + word, "chWord : ");
            ContentValues cv = new ContentValues();
            cv.put("sKaphong", status);

            long row = gameDb.update(game1.Table_Kaphong, cv, where, null);

//            game1.close();
            return row;
        } catch (Exception e) {
            return -1;
        }
    }
}

