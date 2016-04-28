package com.example.kimhuang.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class map3 extends AppCompatActivity {
    Button btn_back;
    Button mapsamon, mapfall, mapmalai, mapball;
    unlock unlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map3);

        unlock = new unlock();

        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, map.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }
        });

        //mapmalai
        mapmalai = (Button) findViewById(R.id.mapmalai);
        final Intent malai = new Intent(this, page3_11.class);
        mapmalai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(malai);
            }
        });

        //mapwaterfall
        mapfall = (Button) findViewById(R.id.mapfall);
        final Intent fall = new Intent(this, page3_21.class);
        mapfall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(fall);

            }
        });

        //mapsamon
        mapsamon = (Button) findViewById(R.id.mapsamon);
        final Intent samon = new Intent(this, page3_3.class);
        mapsamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(samon);

            }
        });

        //mapball
        mapball = (Button) findViewById(R.id.mapball);
        final Intent ball = new Intent(this, page3_41.class);
        mapball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ball);

            }
        });

        Unlock();

    }

    public void Unlock() {
        unlock.readFile(getApplicationContext());
        boolean chlock[] = unlock.getUnlock3();
        for (int i = 0; i < chlock.length; i++) {
            if (chlock[0]) {
                mapmalai.setBackgroundResource(R.drawable.mapmalai);
                mapmalai.setEnabled(true);
            }
            if (chlock[1]) {
                mapfall.setBackgroundResource(R.drawable.mapfall);
                mapfall.setEnabled(true);
            }
            if (chlock[2]) {
                mapsamon.setBackgroundResource(R.drawable.mapsamon);
                mapsamon.setEnabled(true);
            }

            if (chlock[3]) {
                mapball.setBackgroundResource(R.drawable.mapball);
                mapball.setEnabled(true);
            }
        }
    }
}
