package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * Created by วัชรัตน์ on 11/12/2558.
 */
public class scene2_3 extends AppCompatActivity implements View.OnClickListener {

    Button btnNext, btnBack, btnPause, dialogexit, dialoghome, dialogset, dialogclose, btnClose;
    Intent intentNext;
    ImageView sira, sira2, mountain, pasang, giant;
    RelativeLayout tree;
    ToggleButton swMusic, swEffect;
    boolean goneB = false;
    int pIndex = 0;
    int[] resBoat = {R.drawable.kung, R.drawable.gone};
    MediaPlayer mp;
    AnimPopUp animPopUp;
    unlock unlock;
    soundBG soundBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene2_3);


        animPopUp = new AnimPopUp();
        unlock = new unlock();
        soundBG = new soundBG(getApplicationContext());
        soundBG.creatSound();
        mp = MediaPlayer.create(scene2_3.this, R.raw.kung);
        mountain = (ImageView) findViewById(R.id.mountain);
        pasang = (ImageView) findViewById(R.id.pasang);
        tree = (RelativeLayout) findViewById(R.id.tree);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnPause = (Button) findViewById(R.id.btn_pause);
        sira = (ImageView) findViewById(R.id.sira);
        sira2 = (ImageView) findViewById(R.id.sira2);
        giant = (ImageView) findViewById(R.id.giant);

        ((AnimationDrawable) sira.getBackground()).start();

        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        sira.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    public void showDialogGone() {
        final Dialog dialog = new Dialog(scene2_3.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.gonelayout);
        dialog.setCancelable(false);
        final Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        final Button btnNext = (Button) dialog.findViewById(R.id.btnNext);
        final Button btnBack = (Button) dialog.findViewById(R.id.btnBack);
        final ImageView imgGone = (ImageView) dialog.findViewById(R.id.imgGone);

        imgGone.setImageResource(resBoat[0]);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                goneB = true;
                checkPass();
                mp.stop();
                sira.setVisibility(View.VISIBLE);
                ((AnimationDrawable) sira.getBackground()).start();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pIndex == resBoat.length - 1) {
                    imgGone.setImageResource(resBoat[0]);
                    pIndex = 0;
                } else {
                    imgGone.setImageResource(resBoat[++pIndex]);
                    mp.start();

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pIndex == 0) {
                    imgGone.setImageResource(resBoat[resBoat.length - 1]);
                    pIndex = resBoat.length - 1;
                    mp.start();
                } else {
                    imgGone.setImageResource(resBoat[--pIndex]);

                }
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        sira.setVisibility(View.VISIBLE);
        sira2.setVisibility(View.INVISIBLE);

        animPopUp.PlayAnimation(sira);
        animPopUp.PlayAnimation(mountain);
        animPopUp.PlayAnimation(pasang);
        animPopUp.PlayAnimation(tree);
        animPopUp.PlayAnimation(giant);

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();


        ((AnimationDrawable) sira.getBackground()).stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();


        ((AnimationDrawable) sira.getBackground()).stop();

        soundBG.stopBG();
    }

    public void checkPass() {
        if (goneB == true) {
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((AnimationDrawable) sira.getBackground()).start();

        sira.setRotationX(90f);
        mountain.setRotationX(90f);
        pasang.setRotationX(90f);
        tree.setRotationX(90f);
        giant.setRotationX(90f);

        animPopUp.PlayAnimation(sira);
        animPopUp.PlayAnimation(mountain);
        animPopUp.PlayAnimation(pasang);
        animPopUp.PlayAnimation(tree);
        animPopUp.PlayAnimation(giant);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
                intentNext = new Intent(getApplicationContext(), page2_4.class);
                unlock.setUnlock(7, true);
                try {
                    unlock.writeFile(scene2_3.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intentNext);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.sira:
                ((AnimationDrawable) sira.getBackground()).stop();
                sira.setVisibility(View.GONE);
                sira2.setVisibility(View.VISIBLE);
                showDialogGone();
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
                DialogSetting setting = new DialogSetting(scene2_3.this);
                setting.show();

                Window window = setting.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                window.setGravity(Gravity.CENTER);
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
}
