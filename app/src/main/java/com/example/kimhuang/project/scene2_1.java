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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class scene2_1 extends AppCompatActivity implements View.OnClickListener {

    ImageView forests, giantAnim, giant1, boat, imgBoat, giantTalk, giantWord, mountain;
    Button btnNext, btnBack, btnPause, dialogexit, dialoghome, dialogset, dialogclose;
    Intent intent;
    MediaPlayer mp;
    int pIndex = 0;

    int[] resBoat = {R.drawable.suwon, R.drawable.anan, R.drawable.boatage, R.drawable.narai};
    AnimPopUp animPopUp;
    int[] soundBoat = {R.raw.suwon, R.raw.anan, R.raw.ane, R.raw.narai};

    boolean bgiant = false, bboat = false;
    int i = 0;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene2_1);

        animPopUp = new AnimPopUp();


        mountain = (ImageView) findViewById(R.id.mountain);
        forests = (ImageView) findViewById(R.id.forests);
        boat = (ImageView) findViewById(R.id.boatp);
        btnPause = (Button) findViewById(R.id.btn_pause);
        giant1 = (ImageView) findViewById(R.id.giant1);
        giantAnim = (ImageView) findViewById(R.id.giant);
        giantWord = (ImageView) findViewById(R.id.valword);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);

        mp = MediaPlayer.create(scene2_1.this, R.raw.ss2_1);


        boat.setOnClickListener(this);
        giantAnim.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPause.setOnClickListener(this);

    }


    public void countTime(final ImageView img) {
        new CountDownTimer(7000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                img.setVisibility(View.GONE);
                giantAnim.setVisibility(View.VISIBLE);
                giant1.setVisibility(View.GONE);
            }
        }.start();
    }

    public void showDialogBoat() {
        final Dialog dBoat = new Dialog(scene2_1.this);
        dBoat.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dBoat.setContentView(R.layout.layout_boat);
        dBoat.setCancelable(true);

        Button btn_cloase = (Button) dBoat.findViewById(R.id.btn_close);
        imgBoat = (ImageView) dBoat.findViewById(R.id.suwon);
        final Button btn_next = (Button) dBoat.findViewById(R.id.brn_nextD);
        final Button btn_back = (Button) dBoat.findViewById(R.id.brn_backD);

        btn_cloase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dBoat.cancel();
                boat.setVisibility(View.VISIBLE);
                mediaPlayer.stop();
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pIndex == resBoat.length - 1) {
                    imgBoat.setImageResource(resBoat[0]);
                    mediaPlayer.stop();
                    pIndex = 0;
                    mediaPlayer = MediaPlayer.create(scene2_1.this, soundBoat[pIndex]);
                    mediaPlayer.start();
                } else {
                    mediaPlayer.stop();
                    imgBoat.setImageResource(resBoat[++pIndex]);
                    mediaPlayer = MediaPlayer.create(scene2_1.this, soundBoat[pIndex]);
                    mediaPlayer.start();
                }
            }

        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pIndex == 0) {
                    imgBoat.setImageResource(resBoat[resBoat.length - 1]);
                    pIndex = resBoat.length - 1;
                } else {
                    imgBoat.setImageResource(resBoat[--pIndex]);
                }
            }
        });

        dBoat.show();
        Window window = dBoat.getWindow();
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        giantAnim.setVisibility(View.VISIBLE);
        giant1.setVisibility(View.INVISIBLE);

        animPopUp.PlayAnimation(boat);
        animPopUp.PlayAnimation(giantAnim);
        animPopUp.PlayAnimation(forests);
        animPopUp.PlayAnimation(mountain);

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


        ((AnimationDrawable) giantAnim.getBackground()).stop();
        ((AnimationDrawable) boat.getBackground()).stop();

        // mp.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) giantAnim.getBackground()).stop();
        ((AnimationDrawable) boat.getBackground()).stop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new CountDownTimer(1500, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((AnimationDrawable) giantAnim.getBackground()).start();
                ((AnimationDrawable) boat.getBackground()).start();
            }
        }.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((AnimationDrawable) giantAnim.getBackground()).start();
        ((AnimationDrawable) boat.getBackground()).start();

        boat.setRotationX(90f);
        giantAnim.setRotationX(90f);
        forests.setRotationX(90f);
        mountain.setRotationX(90f);

        animPopUp.PlayAnimation(boat);
        animPopUp.PlayAnimation(giantAnim);
        animPopUp.PlayAnimation(forests);
        animPopUp.PlayAnimation(mountain);
    }

    public void checkPass() {
        if (bgiant == true && bboat == true) {
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.boatp:
                boat.setVisibility(View.INVISIBLE);
                showDialogBoat();
                mediaPlayer = MediaPlayer.create(this, soundBoat[0]);
                mediaPlayer.start();
                bboat = true;
                checkPass();
                break;
            case R.id.giant:
                giantAnim.setVisibility(View.INVISIBLE);
                giant1.setVisibility(View.VISIBLE);
                giantWord.setVisibility(View.VISIBLE);
                bgiant = true;
                countTime(giantWord);
                mediaPlayer = MediaPlayer.create(this, R.raw.yuk);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        checkPass();
                    }
                });

                break;
            case R.id.btn_next:
                intent = new Intent(getApplicationContext(), page2_2.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_pause:
                pauseDialog();
                break;
        }
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

}
