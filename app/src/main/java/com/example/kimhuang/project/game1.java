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
    CountDownTimer cdt;
    MediaPlayer mediaPlayer;

    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialoghome, dialogsetting, dialogagain, dialogclose, dialogsummary, dialogreplay;
    RelativeLayout ball1, ball2, ball3;

    //Databas
    SQLiteDatabase gameDb;
    datahomony game1;
    Cursor mCursor;
    static int i = 0;
    Random random = new Random();

    //time
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
        game1 = new datahomony(this);
        gameDb = game1.getWritableDatabase();
        game1.onUpgrade(gameDb, 1, 1);

        //READ DATA (เป็นการอ่านค่าในตาราง database โดยกำหนดให้ mCursor เลื่อนอ่านข้อมูลในแต่ละคอลัมไปเรื่อยๆ)
        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.TableName, null);
        addToList();

        //round1
        teamQuestion1 = randQuestion();
        //เมื่อทำการเรียกใช้คำก็จะทำการลบคำนั้นทิ้งไป
        cutWord(teamQuestion1);
        mCursor.moveToPosition(teamQuestion1);
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
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
                cAns = str2.getText().toString();
                chCorrect(cAns);
                setBall();
                break;
        }
    }

    public void setBall(){
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
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
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
    public void chCorrect(String word){
        mCursor.moveToPosition(teamQuestion1);
        String chWord = mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic));
        if(word.equals(chWord)){
            Toast.makeText(this, "true" ,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"false", Toast.LENGTH_SHORT).show();
        }
    }

    public void randAddBall() {
        int randBall = random.nextInt(3);
        if (randBall == 0) {
            str1.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[0] = true;
        } else if (randBall == 1) {
            str2.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[1] = true;
        } else if (randBall == 2) {
            str3.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[2] = true;
        }
    }


    //random ball of Ans
    public void textInAns() {
        if (chAns[0] == false) {
            str1.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[0] = true;
        } else if (chAns[1] == false) {
            str2.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[1] = true;
        } else if (chAns[2] == false) {
            str3.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
            chAns[2] = true;
        }
    }

    //เซตค่า chAns ให้กลับเป็น false ทั้งหมด
    public  void  resetChAns(){
        for (int i = 0 ; i< chAns.length; i++){
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

    }

    public int randAns() {
        int r = random.nextInt(listAns.size());
        return listAns.get(r);
    }

    public void keepWord(int teamWord) {
        teamRow.add(teamWord);
        int re = listAns.indexOf(teamWord);
        listAns.remove(re);
        Log.e("KeepWord ", " =======================");
        Log.e("val ", " of tempRow : " + teamRow);
        Log.e("val ", " of listAns : " + listAns);

    }

    public void reWord(int teamWord) {
        int keep = teamRow.indexOf(teamWord);
        listAns.add(teamWord);
        teamRow.remove(keep);
        Log.e("reWord ", "==========================");
        Log.e("val ", " of tempRow : " + teamRow);
        Log.e("val ", " of listAns : " + listAns);
    }


}

