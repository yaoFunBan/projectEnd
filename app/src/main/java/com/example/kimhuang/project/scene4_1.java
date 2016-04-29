package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 15/2/2559.
 */
public class scene4_1 extends Activity implements View.OnClickListener {

    Intent intent;
    Button btnNext, btnBack, btnPause, btnClose;
    ToggleButton swMusic, swEffect;
    ImageView home, trees, horus, yotwimon, jantra, box4_1;
    Dialog house;
    int index = 0;
    int[] reshouse = {R.drawable.h1, R.drawable.h2, R.drawable.h3};
    int[] soundBan = {R.raw.ban1, R.raw.ban2, R.raw.ban3};

    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose, dialoghome, dialogexit, dialogsetting;
    boolean flagHome = false;

    MediaPlayer mediaPlayer;

    AnimPopUp animPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scene4_1);

        animPopUp = new AnimPopUp();

        box4_1 = (ImageView) findViewById(R.id.box4_1);
        jantra = (ImageView) findViewById(R.id.jantra);
        yotwimon = (ImageView) findViewById(R.id.yotwimon);
        horus = (ImageView) findViewById(R.id.horus);
        home = (ImageView) findViewById(R.id.home);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);
        trees = (ImageView) findViewById(R.id.forest);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHome();
                flagHome = true;
                checkDown();
                mediaPlayer = MediaPlayer.create(scene4_1.this, soundBan[0]);
                mediaPlayer.start();

            }
        });


        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPause.setOnClickListener(this);


    }

    public void checkDown() {
        if (flagHome == true) {
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_next:
                intent = new Intent(getApplicationContext(), page4_2.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_pause:
                dialogPause();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) home.getBackground()).stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        box4_1.setRotationX(90f);
        home.setRotationX(90f);
        trees.setRotationX(90f);
        jantra.setRotationX(90f);
        yotwimon.setRotationX(90f);
        horus.setRotationX(90f);

        animPopUp.PlayAnimation(box4_1);
        animPopUp.PlayAnimation(home);
        animPopUp.PlayAnimation(trees);
        animPopUp.PlayAnimation(jantra);
        animPopUp.PlayAnimation(yotwimon);
        animPopUp.PlayAnimation(horus);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ((AnimationDrawable) home.getBackground()).start();
        animPopUp.PlayAnimation(box4_1);
        animPopUp.PlayAnimation(home);
        animPopUp.PlayAnimation(trees);
        animPopUp.PlayAnimation(jantra);
        animPopUp.PlayAnimation(yotwimon);
        animPopUp.PlayAnimation(horus);
    }

    public void dialogPause() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.btndialog);

        //TODO findViewby
        dialogexit = (Button) dialog.findViewById(R.id.btn_exit);
        dialoghome = (Button) dialog.findViewById(R.id.btn_home);
        dialogsetting = (Button) dialog.findViewById(R.id.btn_setting);
        dialogclose = (Button) dialog.findViewById(R.id.btn_close);

        dialogexit.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        dialoghome.setOnClickListener(new View.OnClickListener() {
           Intent btnhome = new Intent(getApplicationContext(), map4.class);
            @Override
            public void onClick(View v) {
                        startActivity(btnhome);
            }
        });
        dialogsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDiaglogSetting();
            }
        });
        dialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) home.getBackground()).stop();

    }

    public void showDialogHome() {
        final Dialog dBoat = new Dialog(scene4_1.this);
        dBoat.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dBoat.setContentView(R.layout.houselayout);
        dBoat.setCancelable(true);

        Button btn_cloase = (Button) dBoat.findViewById(R.id.btn_close);
        final ImageView imghome = (ImageView) dBoat.findViewById(R.id.bg1);
        final Button btn_next = (Button) dBoat.findViewById(R.id.btn_next);
        final Button btn_back = (Button) dBoat.findViewById(R.id.btn_back);
        imghome.setImageResource(reshouse[0]);
        btn_cloase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dBoat.cancel();
                mediaPlayer.stop();
                index = 0;
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == reshouse.length - 1) {
                    imghome.setImageResource(reshouse[0]);
                    mediaPlayer.stop();
                    index = 0;
                    mediaPlayer = MediaPlayer.create(scene4_1.this, soundBan[index]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    imghome.setImageResource(reshouse[++index]);
                    mediaPlayer = MediaPlayer.create(scene4_1.this, soundBan[index]);
                    mediaPlayer.start();
                }
            }

        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    mediaPlayer.stop();
                    imghome.setImageResource(reshouse[reshouse.length - 1]);
                    index = reshouse.length - 1;
                    mediaPlayer = MediaPlayer.create(scene4_1.this, soundBan[index]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    imghome.setImageResource(reshouse[--index]);
                    mediaPlayer = MediaPlayer.create(scene4_1.this, soundBan[index]);
                    mediaPlayer.start();
                }
            }
        });

        dBoat.show();
        Window window = dBoat.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
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

