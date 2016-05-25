package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class VoActivity extends Activity implements View.OnClickListener {
    private static final String TAG = VoActivity.class.getSimpleName();
    private TextView TvSimple3, TvSimple2, TvSimple1, wordAns, count_score, tvTimer;
    ProgressBar pTime;
    ImageView namegame;
    private ImageView imgBase;
    private Button btnLeft, btnRigth, btnPause, btnPlay, btnexplain, dialogclose, dialogsetting, btnClose;
    TextView countBefore;
    ToggleButton swMusic, swEffect;
    SQLiteDatabase mDb;
    CountDownTimer ctd;
    database mHelper;
    Cursor mCursor;
    int dx = 0, dy3 = -100, dy1 = -100, dy2 = -100;
    int dxBefore = 0, addScore = 0;
    int left = 1000;
    int score = 0;
    int time = 50000, tempTime = 0;
    RelativeLayout.LayoutParams params, params2, params3, paramBaseL, paramsBaseR;
    RelativeLayout layout1, layout2, layout3, mainLayout, Rbar, RelTime, tabScore;
    int randPosi, randPosi2, randPosi3;
    Handler handler;
    Runnable runnable;
    static int index = 0;

    //word corect and incorect
    static int val2 = 0;
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
    boolean x;
    //keep Word is using
    static int temp1 = 0, temp2 = 0, temp3 = 0;
    static int tPos1 = 0, tPos2 = 0, tPos3 = 0;
    int speed1 = 0, speed2 = 0, speed3 = 0;
    static int word1 = 0, word2 = 0, word3 = 0;

    //position
    ArrayList<Integer> position = new ArrayList<Integer>();
    ArrayList<Integer> tempPosition = new ArrayList<Integer>();
    int[] randPos = new int[]{150, 500, 850, 1200, 1550, 1900};
    int pass = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vo);

        addList();
        setPosition();
        initialViews();

        //decaler database
        mHelper = new database(this);
        mDb = mHelper.getWritableDatabase();
        mHelper.onUpgrade(mDb, 1, 1);

        //query word collect
        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);
//        mCursor.moveToFirst();

        btnRigth.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnexplain.setOnClickListener(this);

        paramsBaseR = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

//      countTime(time);


    }

    private void initialViews() {
        this.imgBase = (ImageView) findViewById(R.id.base);
        wordAns = (TextView) findViewById(R.id.quustion);
        pTime = (ProgressBar) findViewById(R.id.pbTimer);
        TvSimple2 = (TextView) findViewById(R.id.str2);
        TvSimple1 = (TextView) findViewById(R.id.str1);
        TvSimple3 = (TextView) findViewById(R.id.str3);
        this.btnLeft = (Button) findViewById(R.id.btn_back);
        this.btnRigth = (Button) findViewById(R.id.btn_next);
        countBefore = (TextView) findViewById(R.id.count_before);
        count_score = (TextView) findViewById(R.id.count_score);
        btnexplain = (Button) findViewById(R.id.btn_explain);
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnexplain = (Button) findViewById(R.id.btn_explain);
        btnPlay = (Button) findViewById(R.id.btn_play);
        namegame = (ImageView) findViewById(R.id.namegame);
        btnPause = (Button) findViewById(R.id.btn_pause);
        layout1 = (RelativeLayout) findViewById(R.id.ball1);
        layout2 = (RelativeLayout) findViewById(R.id.ball2);
        layout3 = (RelativeLayout) findViewById(R.id.ball3);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        Rbar = (RelativeLayout) findViewById(R.id.bar);
        RelTime = (RelativeLayout) findViewById(R.id.RelTime);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tabScore = (RelativeLayout) findViewById(R.id.tabScore);

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

//                    params.setMargins(randPosi, dy1, 0, 0);
//                    layout1.setLayoutParams(params);
//                    if ((imgBase.getTop() <= layout1.getTop() + layout1.getHeight())
//                            && (layout1.getTop() <= imgBase.getTop() + imgBase.getHeight())
//                            && (imgBase.getLeft() <= layout1.getLeft() + layout1.getWidth())
//                            && (layout1.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {
//
//                        layout1.setVisibility(View.INVISIBLE);
//                        rePosition(tPos1);
//                        reword(temp1);
//                        dy1 = -500;
//
//                        //position
//                        randPosi = getRandomPosition();
//                        tPos1 = keepPosition(randPosi);
//
//                        temp1 = randWordInCorrect();
//                        mCursor.moveToPosition(temp1);
//                        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                        pullQus(temp1);
//                        speed1 = randSpeed();
//                        ctd.cancel();
//                        tempTime -= 3000;
//                        countTime(tempTime);
//
//                    }

//                    if (dy1 >= 1200) {
//                        layout1.setVisibility(View.INVISIBLE);
//                        reword(temp1);
//                        rePosition(tPos1);
//                        //position
//                        randPosi = getRandomPosition();
//                        tPos1 = keepPosition(randPosi);
//
//                        temp1 = randWordInCorrect();
//                        mCursor.moveToPosition(temp1);
//                        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                        pullQus(temp1);
//                        speed1 = randSpeed();
//                        dy1 = -300;
//                    }

                    //end ball1

                    //ball 2 and ans
                    params2.setMargins(randPosi2, dy2, 0, 0);
                    layout2.setLayoutParams(params2);

//                    if ((imgBase.getTop() <= layout2.getTop() + layout2.getHeight())
//                            && (layout2.getTop() <= imgBase.getTop() + imgBase.getHeight())
//                            && (imgBase.getLeft() <= layout2.getLeft() + layout2.getWidth())
//                            && (layout2.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {
//                        layout2.setVisibility(View.INVISIBLE);
//                        rePosition(tPos2);
//
//                        randPosi2 = getRandomPosition();
//                        tPos2 = keepPosition(randPosi2);
//
//
//                        speed2 = randSpeed();
//                        x = true;
//                        dy2 = -600;
//
//                    }

                    if (dy2 >= 1200) {
                        layout2.setVisibility(View.INVISIBLE);
                        rePosition(tPos2);
                        //position
                        reword(temp2);

                        randPosi2 = getRandomPosition();
                        tPos2 = keepPosition(randPosi2);

                        temp2 = randWordAns();
                        mCursor.moveToPosition(temp2);

                        wordAns.setText(mCursor.getString(0));
                        TvSimple2.setText(mCursor.getString(1));
                        pullQus(temp2);

                        speed2 = randSpeed();
                        dy2 = -300;
                    }

//                    //end ball 2 and ans


                    params3.setMargins(randPosi3, dy3, 0, 0);
                    layout3.setLayoutParams(params3);

//                    if ((imgBase.getTop() <= layout3.getTop() + layout3.getHeight())
//                            && (layout3.getTop() <= imgBase.getTop() + imgBase.getHeight())
//                            && (imgBase.getLeft() <= layout3.getLeft() + layout3.getWidth())
//                            && (layout3.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {
//                        dy3 = -600;
//                        layout3.setVisibility(View.INVISIBLE);
//                        reword(temp3);
//                        rePosition(tPos3);
////                        x = true;
//                        //position
//                        randPosi3 = getRandomPosition();
//                        tPos3 = keepPosition(randPosi3);
//
//                        temp3 = randWordInCorrect();
//                        mCursor.moveToPosition(temp3);
//                        TvSimple3.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                        pullQus(temp3);
//                        speed3 = randSpeed();
//
//                        ctd.cancel();
//                        tempTime -= 3000;
//                        countTime(tempTime);
//                    }


                    if (dy3 >= 1200) {
                        layout3.setVisibility(View.INVISIBLE);
                        reword(temp3);
                        rePosition(tPos3);

                        //position
                        randPosi3 = getRandomPosition();
                        tPos3 = keepPosition(randPosi3);

                        temp3 = randWordInCorrect();
                        mCursor.moveToPosition(temp3);
                        TvSimple3.setText(mCursor.getString(1));
                        pullQus(temp3);
                        speed3 = randSpeed();
                        dy3 = -300;
                    }

//                    if (x && layout2.getVisibility() == View.VISIBLE) {
//                        incrementScore();
//                        x = false;
//                    }


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
                dialogPause();
                ctd.cancel();
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
                paramsBaseR.width = 450;
                paramsBaseR.height = 200;
                break;
            case R.id.btn_next:
                if (left >= 1960) {
                    left = 1960;
                } else {
                    left += 250;
                }

                paramsBaseR.setMargins(left, 1370, 0, 0);
                imgBase.setLayoutParams(paramsBaseR);
                paramsBaseR.width = 450;
                paramsBaseR.height = 200;
                break;
            case R.id.btn_play:
                //button being
                namegame.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.GONE);
                btnexplain.setVisibility(View.GONE);

                //in game
                Rbar.setVisibility(View.VISIBLE);
                btnLeft.setVisibility(View.VISIBLE);
                btnRigth.setVisibility(View.VISIBLE);
                imgBase.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                RelTime.setVisibility(View.VISIBLE);
                tabScore.setVisibility(View.VISIBLE);

                playGame();

                break;
            case R.id.btn_explain:
                dialogEx();
                break;
        }
    }

    public int randSpeed() {
        int[] speed = {10, 15, 20};
        int rand = (int) (Math.random() * speed.length);
        return speed[rand];
    }

    public void playGame() {
        //word
        //query and set textview of Answer
        temp2 = randWordAns();
        mCursor.moveToPosition(temp2);
        pullQus(temp2);
        wordAns.setText(mCursor.getString(0));
        //query ,set textvie and random row in table
        TvSimple2.setText(mCursor.getString(1));


//        countTime(100000);
//        //Ans 1
//        temp1 = randWordInCorrect();
//        mCursor.moveToPosition(temp1);
//        pullQus(temp1);
//        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//
////        Ans3
        temp3 = randWordInCorrect();
        mCursor.moveToPosition(temp3);
        pullQus(temp3);
        TvSimple3.setText(mCursor.getString(1));


        params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

//        params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

//        randPosi = getRandomPosition();
//        tPos1 = keepPosition(randPosi);

        randPosi2 = getRandomPosition();
        tPos2 = keepPosition(randPosi2);

        randPosi3 = getRandomPosition();
        tPos3 = keepPosition(randPosi3);

//        speed1 = randSpeed();
        speed2 = randSpeed();
        speed3 = randSpeed();
        moveBall();
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
    int valGet;

    public int randWordAns() {
        //check numRand reduce less than 0 ?
        //If = no
        //rand row from 0 - max of numRand and keep value in pos;

        if (numAns.size() != 0) {
            pos = rand.nextInt(numAns.size());
            //keep[index] get value index = pos of numAns
            valGet = numAns.get(pos);
            numAns.remove(pos);
            Log.e("============= round " + index, "=============" + valGet);
            Log.e("numAns : ", " is " + numAns);

        }
        return valGet;
    }

    public int randWordInCorrect() {
        int r = 0;
        r = rand.nextInt(numUseWord.size());
        return r;
    }


    //pull question in arraylist tempNUmWord and remove value into numUseWord[temp]
    public void pullQus(int temp) {
        tempNumWord.add(temp);
        numUseWord.remove(numUseWord.indexOf(temp));
        Log.e("numUseWord : ", " is " + numUseWord);
        Log.e("tempNumWord : ", " is " + tempNumWord);
    }

    //push question out tempNumWord
    public void reword(int index) {
        int del = tempNumWord.indexOf(index);
        numUseWord.add(index);
        tempNumWord.remove(del);
    }

    //end random word


    public void incrementScore() {
        score += 100;
        count_score.setText("" + score);
//        reword(word2);
        reword(pass);

        pass++;
//        temp2 = randWordAns();
//        mCursor.moveToPosition(numAns.get(temp2));
        mCursor.moveToPosition(pass);
        pullQus(pass);
        wordAns.setText(mCursor.getString(0));
        TvSimple2.setText(mCursor.getString(1));


//        numAns.remove(numAns.indexOf(temp2));


    }

    //rand position
    //set postiont
    public void setPosition() {
        for (int i = 0; i < randPos.length; i++) {
            position.add(randPos[i]);
        }
    }

    public int getRandomPosition() {
        int r = rand.nextInt(position.size());
        return position.get(r);
    }

    public int keepPosition(int pos) {
        tempPosition.add(pos);
        position.remove(position.indexOf(pos));
        return pos;
    }

    public void rePosition(int val) {
        int del = tempPosition.indexOf(val);
        position.add(tempPosition.get(del));
        tempPosition.remove(del);
    }


    //CountDownTimer (โดยจะลดลงครั้งละ 1 วินาที)
    public void countTime(int t) {
        ctd = new CountDownTimer(t, 50) {

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

                handler.removeCallbacks(runnable);

            }
        };
        ctd.start();
    }


    public void finishDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.finishgame);
        dialog.setCancelable(false);

        TextView tx = (TextView) dialog.findViewById(R.id.final_score);
        Button btnReplay = (Button) dialog.findViewById(R.id.btn_replay);
        Button btnMap = (Button) dialog.findViewById(R.id.btn_home);
        Button btnSummary = (Button) dialog.findViewById(R.id.btn_summary);

        tx.setText("" + score);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map2.class);
                startActivity(i);
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), summary.class);
                startActivity(i);
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                dy1 -= 600;
                dy2 -= 500;
                dy3 -= 400;
                countTime(5000);

                params.setMargins(randPosi, dy1, 0, 0);
                layout1.setLayoutParams(params);

                params2.setMargins(randPosi, dy2, 0, 0);
                layout2.setLayoutParams(params);

                params3.setMargins(randPosi, dy3, 0, 0);
                layout3.setLayoutParams(params3);

                score = 0;
                pass = 0;
                mCursor.moveToFirst();
                TvSimple2.setText(mCursor.getString(0));
                wordAns.setText(mCursor.getString(1));
                count_score.setText("" + score);
                handler.postDelayed(runnable, 35);

            }
        });

        dialog.show();
    }

    public void dialogEx() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ecgame2);
        dialog.setCancelable(false);


        Button closeBtn = (Button) dialog.findViewById(R.id.btn_close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();
    }


    public void dialogPause() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pausegame);
        dialog.setCancelable(false);
        ctd.cancel();

        Button dialoghome = (Button) dialog.findViewById(R.id.btn_home);
//        Button agian = (Button) dialog.findViewById(R.id.btn_again);
        Button setting = (Button) dialog.findViewById(R.id.btn_setting);

        dialogclose = (Button) dialog.findViewById(R.id.btn_close);

        //button_home
        dialoghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map2.class);
                startActivity(i);
            }
        });

        //button_setting
        dialogsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSetting setting = new DialogSetting(VoActivity.this);
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
                handler.postDelayed(runnable, 35);
            }
        });
//        agian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//                dy1 -= 600;
//                dy2 -= 500;
//                dy3 -= 400;
//                countTime(5000);
//
//                params.setMargins(randPosi, dy1, 0, 0);
//                layout1.setLayoutParams(params);
//
//                params2.setMargins(randPosi, dy2, 0, 0);
//                layout2.setLayoutParams(params);
//
//                params3.setMargins(randPosi, dy3, 0, 0);
//                layout3.setLayoutParams(params3);
//
//                score = 0;
//                pass = 0;
//                mCursor.moveToFirst();
//                TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
//                count_score.setText("" + score);
//                handler.postDelayed(runnable, 35);
//            }
//        });
        dialog.show();
    }


}



