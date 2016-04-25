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

public class scene3_2 extends AppCompatActivity {
    Button btn_back, btn_next, btn_puase, btnClose;
    Switch swMusic, swEffect;
    ImageView sung1, meat1, fish, fish1, bgSun, wood, tree, trees, waterfall, fishes, bro1, bro2;
    ImageView word3_21, word3_22;
    MediaPlayer mediaM, mediaS;
    //boolean
    boolean sung = false;
    boolean meat = false;
    boolean flagSung, flagMeat;
    AnimPopUp animPopUp;

    //dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene3_2);

        animPopUp = new AnimPopUp();

        //bgSun
        bgSun = (ImageView) findViewById(R.id.imgSun);
        animPopUp.PlayAnimation(bgSun);

        //waterfall
        waterfall = (ImageView) findViewById(R.id.waterfall);
        animPopUp.PlayAnimation(waterfall);

        //wood
        wood = (ImageView) findViewById(R.id.wood);
        animPopUp.PlayAnimation(wood);

        //fishes
        fishes = (ImageView) findViewById(R.id.fish);
        animPopUp.PlayAnimation(fishes);

        //bro1
        bro1 = (ImageView) findViewById(R.id.bro1);
        animPopUp.PlayAnimation(bro1);

        //bro2
        bro2 = (ImageView) findViewById(R.id.bro2);
        animPopUp.PlayAnimation(bro2);

        //tree
        tree = (ImageView) findViewById(R.id.tree);
        animPopUp.PlayAnimation(tree);

        //trees
        trees = (ImageView) findViewById(R.id.trees);
        animPopUp.PlayAnimation(trees);

        //word
        word3_21 = (ImageView) findViewById(R.id.word3_21);
        word3_22 = (ImageView) findViewById(R.id.word3_22);

        //animfish
        fish = (ImageView) findViewById(R.id.animfish);
        fish1 = (ImageView) findViewById(R.id.animfish1);
        //sung1

        sung1 = (ImageView) findViewById(R.id.sung1);
        animPopUp.PlayAnimation(sung1);
        sung1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagSung = true;
                checkDown();
                try {

                    if (sung == false) {
                        ((AnimationDrawable) sung1.getBackground()).stop();
                        sung = true;
                        //change image view
                        sung1.setBackgroundResource(R.drawable.sung1);
                        word3_21.setVisibility(View.VISIBLE);
                        mediaS = MediaPlayer.create(getApplicationContext(), R.raw.sungthong);
                        mediaS.start();
                    } else {
                        sung = false;
                        stopPlaying();
                        sung1.setBackgroundResource(R.drawable.animsung1);
                        word3_21.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) sung1.getBackground()).start();
                    }

                } catch (Exception e) {
                }
            }
        });

        //meat1

        meat1 = (ImageView) findViewById(R.id.meat1);
        animPopUp.PlayAnimation(meat1);
        meat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagMeat = true;
                checkDown();
                try {

                    if (meat == false) {
                        ((AnimationDrawable) meat1.getBackground()).stop();
                        meat = true;
                        //change image view
                        meat1.setBackgroundResource(R.drawable.meat1);
                        word3_22.setVisibility(View.VISIBLE);
                        mediaM = MediaPlayer.create(getApplicationContext(), R.raw.meat);
                        mediaM.start();
                    } else {
                        meat = false;
                        stopPlaying();
                        meat1.setBackgroundResource(R.drawable.animmeat);
                        word3_22.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) meat1.getBackground()).start();
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
        final Intent a = new Intent(this, page3_3.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                startActivity(a);
            }
        });
    }

    public void stopPlaying() {
        if (mediaS != null) {
            mediaS.start();
            mediaS.release();
            mediaS = null;
        }

        if (mediaM != null) {
            mediaM.start();
            mediaM.release();
            mediaM = null;
        }
    }

    public void checkDown() {
        if (flagSung == true && flagMeat == true) {
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
                ((AnimationDrawable) fish.getBackground()).start();
                ((AnimationDrawable) fish1.getBackground()).start();
                ((AnimationDrawable) sung1.getBackground()).start();
                ((AnimationDrawable) meat1.getBackground()).start();
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }

    @Override
    protected void onStart() {
        super.onStart();
        word3_21.setVisibility(View.INVISIBLE);
        word3_22.setVisibility(View.INVISIBLE);
        meat1.setBackgroundResource(R.drawable.animmeat);
        ((AnimationDrawable) meat1.getBackground()).start();
        sung1.setBackgroundResource(R.drawable.animsung1);
        ((AnimationDrawable) sung1.getBackground()).start();
    }
    //DiaglogSetting
    public void displayDiaglogSetting() {
        final Dialog dsetting = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dsetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dsetting.setContentView(R.layout.setting_dialog);

        btnClose = (Button) dsetting.findViewById(R.id.btn_closes);
        swMusic = (Switch) dsetting.findViewById(R.id.sw_music);
        swEffect = (Switch) dsetting.findViewById(R.id.sw_effect);

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



