package com.example.kimhuang.project;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

/**
 * Created by วัชรัตน์ on 2/5/2559.
 */
public class DialogSetting extends Dialog implements View.OnClickListener {
    Button btnClose;
    ToggleButton swMusic, swEffect;
    Dialog dsetting;
    soundBG soundBG;
    Activity activity;

    public DialogSetting(Activity activity) {
        super(activity);

        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_dialog);

        btnClose = (Button) findViewById(R.id.btn_closes);
        swMusic = (ToggleButton) findViewById(R.id.sw_music);
        swEffect = (ToggleButton) findViewById(R.id.sw_effect);

        soundBG = new soundBG(activity);

        btnClose.setOnClickListener(this);
        swMusic.setOnClickListener(this);
        swEffect.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                cancel();
                break;
            case R.id.sw_music:
                soundBG.turnOnSound();
                break;
            case R.id.sw_effect:
                break;
        }
    }
}
