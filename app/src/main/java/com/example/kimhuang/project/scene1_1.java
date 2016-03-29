package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class scene1_1 extends AppCompatActivity {
    //ImageView
    ImageView jantawee1, janta1, yotsawimon1, chair, box1_1;
    ImageView word1, word2, word3;
    //Button
    Button btn_back, btn_next, btn_pause;
    //boolean
    boolean jantawee = false;
    boolean yotsawimon = false;
    boolean janta = false;
    boolean flagjantawee, flagjanta, flagyotsawimon;
    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    //etc
    MediaPlayer mediaPlayer;
    AnimPopUp animPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenc1_1);

        //animpopup
        animPopUp = new AnimPopUp();

        //chair
        chair = (ImageView) findViewById(R.id.chair);
        animPopUp.PlayAnimation(chair);

        //box
        box1_1 = (ImageView) findViewById(R.id.box1_1);
        animPopUp.PlayAnimation(box1_1);

        //Word
        word1 = (ImageView) findViewById(R.id.word1);
        word2 = (ImageView) findViewById(R.id.word2);
        word3 = (ImageView) findViewById(R.id.word3);

        //jantawee
        jantawee1 = (ImageView) findViewById(R.id.jantawee1);
        animPopUp.PlayAnimation(jantawee1);
        jantawee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagjantawee = true;
                checkDown();
                try {

                    if (jantawee == false) {
                        ((AnimationDrawable) jantawee1.getBackground()).stop();
                        jantawee = true;
                        //change image view
                        jantawee1.setBackgroundResource(R.drawable.jantawee1);
                        word1.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.jantawee);
                        mediaPlayer.start();
                    } else {
                        jantawee = false;
                        stopPlaying();
                        jantawee1.setBackgroundResource(R.drawable.animate_jantawee1_1);
                        word1.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) jantawee1.getBackground()).start();
                    }

                } catch (Exception e) {
                }
            }
        });

        //yotsawimon
        yotsawimon1 = (ImageView) findViewById(R.id.yotsawimon1);
        animPopUp.PlayAnimation(yotsawimon1);
        yotsawimon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagyotsawimon = true;
                checkDown();
                try {
                    if (yotsawimon == false) {
                        ((AnimationDrawable) yotsawimon1.getBackground()).stop();
                        yotsawimon = true;
                        //change image view
                        yotsawimon1.setBackgroundResource(R.drawable.yotsawimon1);
                        word2.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.yotsuwimon);
                        mediaPlayer.start();
                    } else {
                        yotsawimon = false;
                        stopPlaying();
                        yotsawimon1.setBackgroundResource(R.drawable.animate_yotsawimon1_1);
                        word2.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) yotsawimon1.getBackground()).start();
                    }
                } catch (Exception e) {
                }
            }
        });

        //janta
        janta1 = (ImageView) findViewById(R.id.janta1);
        animPopUp.PlayAnimation(janta1);
        janta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagjanta = true;
                checkDown();
                try {

                    if (janta == false) {
                        ((AnimationDrawable) janta1.getBackground()).stop();
                        janta = true;
                        //change image view
                        janta1.setBackgroundResource(R.drawable.janta1);
                        word3.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.janta);
                        mediaPlayer.start();
                    } else {
                        janta = false;
                        stopPlaying();
                        janta1.setBackgroundResource(R.drawable.animate_janta1_1);
                        word3.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) janta1.getBackground()).start();
                    }
                } catch (Exception e) {
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
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
                    }
                });

                //button_setting
                dialogset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null)
                            dialog.dismiss();
                    }
                });

                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null)
                            dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        //button_back
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //button_next
        btn_next = (Button) findViewById(R.id.btn_next);
        final Intent i = new Intent(this, page1_2.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }

    //checkDown ว่ากดปุ่มครบหมดไหมถึงสามารถไปหน้าอื่นได้
    public void checkDown() {
        if (flagjantawee == true && flagjanta == true && flagyotsawimon == true) {
            btn_next.setVisibility(View.VISIBLE);
            btn_back.setVisibility(View.VISIBLE);
        }
    }

    //ให้อนิเมชันเริ่มหลังจากที่ popup ขึ้นมาแล้ว
    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((AnimationDrawable) jantawee1.getBackground()).start();
                ((AnimationDrawable) janta1.getBackground()).start();
                ((AnimationDrawable) yotsawimon1.getBackground()).start();
            }
        }.start();
    }

    public void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        jantawee1.setBackgroundResource(R.drawable.animate_jantawee1_1);
        yotsawimon1.setBackgroundResource(R.drawable.animate_yotsawimon1_1);
        janta1.setBackgroundResource(R.drawable.animate_janta1_1);
        ((AnimationDrawable) jantawee1.getBackground()).start();
        ((AnimationDrawable) janta1.getBackground()).start();
        ((AnimationDrawable) yotsawimon1.getBackground()).start();
        word1.setVisibility(View.INVISIBLE);
        word2.setVisibility(View.INVISIBLE);
        word3.setVisibility(View.INVISIBLE);
    }
}

