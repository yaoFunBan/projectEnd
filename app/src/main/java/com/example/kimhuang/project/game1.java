package com.example.kimhuang.project;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class game1 extends AppCompatActivity {
    CountDownTimer cdt;
    TextView tvTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);

        tvTimer = (TextView)findViewById(R.id.tvTimer);


        CountDownTimer cdt = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                // Finish
            }
        }.start();
    }
}
