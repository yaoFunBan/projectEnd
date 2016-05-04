package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class page2_3 extends Activity implements View.OnClickListener {

    MediaPlayer mp;
    MediaPlayer mediaPlayer;
    ToggleButton btn_music;
    boolean isOpen = false;
    Button btnBack, btnNext, btn_pause;
    ToggleButton swMusic, swEffect;
    Button dialogexit, dialoghome, dialogclose, btnPlayAgain, btnClose;
    AlertDialog.Builder builder;
    Dialog dialog;
    Intent i;

    soundBG soundBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2_3);

        mediaPlayer = MediaPlayer.create(this, R.raw.ss2_3);
        mediaPlayer.start();

        soundBG = new soundBG(page2_3.this);

        btnPlayAgain = (Button) findViewById(R.id.btn_play_story);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        btn_music = (ToggleButton) findViewById(R.id.btn_music);
        btn_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (isOpen == true) {
                    mediaPlayer.start();
                    btn_music.setBackgroundResource(R.drawable.btn_music);
                    isOpen = false;
                } else {
                    mediaPlayer.pause();
                    btn_music.setBackgroundResource(R.drawable.btn_music_act);
                    isOpen = true;
                }
            }
        });
        btn_pause = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.btndialog_r);

                //TODO findViewBy
                dialogexit = (Button) dialog.findViewById(R.id.btn_exit);
                dialoghome = (Button) dialog.findViewById(R.id.btn_home);
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
                        Intent i = new Intent(getApplicationContext(), map2.class);
                        startActivity(i);
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                soundBG.creatSound();
                finish();
                break;
            case R.id.btn_next:
                i = new Intent(getApplicationContext(), scene2_3.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOpen == true) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        soundBG.stopBG();
    }
}