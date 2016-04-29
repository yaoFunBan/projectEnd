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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 15/2/2559.
 */
public class scene4_2 extends Activity implements View.OnClickListener {

    Intent intent;
    Button btnNext, btnBack, btnPause, btnClose;
    ToggleButton swMusic, swEffect;
    ImageView jantawee, word41, yotSa, tree1, tree2, mount, box4_2;
    boolean woman = false;

    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose, dialoghome, dialogexit, dialogsetting;

    boolean flagJantra;
    AnimPopUp animPopUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scene4_2);

        animPopUp = new AnimPopUp();

        box4_2 = (ImageView) findViewById(R.id.box4_2);
        word41 = (ImageView) findViewById(R.id.word41);
        jantawee = (ImageView) findViewById(R.id.jantawee);
        yotSa = (ImageView) findViewById(R.id.yotsawimon);
        tree1 = (ImageView) findViewById(R.id.trees1);
        tree2 = (ImageView) findViewById(R.id.trees2);
        mount = (ImageView) findViewById(R.id.mountain);
        btnPause = (Button) findViewById(R.id.btn_pause);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);

        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        jantawee.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_next:
                intent = new Intent(getApplicationContext(), page4_3.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.jantawee:
                flagJantra = true;
                try {
                    if (woman == false) {
                        ((AnimationDrawable) jantawee.getBackground()).stop();
                        woman = true;
                        jantawee.setBackgroundResource(R.drawable.jantra42_light);
                        word41.setVisibility(View.VISIBLE);
                        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.scence4_2);
                        mediaPlayer.start();
                        checkDown();

                    } else {
                        woman = false;
                        word41.setVisibility(View.INVISIBLE);
                        jantawee.setBackgroundResource(R.drawable.animjantawee);
                        ((AnimationDrawable) jantawee.getBackground()).start();
                    }
                } catch (Exception e) {
                }
                break;
        }
    }

    public void checkDown() {
        if (flagJantra == true) {
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        jantawee.setBackgroundResource(R.drawable.animjantawee);
        ((AnimationDrawable) jantawee.getBackground()).start();

        animPopUp.PlayAnimation(box4_2);
        animPopUp.PlayAnimation(jantawee);
        animPopUp.PlayAnimation(yotSa);
        animPopUp.PlayAnimation(tree1);
        animPopUp.PlayAnimation(tree2);
        animPopUp.PlayAnimation(mount);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        box4_2.setRotationX(90f);
        jantawee.setRotationX(90f);
        yotSa.setRotationX(90f);
        tree1.setRotationX(90f);
        tree2.setRotationX(90f);
        mount.setRotationX(90f);

        animPopUp.PlayAnimation(box4_2);
        animPopUp.PlayAnimation(jantawee);
        animPopUp.PlayAnimation(yotSa);
        animPopUp.PlayAnimation(tree1);
        animPopUp.PlayAnimation(tree2);
        animPopUp.PlayAnimation(mount);
    }


    @Override
    protected void onPause() {
        super.onPause();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();


//        ((AnimationDrawable) jantawee.getBackground()).stop();

        // mp.pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) jantawee.getBackground()).stop();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ((AnimationDrawable) jantawee.getBackground()).start();
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