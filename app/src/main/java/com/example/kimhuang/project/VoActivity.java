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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_vo);

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
        Time = (TextView) findViewById(R.id.Time);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        //decaler database
        mHelper = new database(this);
        mDb = mHelper.getWritableDatabase();
        mHelper.onUpgrade(mDb, 1, 1);

        //query word collect
        mCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);
        mCursor.moveToFirst();

        //query word incollect
        wCursor = mDb.rawQuery("SELECT * FROM " + mHelper.TableName, null);

        //query and set textview of Answer
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));

        //query ,set textvie and random row in table
        TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
        wCursor.moveToPosition(randWrong());
        TvSimple2.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));
        wCursor.moveToPosition(randWrong());
        TvSimple1.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));


        params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        //random position of ball
        randPosi2 = getRandomPosition();
        randPosi = getRandomPosition();
        randPosi3 = getRandomPosition();

        paramsBaseR = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        btnRigth.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnexplain.setOnClickListener(this);

        countTime(time);
        //random speed ball 10, 15, 20
        speed2 = randSpeed();
        speed1 = randSpeed();
        speed3 = randSpeed();
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
                paramsBaseR.setMargins(left, 1370, 0, 0);
                imgBase.setLayoutParams(paramsBaseR);
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
                countBefore.setVisibility(View.VISIBLE);
                countdownBefore();
                btnPlay.setVisibility(View.INVISIBLE);
                namegame.setVisibility(View.INVISIBLE);
                btnexplain.setVisibility(View.INVISIBLE);
                bar.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_explain:
                dialogEx();
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


                if (handler == null) {
                    handler = new Handler();
                }
                runnable = new Runnable() {
                    public void run() {

                        try {
                            dy += speed2;
                            dy1 += speed1;
                            dy2 += speed3;


                            if (layout2.getVisibility() == View.INVISIBLE) {
                                layout2.setVisibility(View.VISIBLE);
                            } else if (layout1.getVisibility() == View.INVISIBLE) {
                                layout1.setVisibility(View.VISIBLE);
                            } else if (layout3.getVisibility() == View.INVISIBLE) {
                                layout3.setVisibility(View.VISIBLE);
                            }

                            //ball2
                            params2.setMargins(randPosi2, dy, 0, 0);
                            layout2.setLayoutParams(params2);

                            //check collier collision
                            if ((imgBase.getTop() <= layout2.getTop() + layout2.getHeight())
                                    && (layout2.getTop() <= imgBase.getTop() + imgBase.getHeight())
                                    && (imgBase.getLeft() <= layout2.getLeft() + layout2.getWidth())
                                    && (layout2.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {
                                layout2.setVisibility(View.INVISIBLE);
                                dy = -500;

                                incrementScore();
//                                mDb.execSQL("UPDATE " + mHelper.TableName + " SET " + mHelper.ColStatus + " = 'uncorrent'"
//                                        + " WHERE " + mHelper.ColMean + " = '" + TvSimple2.getText().toString() + "' ");
                                mDb.execSQL("INSERT " + mHelper.ColStatus + " INTO " + mHelper.TableName + " VALUES ("
                                        + mHelper.ColStatus + " = 'uncorrent' WHERE "
                                        + mHelper.ColMean + " = '" + TvSimple2.getText().toString() + "'");

                                Log.e("TvSimple2 : " + TvSimple2.getText().toString(), "");

                                if (count < 4) {
                                    changeWord();
                                    count++;
                                } else {
                                    handler.removeCallbacks(runnable);
                                }

                                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColWord)));
                                TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));

                                randPosi2 = getRandomPosition();
                                params2.setMargins(randPosi2, dy, 0, 0);
                                speed2 = randSpeed();
                                Log.e("" + speed2, "");
                            }


                            //if ball move to azis y more than 1200 set invisible and start position -500 and random position, set speed and new word
                            if (dy >= 1200) {
                                layout2.setVisibility(View.INVISIBLE);
                                dy = -500;
                                randPosi2 = getRandomPosition();
                                speed2 = randSpeed();
                                TvSimple2.setText(mCursor.getString(mCursor.getColumnIndex(mHelper.ColMean)));
                            }

                            // finish ball2

                            //ball2
                            params.setMargins(randPosi, dy1, 0, 0);
                            layout1.setLayoutParams(params);

                            //check collier collision
                            if ((imgBase.getTop() <= layout1.getTop() + layout1.getHeight())
                                    && (layout1.getTop() <= imgBase.getTop() + imgBase.getHeight())
                                    && (imgBase.getLeft() <= layout1.getLeft() + layout1.getWidth())
                                    && (layout1.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {

                                layout1.setVisibility(View.INVISIBLE);
                                dy1 = -500;
//                                mDb.execSQL("UPDATE " + mHelper.TableName + " SET " + mHelper.ColStatus + " = 'corrent' WHERE "
//                                        + mHelper.ColMean + " = '" + TvSimple1.getText().toString() + "' ");

                                mDb.execSQL("INSERT " + mHelper.ColStatus + " INTO " + mHelper.TableName + " VALUES ("
                                        + mHelper.ColStatus + " = 'corrent' WHERE "
                                        + mHelper.ColMean + " = '" + TvSimple1.getText().toString() + "'");

                                randPosi = getRandomPosition();
                                params.setMargins(randPosi, dy, 0, 0);
                                speed1 = randSpeed();

                                wCursor.moveToPosition(randWrong());
                                TvSimple1.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));

                                if (params.topMargin == -500) {
                                    cdt.cancel();
                                    tempTime -= 3000;
                                    countTime(tempTime);
                                }
                            }

                            if (dy1 >= 1200) {
                                layout1.setVisibility(View.INVISIBLE);

                                dy1 = -500;
                                randPosi = getRandomPosition();

                                speed1 = randSpeed();
                                wCursor.moveToPosition(randWrong());
                                TvSimple1.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));
                            }

                            //finish

                            //ball2
                            params3.setMargins(randPosi3, dy2, 0, 0);
                            layout3.setLayoutParams(params3);


                            //check collier collision
                            if ((imgBase.getTop() <= layout3.getTop() + layout3.getHeight())
                                    && (layout3.getTop() <= imgBase.getTop() + imgBase.getHeight())
                                    && (imgBase.getLeft() <= layout3.getLeft() + layout3.getWidth())
                                    && (layout3.getLeft() <= imgBase.getLeft() + imgBase.getWidth())) {
                                layout1.setVisibility(View.INVISIBLE);
                                dy2 = -500;

//                                mDb.execSQL("UPDATE " + mHelper.TableName + " SET " + mHelper.ColStatus + " = 'uncorrent' WHERE "
//                                        + mHelper.ColMean + " = '" + TvSimple3.getText().toString() + "' ");

                                mDb.execSQL("INSERT " + mHelper.ColStatus + " INTO " + mHelper.TableName + " VALUES ("
                                        + mHelper.ColStatus + " = 'uncorrent' WHERE "
                                        + mHelper.ColMean + " = '" + TvSimple3.getText().toString() + "'");

                                randPosi3 = getRandomPosition();
                                params3.setMargins(randPosi3, dy2, 0, 0);
                                speed3 = randSpeed();

                                wCursor.moveToPosition(randWrong());
                                TvSimple3.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));

                                if (params3.topMargin == -500) {
                                    cdt.cancel();
                                    tempTime -= 3000;
                                    countTime(tempTime);
                                }
                            }

                            if (dy2 >= 1200) {
                                layout1.setVisibility(View.INVISIBLE);

                                dy2 = -500;
                                randPosi3 = getRandomPosition();

                                speed3 = randSpeed();
                                wCursor.moveToPosition(randWrong());
                                TvSimple3.setText(wCursor.getString(wCursor.getColumnIndex(mHelper.ColMean)));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.postDelayed(this, 35);
                    }
                };
                handler.postDelayed(runnable, 35);
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

        if (addScore >= 4) {
            addScore = 4;
            y_scrode.setText(String.valueOf(addScore));
            showFinalDialog();
        }
    }

    public int randSpeed() {
        int[] speed = {10, 15, 20};
        int rand = (int) (Math.random() * speed.length);
        return speed[rand];
    }

    //randdom uncorrect word

    public int randWrong() {
        int leg = wCursor.getCount();
        Random r = new Random();
        int i1 = r.nextInt(leg - 4) + 4;
        return i1;
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

}
