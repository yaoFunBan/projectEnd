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
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class scene3_3 extends AppCompatActivity {
    Button btn_back, btn_next, btn_puase, btnClose;
    ToggleButton swMusic, swEffect;
    ImageView thaosamon1, prain1, bgSky;
    LinearLayout wall;
    ImageView word3_31, word3_32;
    MediaPlayer mediaYok, mediaPaIn;
    //boolean
    boolean thaosamon = false;
    boolean prain = false;
    boolean flagThaosamon, flagPrain;
    AnimPopUp animPopUp;
    //dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene3_3);

        animPopUp = new AnimPopUp();

        //bgSky
        bgSky = (ImageView) findViewById(R.id.imgSky);
        animPopUp.PlayAnimation(bgSky);

        //wall
        wall = (LinearLayout) findViewById(R.id.wall);
        animPopUp.PlayAnimation(wall);

        //word
        word3_31 = (ImageView) findViewById(R.id.word3_31);
        word3_32 = (ImageView) findViewById(R.id.word3_32);

        //thaosamon


        thaosamon1 = (ImageView) findViewById(R.id.thaosamon1);
        animPopUp.PlayAnimation(thaosamon1);
        thaosamon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagThaosamon = true;
                checkDown();
                try {

                    if (thaosamon == false) {
                        ((AnimationDrawable) thaosamon1.getBackground()).stop();
                        thaosamon = true;
                        //change image view
                        thaosamon1.setBackgroundResource(R.drawable.thaosamon1);
                        word3_31.setVisibility(View.VISIBLE);
                        mediaYok = MediaPlayer.create(getApplicationContext(), R.raw.thaosamon);
                        mediaYok.start();
                    } else {
                        thaosamon = false;
                        stopPlaying();
                        thaosamon1.setBackgroundResource(R.drawable.animthaosamon);
                        word3_31.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) thaosamon1.getBackground()).start();
                        mediaYok.start();
                    }

                } catch (Exception e) {
                }
            }
        });
        //prain

        prain1 = (ImageView) findViewById(R.id.prain1);
        animPopUp.PlayAnimation(prain1);
        prain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagPrain = true;
                checkDown();
                try {

                    if (prain == false) {
                        ((AnimationDrawable) prain1.getBackground()).stop();
                        prain = true;
                        //change image view
                        prain1.setBackgroundResource(R.drawable.prain1);
                        word3_32.setVisibility(View.VISIBLE);
                        mediaPaIn = MediaPlayer.create(getApplicationContext(), R.raw.prain);
                        mediaPaIn.start();

                    } else {
                        prain = false;
                        stopPlaying();
                        prain1.setBackgroundResource(R.drawable.animprain);
                        word3_32.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) prain1.getBackground()).start();
                    }

                } catch (Exception e) {
                }
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
                        Intent i = new Intent(getApplicationContext(), map3.class);
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

        //เปลี่ยนหน้า
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_next = (Button) findViewById(R.id.btn_next);
        final Intent a = new Intent(this, page3_41.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                startActivity(a);
            }
        });
    }

    public void stopPlaying() {
        if (mediaPaIn != null) {
            mediaPaIn.start();
            mediaPaIn.release();
            mediaPaIn = null;
        }

        if (mediaYok != null) {
            mediaYok.start();
            mediaYok.release();
            mediaYok = null;
        }
    }

    // check ว่า กดทุกอันยัง แล้วปุ่มเปลี่ยนจะขึ้นมา
    public void checkDown() {
        if (flagThaosamon == true && flagPrain == true) {
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        thaosamon1.setBackgroundResource(R.drawable.animthaosamon);
        prain1.setBackgroundResource(R.drawable.animprain);
        word3_31.setVisibility(View.INVISIBLE);
        word3_32.setVisibility(View.INVISIBLE);

        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            //ให้ popup ขึ้นมาก่อนแล้วค่อยกระพริบ
            @Override
            public void onFinish() {
                ((AnimationDrawable) thaosamon1.getBackground()).start();
                ((AnimationDrawable) prain1.getBackground()).start();
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
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
