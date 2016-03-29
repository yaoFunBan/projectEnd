package com.example.kimhuang.project;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


/**
 * Created by วัชรัตน์ on 26/2/2559.
 */
public class AnimPopUp {

    public void PlayAnimation(final View target) {

        target.setPivotY(target.getLayoutParams().height);
        new CountDownTimer(1000, 50) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ObjectAnimator anim = ObjectAnimator.ofFloat(target, View.ROTATION_X, 0f);
                anim.setDuration(1000);
                anim.start();
            }
        }.start();

    }
}

