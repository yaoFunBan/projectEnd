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

public class scene1_2 extends AppCompatActivity {
    //ImageView
    ImageView oldmen1, oldwomen1, jantawee2, box1_2, trees1, trees2;
    ImageView word4, word5;
    //Button
    Button btn_back, btn_next, btn_pause;
    //boolean
    boolean oldwomen = false;
    boolean jantawee = false;
    boolean flagoldwomen, flagjantawee;
    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    //etc
    AnimPopUp animPopUp;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1_2);

        //animPopUp
        animPopUp = new AnimPopUp();

        //box1_2
        box1_2 = (ImageView) findViewById(R.id.box1_2);
        animPopUp.PlayAnimation(box1_2);

        //trees1
        trees1 = (ImageView) findViewById(R.id.trees1);
        animPopUp.PlayAnimation(trees1);

        //trees2
        trees2 = (ImageView) findViewById(R.id.trees2);
        animPopUp.PlayAnimation(trees2);

        //word4
        word4 = (ImageView) findViewById(R.id.word4);

        //word5
        word5 = (ImageView) findViewById(R.id.word5);

        //oldmen
        oldmen1 = (ImageView) findViewById(R.id.oldmen1);
        animPopUp.PlayAnimation(oldmen1);

        //oldwomen
        oldwomen1 = (ImageView) findViewById(R.id.oldwomen1);
        animPopUp.PlayAnimation(oldwomen1);
        oldwomen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagoldwomen = true;
                checkDown();
                try {

                    if (oldwomen == false) {
                        ((AnimationDrawable) oldwomen1.getBackground()).stop();
                        oldwomen = true;
                        //change image view
                        oldwomen1.setBackgroundResource(R.drawable.oldwomen1);
                        word4.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oldwomen);
                        mediaPlayer.start();
                    } else {
                        oldwomen = false;
                        stopPlaying();
                        oldwomen1.setBackgroundResource(R.drawable.animate_oldwomen);
                        word4.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) oldwomen1.getBackground()).start();
                    }

                } catch (Exception e) {
                }
            }
        });

        //jantawee
        jantawee2 = (ImageView) findViewById(R.id.jantawee2);
        animPopUp.PlayAnimation(jantawee2);
        jantawee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagjantawee = true;
                checkDown();
                try {

                    if (jantawee == false) {
                        ((AnimationDrawable) jantawee2.getBackground()).stop();
                        jantawee = true;
                        //change image view
                        jantawee2.setBackgroundResource(R.drawable.jantawee3);
                        word5.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.jantawee2);
                        mediaPlayer.start();
                    } else {
                        jantawee = false;
                        stopPlaying();
                        jantawee2.setBackgroundResource(R.drawable.animate_jantawee1_2);
                        word5.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) jantawee2.getBackground()).start();
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
        final Intent i = new Intent(this, page1_3.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

    }

    //checkDown ว่ากดปุ่มครบหมดไหมถึงสามารถไปหน้าอื่นได้
    public void checkDown() {
        if (flagoldwomen == true && flagjantawee == true) {
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
                ((AnimationDrawable) oldmen1.getBackground()).start();
                ((AnimationDrawable) oldwomen1.getBackground()).start();
                ((AnimationDrawable) jantawee2.getBackground()).start();
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
        oldwomen1.setBackgroundResource(R.drawable.animate_oldwomen);
        jantawee2.setBackgroundResource(R.drawable.animate_jantawee1_2);
        ((AnimationDrawable) oldwomen1.getBackground()).start();
        ((AnimationDrawable) jantawee2.getBackground()).start();
        word4.setVisibility(View.INVISIBLE);
        word5.setVisibility(View.INVISIBLE);
    }
}

