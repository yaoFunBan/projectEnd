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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

public class scene1_3 extends AppCompatActivity {
    ImageView house1, sungthong3, chicken1, grass1, grass2, trees1, box1_3;
    Button btn_back, btn_next, btn_close, btn_pause,btnClose;
    Switch swMusic, swEffect;
    //boolean
    boolean chicken = false;
    boolean house = false;
    boolean flagchicken, flaghouse;
    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    Dialog knowlesst;
    int pindex = 0;
    int[] resChiken = {R.drawable.knowless_ck1, R.drawable.knowless_ck2, R.drawable.knowless_ck3};
    int[] soundChiken = {R.raw.ch1, R.raw.ch2, R.raw.ch3};
    //etc
    AnimPopUp animPopUp;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene1_3);

        //animPopUp
        animPopUp = new AnimPopUp();

        //grass1
        grass1 = (ImageView) findViewById(R.id.grass1);
        animPopUp.PlayAnimation(grass1);

        //grass2
        grass2 = (ImageView) findViewById(R.id.grass2);
        animPopUp.PlayAnimation(grass2);

        //trees1
        trees1 = (ImageView) findViewById(R.id.trees1);
        animPopUp.PlayAnimation(trees1);

        //box1_3
        box1_3 = (ImageView) findViewById(R.id.box1_3);
        animPopUp.PlayAnimation(box1_3);

        //house
        house1 = (ImageView) findViewById(R.id.house1);
        animPopUp.PlayAnimation(house1);
        house1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flaghouse = true;
                checkDown();
                try {

                    if (house == false) {
                        ((AnimationDrawable) house1.getBackground()).stop();
                        house = true;
                        //change image view
                        house1.setBackgroundResource(R.drawable.house3);
                    } else {
                        house = false;
                        house1.setBackgroundResource(R.drawable.animate_house);
                        ((AnimationDrawable) house1.getBackground()).start();
                    }

                } catch (Exception e) {
                }
            }
        });


        // sungthong
        sungthong3 = (ImageView) findViewById(R.id.sungthong3);
        animPopUp.PlayAnimation(sungthong3);

        //chicken
        chicken1 = (ImageView) findViewById(R.id.chicken1);
        animPopUp.PlayAnimation(chicken1);
        chicken1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagchicken = true;
                checkDown();
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rooster);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer = MediaPlayer.create(scene1_3.this, soundChiken[0]);
                        mediaPlayer.start();
                    }
                });
                try {
                    if (chicken = false) {
                        ((AnimationDrawable) chicken1.getBackground()).stop();
                        chicken = true;
                        //change image view
                        chicken1.setBackgroundResource(R.drawable.chicken1);
                        knowlesst.show();
                    } else {
                        chicken = false;
                        //change image view
                        chicken1.setBackgroundResource(R.drawable.animate_chicken);
                        ((AnimationDrawable) chicken1.getBackground()).start();
                        knowlesst.show();
                    }
                } catch (Exception e) {
                }
            }
        });

        knowlesst = new Dialog(scene1_3.this);
        knowlesst.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog นำ layout chickenlayout มาแสดง
        knowlesst.setContentView(R.layout.chickenlayout);
        //dialog ให้สามารถปิิดได้
        knowlesst.setCancelable(true);

        final Button btnBack = (Button) knowlesst.findViewById(R.id.button1);
        final Button btnNext = (Button) knowlesst.findViewById(R.id.button);
        final ImageView bg = (ImageView) knowlesst.findViewById(R.id.knowless_ck1);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pindex == resChiken.length - 1) {
                    bg.setImageResource(resChiken[0]);
                    mediaPlayer.stop();
                    pindex = 0;
                    mediaPlayer = MediaPlayer.create(scene1_3.this, soundChiken[pindex]);
                    mediaPlayer.start();

                } else {
                    mediaPlayer.stop();
                    bg.setImageResource(resChiken[++pindex]);
                    mediaPlayer = MediaPlayer.create(scene1_3.this, soundChiken[pindex]);
                    mediaPlayer.start();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pindex == 0) {
                    mediaPlayer.stop();
                    bg.setImageResource(resChiken[resChiken.length - 1]);
                    pindex = resChiken.length - 1;
                    mediaPlayer = MediaPlayer.create(scene1_3.this, soundChiken[pindex]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    bg.setImageResource(resChiken[--pindex]);
                    mediaPlayer = MediaPlayer.create(scene1_3.this, soundChiken[pindex]);
                    mediaPlayer.start();

                }
            }
        });

        //dialog เต็มหน้าจอ
        knowlesst.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //button_close
        btn_close = (Button) knowlesst.findViewById(R.id.button3);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knowlesst.cancel();
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
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
                        displayDiaglogSetting();
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
        final Intent i = new Intent(this, page1_4.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }

    //checkDown ว่ากดปุ่มครบหมดไหมถึงสามารถไปหน้าอื่นได้
    public void checkDown() {
        if (flaghouse == true && flagchicken == true) {
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
                ((AnimationDrawable) sungthong3.getBackground()).start();
                ((AnimationDrawable) chicken1.getBackground()).start();
                ((AnimationDrawable) house1.getBackground()).start();
            }
        }.start();
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

