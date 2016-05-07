package com.example.kimhuang.project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = VoActivity.class.getSimpleName();
    private TextView TvSimple3, TvSimple2, TvSimple1, wordAns, y_scrode, Time, tScore;
    ProgressBar pTime;
    ImageView namegame, bar;
    private ImageView imgBase;
    private Button btnLeft, btnRigth, btnPause, btnPlay, btnexplain, dialogclose;
    CountDownTimer cdt;
    TextView countBefore;
    SQLiteDatabase mDb;
    database mHelper;
    Cursor mCursor;
    int dx = 0, dy3 = -100, dy1 = -100, dy2 = -100;
    int dxBefore = 0, addScore = 0;
    int left = 1000;


    int time = 50000, tempTime = 0;
    RelativeLayout.LayoutParams params, params2, params3, paramBaseL, paramsBaseR;
    RelativeLayout layout1, layout2, layout3, mainLayout;
    int randPosi, randPosi2, randPosi3;
    Handler handler;
    Runnable runnable;
    static int index = 0;

    //word corect and incorect

    //fisher
    ArrayList<Integer> numAns = new ArrayList<Integer>();
    ArrayList<Integer> numUseWord = new ArrayList<Integer>();
    ArrayList<Integer> tempNumWord = new ArrayList<Integer>();

    //its use when add value in arrayList
    int[] randNum = new int[]
            {0, 1, 2, 3, 4, 5
                    , 6, 7, 8, 9, 10
                    , 11, 12, 13, 14, 15
                    , 16, 17, 18, 19
            };

    //Keep the words that were used
    int[] keep = new int[20];

    //keep Word is using
    static int temp1 = 0, temp2 = 0, temp3 = 0;
    static int val2 = 0;
    static int tPos = 0, tPos2 = 0, tPos3 = 0;
    int speed1 = 0, speed2 = 0, speed3 = 0;

    //position
    ArrayList<Integer> position = new ArrayList<Integer>();
    ArrayList<Integer> tempPosition = new ArrayList<Integer>();
    int[] randPos = new int[]{400, 600, 800, 1000, 1200, 1400, 1600};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vo);

        addList();
        setPosition();

        this.imgBase = (ImageView) findViewById(R.id.base);
        wordAns = (TextView) findViewById(R.id.quustion);
        pTime = (ProgressBar) findViewById(R.id.pbTimer);
        y_scrode = (TextView) findViewById(R.id.y_scrode);
        TvSimple2 = (TextView) findViewById(R.id.str2);
        TvSimple1 = (TextView) findViewById(R.id.str1);
        TvSimple3 = (TextView) findViewById(R.id.str3);
        this.btnLeft = (Button) findViewById(R.id.btn_back);
        this.btnRigth = (Button) findViewById(R.id.btn_next);
        countBefore = (TextView) findViewById(R.id.count_before);


        btnexplain = (Button) findViewById(R.id.btn_explain);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnexplain = (Button) findViewById(R.id.btn_explain);
        btnPlay = (Button) findViewById(R.id.btn_play);
        namegame = (ImageView) findViewById(R.id.namegame);
        bar = (ImageView) findViewById(R.id.bar);
        btnPause = (Button) findViewById(R.id.btn_pause);
        layout1 = (RelativeLayout) findViewById(R.id.ball1);
        layout2 = (RelativeLayout) findViewById(R.id.ball2);
        layout3 = (RelativeLayout) findViewById(R.id.ball3);
        tScore = (TextView) findViewById(R.id.count_score);
        Time = (TextView) findViewById(R.id.tvTimer);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        //decaler database
        mHelper = new database(this);
        mDb = mHelper.getWritableDatabase();
        mHelper.onUpgrade(mDb, 1, 1);

        //query word collect
        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);
        mCursor.moveToFirst();

        //query and set textview of Answer
        temp2 = randWordAns();
        mCursor.moveToPosition(numAns.get(temp2));
        pullQus(temp2);
        numAns.remove(temp2);
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
        //query ,set textvie and random row in table
        TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
        Log.e("Pos  " + index, " Vulse " + mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));

        //Ans 1
        temp1 = randWordInCorrect();
        mCursor.moveToPosition(temp1);
        pullQus(temp1);
        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));

        //Ans3
        temp3 = randWordInCorrect();
        mCursor.moveToPosition(temp3);
        pullQus(temp3);
        TvSimple3.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));


        btnRigth.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
//        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnexplain.setOnClickListener(this);


        params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        randPosi = getRandomPosition();
        tPos = keepPosition(randPosi);

        randPosi2 = getRandomPosition();
        tPos2 = keepPosition(randPosi2);

        randPosi3 = getRandomPosition();
        tPos3 = keepPosition(randPosi3);

        speed1 = randSpeed();
        speed2 = randSpeed();
        speed3 = randSpeed();
        moveBall();

//        countTime(time);


    }

    public void moveBall() {
        if (handler == null) {
            handler = new Handler();
        }
        runnable = new Runnable() {
            public void run() {

                try {
                    dy1 += speed1;
                    dy2 += speed2;
                    dy3 += speed3;

                    if (layout2.getVisibility() == View.INVISIBLE) {
                        layout2.setVisibility(View.VISIBLE);
                    } else if (layout1.getVisibility() == View.INVISIBLE) {
                        layout1.setVisibility(View.VISIBLE);
                    } else if (layout3.getVisibility() == View.INVISIBLE) {
                        layout3.setVisibility(View.VISIBLE);
                    }

                    //start ball 1

                    params.setMargins(randPos[randPosi], dy1, 0, 0);
                    layout1.setLayoutParams(params);

                    if (dy1 >= 1200) {
                        layout1.setVisibility(View.INVISIBLE);
                        reword(val2);
                        rePosition(tPos);

                        //position
                        randPosi = getRandomPosition();
                        keepPosition(randPosi);

                        temp1 = randWordInCorrect();
                        mCursor.moveToPosition(temp1);
                        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                        Log.e("Pos  dy 1 ", " Vulse " + mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
                        pullQus(temp1);
                        speed1 = randSpeed();
                        dy1 = -300;

                    }

                    //end ball1

                    //ball 2 and ans
                    params2.setMargins(randPos[randPosi2], dy2, 0, 0);
                    layout2.setLayoutParams(params2);
                    if (dy2 >= 1200) {
                        layout2.setVisibility(View.INVISIBLE);
                        reword(val2);
                        rePosition(tPos2);

                        //position
                        randPosi2 = getRandomPosition();
                        keepPosition(randPosi2);

                        temp2 = randWordAns();
                        mCursor.moveToPosition(numAns.get(temp2));
//                        Log.e("Pos  " + numAns.get(temp2), " Vulse " + mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
                        TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
                        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
                        pullQus(temp2);
                        numAns.remove(temp2);
                        speed2 = randSpeed();
                        dy2 = -300;
                    }

                    //end ball 2 and ans


                    params3.setMargins(randPos[randPosi3], dy3, 0, 0);
                    layout3.setLayoutParams(params3);

                    if (dy3 >= 1200) {
                        layout3.setVisibility(View.INVISIBLE);
                        reword(val2);
                        rePosition(tPos3);

                        //position
                        randPosi3 = getRandomPosition();
                        Log.e("rand position", " 3 : " + randPosi3);
//                        keepPosition(randPosi3);

                        temp3 = randWordInCorrect();
                        mCursor.moveToPosition(temp3);
                        TvSimple3.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
                        pullQus(temp3);
                        speed3 = randSpeed();
                        dy3 = -300;

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 35);
            }
        };
        handler.postDelayed(runnable, 35);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause:
                cdt.cancel();
                handler.removeCallbacks(runnable);
                break;
            case R.id.btn_back:
                // move bar to left if po
                // sition of bar less than 320 set position is 160
                if (left <= 320) {
                    left = 160;
                } else {
                    left -= 250;
                }
                paramsBaseR.setMargins(left, 1370, 0, 0);
                imgBase.setLayoutParams(paramsBaseR);
//                reword(temp2);
                Log.e("size of", "temp 2 " + tempNumWord.size());
                break;
            case R.id.btn_next:
                if (left >= 2000) {
                    left = 2150;
                } else {
                    left += 250;
                }
                paramsBaseR.setMargins(left, 1370, 0, 0);
                imgBase.setLayoutParams(paramsBaseR);
//                reword(temp3);
                break;
            case R.id.btn_play:
                for (int i = 0; i < numUseWord.size(); i++) {
                    Log.e("numUseWord", " is " + numUseWord.get(i));
                }
                break;

            case R.id.btn_explain:
//                reword(temp);
                for (int i = 0; i < tempNumWord.size(); i++) {
                    Log.e("tempNumWord", " is " + tempNumWord.get(i));
                }
//                dialogEx();
                break;
        }
    }

    public int randSpeed() {
        int[] speed = {10, 15, 20};
        int rand = (int) (Math.random() * speed.length);
        return speed[rand];
    }

    //fisher
    public void addList() {
        // loop add all row of database in variable numRand is type Arraylist<Integer>
        for (int i = 0; i < randNum.length; i++) {
            numAns.add(randNum[i]);
            numUseWord.add(randNum[i]);
        }
    }

    Random rand = new Random();
    int pos;

    //random word
    public int randWordAns() {
        //check numRand reduce less than 0 ?
        //If = no
        //rand row from 0 - max of numRand and keep value in pos;

        if (numAns.size() != 0) {
            pos = rand.nextInt(numAns.size());
            //keep[index] get value index = pos of numAns
            keep[index] = numAns.get(pos);
//            Log.e("Size " + pos, " of numAns" + numAns.size());
            index++;
            return pos;

        }
        return 0;
    }

    public int randWordInCorrect() {
        int r = 0;
        r = rand.nextInt(numUseWord.size());
        return r;
    }

    //pull question in arraylist tempNUmWord and remove value into numUseWord[temp]
    public void pullQus(int temp) {
        val2 = numUseWord.get(temp);
        tempNumWord.add(val2);
//        Log.e("Use ", "Value : " + numUseWord.get(temp));
//        Log.e("word ", " Vulse" + numUseWord.get(temp));
        numUseWord.remove(temp);
//        Log.e("===== Function ", " pullQus");
//        Log.e("Temp ", "temp : " + tempNumWord.size());
//        Log.e("Use ", "Size : " + numUseWord.size());

    }

    //push question out tempNumWord
    public void reword(int index) {
        numUseWord.add(randNum[index]);
        int del = tempNumWord.indexOf(randNum[index]);

        tempNumWord.remove(del);
//        Log.e("===== Function ", " reword");
//        Log.e("Temp ", "Index : " + del);
//        Log.e("Temp ", "Size : " + tempNumWord.size());
//        Log.e("Use ", "Size : " + numUseWord.size());
//        Log.e("Use ", "Value : " + randNum[index]);
    }


    //end random word

    //rand position
    //set postiont
    public void setPosition() {
        for (int i = 0; i < randPos.length; i++) {
            position.add(randPos[i]);
        }
    }

    public int getRandomPosition() {
        int r = rand.nextInt(position.size());
        return r;
    }

    public int keepPosition(int pos) {
        int tPos = position.get(pos);
        tempPosition.add(tPos);
        Log.e("keepPosition ", "==============");
        Log.e("Size ", " of tempposition " + tempPosition.size());
        Log.e("Size ", " of position " + position.size());
        position.remove(pos);
        return tPos;
    }

    public void rePosition(int val) {
        int del = tempPosition.indexOf(randPos[val]);
        position.add(tempPosition.get(val));
        Log.e("rePosition ", "==============");
        Log.e("Size ", " of position " + position.size());
        Log.e("Size ", " of tempposition " + tempPosition.size());
        tempPosition.remove(del);

    }

}
