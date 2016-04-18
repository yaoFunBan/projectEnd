package com.example.kimhuang.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class map4 extends AppCompatActivity {

    Button btn_back;
    ImageView palaces, houses, mapsamon, bowls;
    boolean unlock41 = false;
    boolean unlock42 = false;
    boolean unlock43 = false;
    boolean unlock44 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map4);

        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, map.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }
        });

        //palaces
        palaces = (ImageView) findViewById(R.id.mapmalai);
        final Intent palace = new Intent(this, page4_11.class);
        palaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlock41) {
                    palaces.setBackgroundResource(R.drawable.city);
                    startActivity(palace);
                }
            }
        });

        //houses
        houses = (ImageView) findViewById(R.id.mapfall);
        final Intent house = new Intent(this, page4_2.class);
        houses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlock42) {
                    houses.setBackgroundResource(R.drawable.house);
                    startActivity(house);
                }
            }
        });

        //mapsamon
        mapsamon = (ImageView) findViewById(R.id.mapsamon);
        final Intent samon = new Intent(this, page3_3.class);
        mapsamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlock44) {
                    mapsamon.setBackgroundResource(R.drawable.mapsamon);
                    startActivity(samon);
                }
            }
        });

        //bowl
        bowls = (ImageView) findViewById(R.id.mapball);
        final Intent bowl = new Intent(this, page3_41.class);
        bowls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unlock44) {
                    bowls.setBackgroundResource(R.drawable.bowl_f2);
                    startActivity(bowl);
                }
            }
        });
    }
}
