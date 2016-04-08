package com.example.kimhuang.project;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by วัชรัตน์ on 5/4/2559.
 */
public class BackgroundSound extends AsyncTask<Void, Void, Void> {
    private static final String TAG = null;
    MediaPlayer player;

    @Override
    protected Void doInBackground(Void... params) {
//        player = MediaPlayer.create(this, R.raw.bensound);
//        player.setLooping(true);
//        player.setVolume(50, 50);
        return null;
    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//        player = MediaPlayer.create(this, R.raw.bensound);
//        player.setLooping(true);
//        player.setVolume(50, 50);
//    }
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        player.start();
//        return 1;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        player.start();
//        player.release();
//    }
}
