package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 22/12/2558.
 */
public class page2_1 extends Activity implements View.OnClickListener {

    MediaPlayer mp;
    MediaPlayer mediaPlayer;
    ToggleButton btn_music;
    boolean isOpen = false;
    Button btnBack, btnNext, btn_pause, btnPlayAgain;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    AlertDialog.Builder builder;
    Dialog dialog;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2_1);

        mediaPlayer = MediaPlayer.create(this, R.raw.ss2_1);
        mediaPlayer.start();

        btnPlayAgain = (Button) findViewById(R.id.btn_play_story);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        btnBack = (Button) findViewById(R.id.btn_back);
        btnNext = (Button) findViewById(R.id.btn_next);
        btn_music = (ToggleButton) findViewById(R.id.btn_music);

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
                        Intent i = new Intent(getApplicationContext(), map2.class);
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
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_next:
                i = new Intent(getApplicationContext(), scene2_1.class);
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
    protected void onRestart() {
        super.onRestart();


    }
}