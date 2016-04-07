package com.example.kimhuang.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Handler;
import android.widget.TextView;

public class loadgame extends Activity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadgame);

        //Get the widgets reference from XML layout
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
        final TextView tv = (TextView) findViewById(R.id.tv);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    //progressStatus = doWork();
                    progressStatus += 1;

                    //Try to sleep the thread for 20 milliseconds
                    //เวลาในการโหลด
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Update the progress bar
                    handler.post(new Runnable() {
                        public void run() {
                            pb.setProgress(progressStatus);
                            tv.setText(progressStatus + "");
                        }
                    });
                }
            }
        }).start();
    }
}