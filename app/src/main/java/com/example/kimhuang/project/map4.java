package com.example.kimhuang.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class map4 extends AppCompatActivity {

    Button btn_back;
    Button palaces, houses, mapsamon, bowls;

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
        palaces = (Button) findViewById(R.id.palaces);
        final Intent palace = new Intent(this, page4_11.class);
        palaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(palace);
            }
        });

        //houses
        houses = (Button) findViewById(R.id.house);
        final Intent house = new Intent(this, page4_2.class);
        houses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(house);
            }
        });

        //mapsamon
        mapsamon = (Button) findViewById(R.id.samon);
        final Intent samon = new Intent(this, page3_3.class);
        mapsamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(samon);
            }
        });

        //bowl
        bowls = (Button) findViewById(R.id.bowl);
        final Intent bowl = new Intent(this, page3_41.class);
        bowls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(bowl);
            }
        });
    }
}
