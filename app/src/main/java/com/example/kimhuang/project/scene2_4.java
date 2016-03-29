package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by วัชรัตน์ on 11/12/2558.
 */
public class scene2_4 extends AppCompatActivity implements View.OnClickListener {

    ImageView kong, val24, kong1, bg_scene;
    boolean clickKong = false;
    Button btnBack, btnPause, dialogexit, dialoghome, dialogset, dialogclose, btnNext;
    RelativeLayout cloud;

    AnimPopUp animPopUp;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene2_4);

        animPopUp = new AnimPopUp();

        mediaPlayer = MediaPlayer.create(this, R.raw.scence2_4);

        kong = (ImageView) findViewById(R.id.kong);
        val24 = (ImageView) findViewById(R.id.val24);
        kong1 = (ImageView) findViewById(R.id.kong1);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPause = (Button) findViewById(R.id.btn_pause);
        bg_scene = (ImageView) findViewById(R.id.bg_scene);
        cloud = (RelativeLayout) findViewById(R.id.cloud);


        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        kong.setOnClickListener(this);
        btnPause.setOnClickListener(this);


    }

    public void countDisplay(final ImageView img) {
        new CountDownTimer(7000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                kong1.setVisibility(View.GONE);
                kong.setVisibility(View.VISIBLE);
                ((AnimationDrawable) kong.getBackground()).start();
                img.setVisibility(View.GONE);
            }
        }.start();
    }

    public void checkEvent() {
        if (clickKong == true) {
            btnBack.setEnabled(true);
            btnNext.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        kong1.setVisibility(View.GONE);
        kong.setVisibility(View.VISIBLE);

        ((AnimationDrawable) kong.getBackground()).start();
        animPopUp.PlayAnimation(kong);
        animPopUp.PlayAnimation(cloud);
        animPopUp.PlayAnimation(bg_scene);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) kong.getBackground()).stop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
        ((AnimationDrawable) kong.getBackground()).stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kong:
                val24.setVisibility(View.VISIBLE);
                kong1.setVisibility(View.VISIBLE);
                kong.setVisibility(View.INVISIBLE);
                countDisplay(val24);
                clickKong = true;
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        checkEvent();
                    }
                });
                break;
            case R.id.val24:
                break;
            case R.id.kong1:
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_next:
                Intent i = new Intent(getApplicationContext(), VoActivity.class);
                startActivity(i);
                break;
            case R.id.btn_pause:
                pauseDialog();
                break;

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        kong.setRotationX(90f);
        bg_scene.setRotationX(90f);
        cloud.setRotationX(90f);

        animPopUp.PlayAnimation(kong);
        animPopUp.PlayAnimation(bg_scene);
        animPopUp.PlayAnimation(cloud);

    }

    public void pauseDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                dialog.dismiss();
            }
        });

//                //button_close
        dialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
