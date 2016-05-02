package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 15/2/2559.
 */
public class page4_12 extends Activity implements View.OnClickListener {
    Button btnBack, btnNext, btnPause, btnClose;
    ToggleButton swMusic, swEffect;
    MediaPlayer mediaPlayer;
    ToggleButton btn_music;

    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogclose, dialoghome, dialogexit, dialogsetting, btnPlayAgain;
    Intent i;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4_12);

        //เล่านิทาน
        mediaPlayer = MediaPlayer.create(this, R.raw.pg4_12);
        mediaPlayer.start();

        btnPlayAgain = (Button) findViewById(R.id.btn_play_story);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        //ปุ่ม btn_music
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
                    //                    Intent btnhome = new Intent(getApplicationContext(), map4.class);
                    @Override
                    public void onClick(View v) {
                        Intent btnhome = new Intent(getApplicationContext(), map4.class);
                        startActivity(btnhome);
                    }
                });
                dialogsetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogSetting setDialog = new DialogSetting(page4_12.this);
                        setDialog.show();

                        Window window = setDialog.getWindow();
                        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        window.setGravity(Gravity.CENTER);
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
        btnBack = (Button) findViewById(R.id.btn_back);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_next:
                i = new Intent(getApplicationContext(), scene4_1.class);
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
}

