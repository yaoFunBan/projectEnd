package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * Created by วัชรัตน์ on 11/12/2558.
 */
public class scene2_2 extends AppCompatActivity implements View.OnClickListener {

    Button btnNext, btnBack, btnPause, dialogexit, dialoghome, dialogset, dialogclose, btnClose;
    Intent intentNext;
    boolean checkBtn1;
    ToggleButton swMusic, swEffect;
    RelativeLayout LaoutTan;
    ImageView sandThong, poolGold, sandGold, poolNormal, head1, head2, kong22, wall;

    AnimPopUp animPopUp;
    soundBG soundBG;

    unlock unlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene2_2);

        animPopUp = new AnimPopUp();
        soundBG = new soundBG(getApplicationContext());
        soundBG.creatSound();

        unlock = new unlock();


        btnNext = (Button) findViewById(R.id.btn_next);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnPause = (Button) findViewById(R.id.btn_pause);
        sandThong = (ImageView) findViewById(R.id.sandThong);
        LaoutTan = (RelativeLayout) findViewById(R.id.LaoutTan);
        wall = (ImageView) findViewById(R.id.wall);
        head1 = (ImageView) findViewById(R.id.head1);
        head2 = (ImageView) findViewById(R.id.head2);
        kong22 = (ImageView) findViewById(R.id.kong22);
        poolGold = (ImageView) findViewById(R.id.poolG);
        sandGold = (ImageView) findViewById(R.id.sandGold);
        poolNormal = (ImageView) findViewById(R.id.pool_normal);


        ((AnimationDrawable) poolGold.getBackground()).start();

        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        poolGold.setOnClickListener(this);
        head1.setOnClickListener(this);
        btnPause.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_next:
                intentNext = new Intent(getApplicationContext(), page2_3.class);
                unlock.setUnlock(6, true);
                try {
                    unlock.writeFile(scene2_2.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intentNext);
                break;
            case R.id.poolG:
                sandGold.setVisibility(View.VISIBLE);
                sandThong.setVisibility(View.INVISIBLE);
                poolNormal.setVisibility(View.VISIBLE);
                poolGold.setVisibility(View.INVISIBLE);
                head1.setVisibility(View.VISIBLE);
                head2.setVisibility(View.INVISIBLE);

                ((AnimationDrawable) head1.getBackground()).start();
                ((AnimationDrawable) poolGold.getBackground()).stop();
                checkBtn1 = true;
                break;
            case R.id.head1:
                Log.e("show", "here");
                ((AnimationDrawable) poolGold.getBackground()).stop();
                head1.setVisibility(View.INVISIBLE);
                head2.setVisibility(View.VISIBLE);
                sandGold.setVisibility(View.INVISIBLE);
                kong22.setVisibility(View.VISIBLE);
                checkBtn1 = true;
                checkBtn();
                break;
            case R.id.btn_pause:
                pauseDialog();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        poolGold.setVisibility(View.VISIBLE);
        sandThong.setVisibility(View.VISIBLE);
        head2.setVisibility(View.VISIBLE);
        head1.setVisibility(View.INVISIBLE);

        sandGold.setVisibility(View.INVISIBLE);
        kong22.setVisibility(View.INVISIBLE);

        animPopUp.PlayAnimation(sandThong);
        animPopUp.PlayAnimation(wall);
        animPopUp.PlayAnimation(LaoutTan);
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


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();

        ((AnimationDrawable) poolGold.getBackground()).stop();
        ((AnimationDrawable) head1.getBackground()).stop();

        soundBG.stopBG();
    }

    public void checkBtn() {
        if (checkBtn1 == true) {
            btnNext.setEnabled(true);
            btnBack.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ((AnimationDrawable) poolGold.getBackground()).start();

        poolGold.setVisibility(View.VISIBLE);
        sandThong.setVisibility(View.VISIBLE);
        head2.setVisibility(View.VISIBLE);
        head1.setVisibility(View.INVISIBLE);

        sandGold.setVisibility(View.INVISIBLE);
        kong22.setVisibility(View.INVISIBLE);

        sandThong.setRotationX(90f);
        wall.setRotationX(90f);
        LaoutTan.setRotationX(90f);

        animPopUp.PlayAnimation(sandThong);
        animPopUp.PlayAnimation(wall);
        animPopUp.PlayAnimation(LaoutTan);
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
                DialogSetting setting = new DialogSetting(scene2_2.this);
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

