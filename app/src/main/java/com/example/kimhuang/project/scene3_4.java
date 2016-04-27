package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class scene3_4 extends AppCompatActivity implements View.OnClickListener{
    Button btn_back, btn_next, btn_puase, btnClose;
    ToggleButton swMusic, swEffect;
    ImageView ball, horse, bgSky, prain;
    LinearLayout wall;
    Animation slide;
    MediaPlayer mediaPlayer;
    Dialog toy;
    int index = 0;
    int[] restoy = {R.drawable.kema, R.drawable.kala, R.drawable.kanklauy};
    int[] soundtoy = {R.raw.kema, R.raw.kala, R.raw.kankluy};
    //dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    boolean flagHorse, flagball;
    AnimPopUp animPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene3_4);

        animPopUp = new AnimPopUp();

        //bgSky
        bgSky = (ImageView) findViewById(R.id.imgSky);
        animPopUp.PlayAnimation(bgSky);

        //wall
        wall = (LinearLayout) findViewById(R.id.wall);
        animPopUp.PlayAnimation(wall);

        //prain
        prain = (ImageView) findViewById(R.id.prain);
        animPopUp.PlayAnimation(prain);

        //ball
        ball = (ImageView) findViewById(R.id.ball);
        ball.setOnClickListener(this);
        animPopUp.PlayAnimation(ball);
//        ball.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flagball = true;
//                checkDown();
//                slide = AnimationUtils.loadAnimation(scene3_4.this, R.anim.moveball);
//                ball.startAnimation(slide);
//                slide = AnimationUtils.loadAnimation(scene3_4.this, R.anim.moveball);
//                ball.startAnimation(slide);
////                ball.setEnabled(false);//ปิดให้ปุ่มนี้ไม่ทำงาน
////                ((AnimationDrawable) ball.getBackground()).stop();
//                ball.setBackgroundResource(R.drawable.ball2);

//            }
//        });


        //horse
        horse = (ImageView) findViewById(R.id.prasung);

        animPopUp.PlayAnimation(horse);
        horse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagHorse = true;
                checkDown();
                toy.show();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horse);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer = MediaPlayer.create(scene3_4.this, soundtoy[0]);
                        mediaPlayer.start();

                    }
                });
            }
        });

        toy = new Dialog(scene3_4.this);
        toy.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog นำ layout toylayout มาแสดง
        toy.setContentView(R.layout.toylayout);
        //dialog ให้สามารถปิิดได้
        toy.setCancelable(true);

        //เปลี่ยนหน้า dialog การละเล่น
        final Button btnBack = (Button) toy.findViewById(R.id.btnBack);
        final Button btnNext = (Button) toy.findViewById(R.id.btnNext);
        final ImageView kala = (ImageView) toy.findViewById(R.id.kala);
        final Button btnClose = (Button) toy.findViewById(R.id.btnClose);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == restoy.length - 1) {
                    kala.setImageResource(restoy[0]);
                    index = 0;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(scene3_4.this, soundtoy[index]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    kala.setImageResource(restoy[++index]);
                    mediaPlayer = MediaPlayer.create(scene3_4.this, soundtoy[index]);
                    mediaPlayer.start();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    kala.setImageResource(restoy[restoy.length - 1]);
                    index = restoy.length - 1;
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(scene3_4.this, soundtoy[index]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    kala.setImageResource(restoy[--index]);
                    mediaPlayer = MediaPlayer.create(scene3_4.this, soundtoy[index]);
                    mediaPlayer.start();
                }
            }
        });
        //dialog เต็มหน้าจอ
        toy.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toy.cancel();
                mediaPlayer.stop();
            }
        });

        //builder
        btn_puase = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_puase.setOnClickListener(new View.OnClickListener() {
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
                        Intent i = new Intent(getApplicationContext(), map.class);
                        startActivity(i);
                    }
                });
                //button_setting
                dialogset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayDiaglogSetting();
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

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_next = (Button) findViewById(R.id.btn_next);
        final Intent a = new Intent(this, minigame3.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                startActivity(a);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) horse.getBackground()).stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((AnimationDrawable) horse.getBackground()).start();
    }

    public void checkDown() {
        if (flagball == true && flagHorse == true) {
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((AnimationDrawable) ball.getBackground()).start();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ball :
                flagball = true;
                checkDown();
                slide = AnimationUtils.loadAnimation(scene3_4.this, R.anim.moveball);
                ball.startAnimation(slide);

//                ball.setEnabled(false);//ปิดให้ปุ่มนี้ไม่ทำงาน
//                ((AnimationDrawable) ball.getBackground()).stop();
                ball.setBackgroundResource(R.drawable.ball2);
                break;
        }
    }
    //DiaglogSetting
    public void displayDiaglogSetting() {
        final Dialog dsetting = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dsetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dsetting.setContentView(R.layout.setting_dialog);

        btnClose = (Button) dsetting.findViewById(R.id.btn_closes);
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
}

