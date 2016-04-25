package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

/**
 * Created by วัชรัตน์ on 15/2/2559.
 */
public class scene4_4 extends Activity implements View.OnClickListener {
    Intent intent;
    Button btnBack, btnPause, btnClose;
    Switch swMusic, swEffect;

    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose, dialoghome, dialogexit, dialogsetting;
    ImageView rosjana, pasang, castles4_4, tress, mount, box4_4;
    AnimPopUp animPopUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scene4_4);

        box4_4 = (ImageView) findViewById(R.id.box4_4);
        btnPause = (Button) findViewById(R.id.btn_pause);
        rosjana = (ImageView) findViewById(R.id.rosjana);
        castles4_4 = (ImageView) findViewById(R.id.castles4_4);
        pasang = (ImageView) findViewById(R.id.pasang);
        tress = (ImageView) findViewById(R.id.tress);
        mount = (ImageView) findViewById(R.id.mount);

        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        animPopUp = new AnimPopUp();

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
                    Intent btnhome = new Intent(getApplicationContext(), map.class);
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

//        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);


//        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
//            case R.id.btn_next:
//                intent = new Intent(getApplicationContext(), page4_4.class);
//                startActivity(intent);
//                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        animPopUp.PlayAnimation(box4_4);
        animPopUp.PlayAnimation(rosjana);
        animPopUp.PlayAnimation(pasang);
        animPopUp.PlayAnimation(castles4_4);
        animPopUp.PlayAnimation(tress);
        animPopUp.PlayAnimation(mount);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        box4_4.setRotationX(90f);
        rosjana.setRotationX(90f);
        pasang.setRotationX(90f);
        castles4_4.setRotationX(90f);
        tress.setRotationX(90f);
        mount.setRotationX(90f);

        animPopUp.PlayAnimation(box4_4);
        animPopUp.PlayAnimation(rosjana);
        animPopUp.PlayAnimation(pasang);
        animPopUp.PlayAnimation(castles4_4);
        animPopUp.PlayAnimation(tress);
        animPopUp.PlayAnimation(mount);
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