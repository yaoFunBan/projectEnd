package com.example.kimhuang.project;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by วัชรัตน์ on 1/5/2559.
 */
public class soundBG {
    private static boolean chSound = true;
    private Context context;
    private static MediaPlayer playSound;

    public soundBG(Context ctx) {
        this.context = ctx;
    }


    public void turnOnSound() {
        if (this.chSound) {
            playSound.stop();
            playSound.release();
            playSound = null;
            this.chSound = false;
        } else {
            creatSound();
            this.chSound = true;
        }
    }

    public void creatSound() {
        playSound = MediaPlayer.create(this.context, R.raw.bensound);
        playSound.start();
    }
}
