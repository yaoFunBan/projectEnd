package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

public class scene3_1 extends AppCompatActivity {
    Button btn_back, btn_next, btn_puase, btnClose;
    Switch swMusic, swEffect;
    ImageView ng1, malai, woman1, bgcloud, sis, stage;
    ImageView word31;
    Animation slide;
    MediaPlayer mediaPlayer;

    boolean flagMalai, flagKong, flagRojana;

    //boolean
    boolean woman = false;
    boolean flat = false;
    //dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    AnimPopUp animPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene3_1);
        animPopUp = new AnimPopUp();

        //popup ฉากหลัง
        bgcloud = (ImageView) findViewById(R.id.imgCloud);
        animPopUp.PlayAnimation(bgcloud);

        //popupsis
        sis = (ImageView) findViewById(R.id.sis);
        animPopUp.PlayAnimation(sis);

        //word
        word31 = (ImageView) findViewById(R.id.word31);

        //รจนา

        woman1 = (ImageView) findViewById(R.id.woman1);
        //popup รจนา
        animPopUp.PlayAnimation(woman1);
        //คลิกรจนา
        woman1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagRojana = true;
                checkDown();
                try {
                    if (woman == false) {
                        ((AnimationDrawable) woman1.getBackground()).stop();
                        woman = true;
                        //change image view
                        woman1.setBackgroundResource(R.drawable.woman1);
                        word31.setVisibility(View.VISIBLE);
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rojana);
                        mediaPlayer.start();
                    } else {
                        woman = false;
                        stopPlaying();
                        woman1.setBackgroundResource(R.drawable.animwoman1);
                        word31.setVisibility(View.INVISIBLE);
                        ((AnimationDrawable) woman1.getBackground()).start();

                    }
                } catch (Exception e) {
                }
            }
        });
        // พวงมาลัย
        malai = (ImageView) findViewById(R.id.malai);
        // popup พวงมาลัย
        animPopUp.PlayAnimation(malai);
        // คลิก พวงมาลัย
        malai.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         flagMalai = true;
                                         checkDown();
                                         slide = AnimationUtils.loadAnimation(scene3_1.this, R.anim.movemalai);
                                         malai.startAnimation(slide);
                                         malai.setEnabled(false);//ปิดให้ปุ่มนี้ไม่ทำงาน
                                         malai.setBackgroundResource(R.drawable.malai2);
//                                         ((AnimationDrawable) malai.getBackground()).stop();
                                     }
                                 }
        );

        //กดเงาะ
        ng1 = (ImageView) findViewById(R.id.ng1);
//        anim = new animStandUp(ng1);
        animPopUp.PlayAnimation(ng1);
        ng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagKong = true;
                checkDown();
//                try {
//                    if (flat == false) {
//                        ((AnimationDrawable) ng1.getBackground()).stop();
//                        flat = true;
//                        //change image view
//                        ng1.setBackgroundResource(R.drawable.gold);
////                        gold.setVisibility(View.VISIBLE);
//                    } else {
//                        flat = false;
//                        ng1.setBackgroundResource(R.drawable.animred);
//                        gold.setVisibility(View.INVISIBLE);
//                        ((AnimationDrawable) ng1.getBackground()).start();
//                    }
//                } catch (Exception e) {
//                }
//            }
//        });

                try {
                    ((AnimationDrawable) ng1.getBackground()).stop();
                    //change image view
                    ng1.setBackgroundResource(R.drawable.gold);
                } catch (Exception e) {
                }
            }
        });
        //builder
        btn_puase = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // คลิกปุ่ม pause
        btn_puase.setOnClickListener(new View.OnClickListener() {
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
                        Intent i = new Intent(getApplicationContext(), map3.class);
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
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        //เปลี่ยนหน้า
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }
                                    }
        );
        btn_next = (Button) findViewById(R.id.btn_next);
        final Intent a = new Intent(this, page3_21.class);
        btn_next.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View b) {
                                            startActivity(a);
                                        }
                                    }
        );
    }

    public void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void checkDown() {
        if (flagKong == true && flagMalai == true && flagRojana == true) {
            btn_back.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        woman1.setBackgroundResource(R.drawable.animwoman1);
        ng1.setBackgroundResource(R.drawable.animred);
        malai.setBackgroundResource(R.drawable.animmalai);
        word31.setVisibility(View.INVISIBLE);


        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((AnimationDrawable) ng1.getBackground()).start();
                ((AnimationDrawable) malai.getBackground()).start();
                ((AnimationDrawable) woman1.getBackground()).start();
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
