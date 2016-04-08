package com.example.kimhuang.project;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by วัชรัตน์ on 7/4/2559.
 */
public class startEffect {

    private MediaPlayer player;

    public void checkEffect(Context ctx, boolean swTurnOff) {
        player = MediaPlayer.create(ctx, R.raw.button_click);

        if (swTurnOff) {
            player.start();
        } else {
            player.release();
            player.stop();
        }

    }


}
