package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class scene1_4 extends AppCompatActivity {
    ImageView sungthong4, firsh1, firsh2, firsh3, box1_4, alga1, alga2;
    ImageView word6, word7;
    Button btn_back, btn_next, btn_pause;
    //boolean
    boolean firsh = false;
    boolean sungthong = false;
    boolean flagfirsh, flagsungthong;
    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    //etc
    AnimPopUp animPopUp;
    MediaPlayer mediaF, mediaS;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1_4);

        //animPopUp
        animPopUp = new AnimPopUp();

        //box1_4
        box1_4 = (ImageView) findViewById(R.id.box1_4);
        animPopUp.PlayAnimation(box1_4);

        //frish1
        firsh1 = (ImageView) findViewById(R.id.firsh1);
        animPopUp.PlayAnimation(firsh1);

        //alga1
        alga1 = (ImageView) findViewById(R.id.alga1);
        animPopUp.PlayAnimation(alga1);

        //firsh3
        firsh3 = (ImageView) findViewById(R.id.firsh3);
        animPopUp.PlayAnimation(firsh3);


        //firsh3
        alga2 = (ImageView) findViewById(R.id.alga2);
        animPopUp.PlayAnimation(alga2);

        //word6
        word6 = (ImageView) findViewById(R.id.word6);

        //word7
        word7 = (ImageView) findViewById(R.id.word7);

        //firsh
        firsh2 = (ImageView) findViewById(R.id.firsh2);
        animPopUp.PlayAnimation(firsh2);
        firsh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagfirsh = true;
                checkDown();
                try {

                    if (firsh == false) {
                        ((AnimationDrawable) firsh2.getBackground()).stop();
                        firsh = true;
                        //change image view
                        firsh2.setBackgroundResource(R.drawable.firsh4);
                        word6.setVisibility(View.VISIBLE);
                        //mediaplayer
                        mediaF = MediaPlayer.create(scene1_4.this, R.raw.firsh);
                        mediaF.start();
                    } else {

                        firsh = false;
                        word6.setVisibility(View.INVISIBLE);
                        stopPlaying();
                        firsh2.setBackgroundResource(R.drawable.animate_firsh1);
                        ((AnimationDrawable) firsh2.getBackground()).start();

                    }
                } catch (Exception e) {
                }

            }
        });

        // sungthong
        sungthong4 = (ImageView) findViewById(R.id.sungthong4);
        animPopUp.PlayAnimation(sungthong4);
        sungthong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagsungthong = true;
                checkDown();
                try {

                    if (sungthong == false) {
                        ((AnimationDrawable) sungthong4.getBackground()).stop();
                        sungthong = true;
                        //change image view
                        sungthong4.setBackgroundResource(R.drawable.sungthong5);
                        word7.setVisibility(View.VISIBLE);
                        mediaS = MediaPlayer.create(getApplicationContext(), R.raw.prasung);
                        mediaS.start();
                        //mediaplayer
                    } else {
                        sungthong = false;
                        stopPlaying();
                        sungthong4.setBackgroundResource(R.drawable.animate_sungthong1_4);
                        word7.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) sungthong4.getBackground()).start();

                    }

                } catch (Exception e) {
                }
            }
        });


        //button_pause
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
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
                    }
                });

                //button_setting
                dialogset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null)
                            dialog.dismiss();
                    }
                });

                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null)
                            dialog.cancel();

                    }
                });
                dialog.show();
            }
        });


        //button_back
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //button_next
        btn_next = (Button) findViewById(R.id.btn_next);
        final Intent i = new Intent(this, map1.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }

    public void checkDown() {
        if (flagsungthong == true && flagfirsh == true) {
            btn_next.setVisibility(View.VISIBLE);
            btn_back.setVisibility(View.VISIBLE);
        }
    }

    //ให้อนิเมชันเริ่มหลังจากที่ popup ขึ้นมาแล้ว
    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((AnimationDrawable) firsh1.getBackground()).start();
                ((AnimationDrawable) firsh2.getBackground()).start();
                ((AnimationDrawable) sungthong4.getBackground()).start();
                word6.setVisibility(View.INVISIBLE);
                word7.setVisibility(View.INVISIBLE);

            }
        }.start();
    }


    public void stopPlaying() {
        if (mediaF != null) {
            mediaF.start();
            mediaF.release();
            mediaF = null;
        }

        if (mediaS != null) {
            mediaS.start();
            mediaS.release();
            mediaS = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlaying();
    }


    @Override
    protected void onStart() {
        super.onStart();
        firsh2.setBackgroundResource(R.drawable.animate_firsh1);
        sungthong4.setBackgroundResource(R.drawable.animate_sungthong1_4);
        ((AnimationDrawable) firsh1.getBackground()).start();
        ((AnimationDrawable) firsh2.getBackground()).start();
        ((AnimationDrawable) sungthong4.getBackground()).start();
        word6.setVisibility(View.INVISIBLE);
        word7.setVisibility(View.INVISIBLE);
    }
}
