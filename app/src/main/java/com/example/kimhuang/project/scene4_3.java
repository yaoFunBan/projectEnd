package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 15/2/2559.
 */
public class scene4_3 extends Activity implements View.OnClickListener {
    Intent intent;
    Button btnNext, btnBack, btnPause;
    ToggleButton swMusic, swEffect;
    ImageView bowl, sandThong, cast, Table, box4_3, f1, f2, f3;
    RelativeLayout layoutF;
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose, dialoghome, dialogexit, dialogsetting, btnClose;
    Animation anim, anim2;
    boolean flagBowl = false;
    AnimPopUp animPopUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scene4_3);

        animPopUp = new AnimPopUp();
        anim = new AlphaAnimation(1, 0);
        anim.setDuration(3000);

        anim2 = new AlphaAnimation(1, 0);
        anim2.setDuration(3000);

        box4_3 = (ImageView) findViewById(R.id.box4_3);
        bowl = (ImageView) findViewById(R.id.bowl);
        sandThong = (ImageView) findViewById(R.id.sandThong);
        cast = (ImageView) findViewById(R.id.cast);
        Table = (ImageView) findViewById(R.id.Table);
        layoutF = (RelativeLayout) findViewById(R.id.layout_f);
        btnClose = (Button) findViewById(R.id.btn_close);
        f1 = (ImageView) findViewById(R.id.f1);
        f2 = (ImageView) findViewById(R.id.f2);
        f3 = (ImageView) findViewById(R.id.f3);


        btnPause = (Button) findViewById(R.id.btn_pause);
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

        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        bowl.setOnClickListener(this);
        btnClose.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_next:
                intent = new Intent(getApplicationContext(), page4_4.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.bowl:
                layoutF.setVisibility(View.VISIBLE);
                f1.setAnimation(anim);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        f2.setVisibility(View.VISIBLE);
                        f2.setAnimation(anim2);
                        anim2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                f2.setVisibility(View.INVISIBLE);
                                f3.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.btn_close:
                layoutF.setVisibility(View.INVISIBLE);
                f3.setVisibility(View.INVISIBLE);
                f1.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void checkDown() {
        if (flagBowl == true) {
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        animPopUp.PlayAnimation(box4_3);
        animPopUp.PlayAnimation(bowl);
        animPopUp.PlayAnimation(sandThong);
        animPopUp.PlayAnimation(cast);
        animPopUp.PlayAnimation(Table);

        bowl.setBackgroundResource(R.drawable.anim_bow);
        ((AnimationDrawable) bowl.getBackground()).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        box4_3.setRotationX(90f);
        bowl.setRotationX(90f);
        sandThong.setRotationX(90f);
        cast.setRotationX(90f);
        Table.setRotationX(90f);

        animPopUp.PlayAnimation(box4_3);
        animPopUp.PlayAnimation(bowl);
        animPopUp.PlayAnimation(sandThong);
        animPopUp.PlayAnimation(cast);
        animPopUp.PlayAnimation(Table);
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();


        // mp.pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
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