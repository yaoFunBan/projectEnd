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
    Cursor mCursor, wCursor;
    int dx = 0, dy = -100, dy1 = -100, dy2 = -100;
    int dxBefore = 0, addScore = 0;
    int left = 1000;
    int count;
    int speed2, speed1, speed3;

    int time = 50000, tempTime = 0;
    RelativeLayout.LayoutParams params, params2, params3, paramBaseL, paramsBaseR;
    RelativeLayout layout1, layout2, layout3, mainLayout;
    int randPosi, randPosi2, randPosi3;
    Handler handler;
    Runnable runnable;
    Integer[] shuffle = new Integer[20];
    static int index = 0;

    //fisher
    ArrayList<Integer> numRand = new ArrayList<Integer>();
    int[] randNum = new int[]
            {1, 2, 3, 4, 5
                    , 6, 7, 8, 9, 10
                    , 11, 12, 13, 14, 15
                    , 16, 17, 18, 19, 20
            };
    int[] keep = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vo);

        addList();

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

        //query word incollect
//        wCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);
//        wCursor.moveToFirst();

        //query and set textview of Answer
//        mCursor.moveToPosition(randWrong());
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));

        //query ,set textvie and random row in table
        TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//        mCursor.moveToPosition(randWrong());
        TvSimple3.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//        mCursor.moveToPosition(randWrong());
        TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));


//        params2 = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//        params3 = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);


//        btnRigth.setOnClickListener(this);
//        btnLeft.setOnClickListener(this);
//        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnexplain.setOnClickListener(this);

//        countTime(time);
    }


    //random position
    private int getRandomPosition() {
        float d = getApplicationContext().getResources().getDisplayMetrics().density;
        int[] posiLeft = {95, 255, 415, 575, 735, 895, 990};
        int num = (int) (Math.random() * posiLeft.length);
        return (int) (posiLeft[num] * d);
    }


    //countdown time 50s when time up set text score is 0 and show dialog showFinalDialog() stop habdler for stop fall down of every ball
    public void countTime(int t) {
        cdt = new CountDownTimer(t, 50) {

            @Override
            public void onTick(long millisUntilFinished) {

                tempTime = (int) millisUntilFinished;
                Time.setText(String.valueOf(tempTime));
                String strTime = String.format("%.1f"
                        , (double) millisUntilFinished / 1000);

                //when time reduction will set Time in textview
                Time.setText(String.valueOf(strTime));

            }

            @Override
            public void onFinish() {
                y_scrode.setText("0");
                showFinalDialog();
                handler.removeCallbacks(runnable);
            }
        };
        cdt.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pause:
                showPauseDialog();
                cdt.cancel();
                handler.removeCallbacks(runnable);
                break;
            case R.id.btn_back:
                // move bar to left if position of bar less than 320 set position is 160
                if (left <= 320) {
                    left = 160;
                } else {
                    left -= 250;
                }
                //set margin left for move bar turn left
//                paramsBaseR.setMargins(left, 1370, 0, 0);
//                imgBase.setLayoutParams(paramsBaseR);
                break;
            case R.id.btn_next:
                if (left >= 2000) {
                    left = 2150;
                } else {
                    left += 250;
                }
                //set margin left for move bar turn rigth
                paramsBaseR.setMargins(left, 1370, 0, 0);
                //set position in layout
                imgBase.setLayoutParams(paramsBaseR);
                break;
            case R.id.btn_play:
                int pos = randWord();
                mCursor.moveToPosition(pos);
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
//
//                //query ,set textvie and random row in table
//                TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                numRand.remove(pos);
//                Log.e("Size of ", " numRand " + numRand.size());
//                mCursor.moveToPosition(randWrong());
//                TvSimple3.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
//                mCursor.moveToPosition(randWrong());
//                TvSimple1.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));

//                Toast.makeText(getApplicationContext(), "" + mCursor.getCount(), Toast.LENGTH_SHORT).show();
//                Log.e("getcount ", "of : " + wCursor.getCount());
//                countBefore.setVisibility(View.VISIBLE);
//                countdownBefore();
//                btnPlay.setVisibility(View.INVISIBLE);
//                namegame.setVisibility(View.INVISIBLE);
//                btnexplain.setVisibility(View.INVISIBLE);
//                bar.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_explain:
                for (int i = 0; i < keep.length; i++) {
                    Log.e("Keep ", " of : " + keep[i]);
                }
//                dialogEx();
                break;
        }
    }

    //dialog Explain game
    public void dialogEx() {
        final Dialog exDialog = new Dialog(this);
        exDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        exDialog.setContentView(R.layout.loggame2);
        dialogclose = (Button) exDialog.findViewById(R.id.btn_close);

        //button_close
        dialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exDialog.cancel();
            }
        });
        exDialog.show();
    }


    // before start count down 3s
    public void countdownBefore() {
        new CountDownTimer(3000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

                countBefore.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                countBefore.setVisibility(View.INVISIBLE);
                btnLeft.setVisibility(View.VISIBLE);
                btnRigth.setVisibility(View.VISIBLE);
                imgBase.setVisibility(View.VISIBLE);
                y_scrode.setVisibility(View.VISIBLE);
                Time.setVisibility(View.VISIBLE);
                wordAns.setVisibility(View.VISIBLE);
                tScore.setVisibility(View.VISIBLE);
                pTime.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);


            }
        }.start();
    }


    public void changeWord() {
        mCursor.moveToNext();
    }


    // imcrement score addScore++ and setText
    public void incrementScore() {
        addScore++;
        y_scrode.setText(String.valueOf(addScore));

//        if (addScore >= 4) {
//            addScore = 4;
//            y_scrode.setText(String.valueOf(addScore));
//            showFinalDialog();
//        }
    }

    public int randSpeed() {
        int[] speed = {10, 15, 20};
        int rand = (int) (Math.random() * speed.length);
        return speed[rand];
    }

    //randdom uncorrect word

    public int randWrong() {
        int leg = mCursor.getCount();
        return (int) (Math.random() * leg);
    }


    public void showFinalDialog() {
        final Dialog fDialog = new Dialog(this);
        fDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fDialog.setContentView(R.layout.finishgame);

        TextView showScore = (TextView) fDialog.findViewById(R.id.final_score);
        Button btn_home = (Button) fDialog.findViewById(R.id.btn_home);
        Button btn_replay = (Button) fDialog.findViewById(R.id.btn_replay);

        showScore.setText(String.valueOf(addScore));

        btn_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
                countspawn();
                fDialog.cancel();
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map.class);
                startActivity(i);
            }
        });

        fDialog.show();
    }

    public void showPauseDialog() {
        final Dialog fDialog = new Dialog(this);
        fDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        fDialog.setContentView(R.layout.pausegame);


        Button btnCloase = (Button) fDialog.findViewById(R.id.btn_close);
        Button btnHome = (Button) fDialog.findViewById(R.id.btn_home);
        Button btnAgain = (Button) fDialog.findViewById(R.id.btn_again);

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
                countspawn();
                fDialog.cancel();
            }
        });

        btnCloase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fDialog.cancel();
                countTime(tempTime);
                handler.postDelayed(runnable, 35);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map.class);
                startActivity(i);
            }
        });

        fDialog.show();
    }

    public void countspawn() {
        new CountDownTimer(5000, 50) {
            @Override


            public void onTick(long millisUntilFinished) {

            }

            @Override


            public void onFinish() {
                countTime(50000);
                handler.postDelayed(runnable, 35);

                dy = -1000;
                randPosi2 = getRandomPosition();
                params2.setMargins(randPosi2, dy, 0, 0);
                mCursor.moveToFirst();
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
                TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
                speed2 = randSpeed();


                dy1 = -1000;
                randPosi = getRandomPosition();
                params.setMargins(randPosi, dy1, 0, 0);
                randPosi = getRandomPosition();
                speed1 = randSpeed();
                wCursor.moveToPosition(randWrong());
                TvSimple1.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));

                dy2 = -1000;
                randPosi3 = getRandomPosition();
                params.setMargins(randPosi3, dy2, 0, 0);
                speed3 = randSpeed();
                wCursor.moveToPosition(randWrong());
                TvSimple3.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));


            }


        }.start();

    }

    //fisher
    public void addList() {
        for (int i = 0; i < randNum.length; i++) {
            numRand.add(randNum[i]);
        }
    }

    Random rand = new Random();
    int pos;


    public int randWord() {
        if (numRand.size() != 0) {
            pos = rand.nextInt(numRand.size());
            keep[index] = numRand.get(pos);
            Log.e("Pos ", " is" + pos);
            Log.e("NumRand ", " get " + numRand.get(pos));
            index++;
            return pos;
        }
        return 0;
    }

    //end fisher


}
