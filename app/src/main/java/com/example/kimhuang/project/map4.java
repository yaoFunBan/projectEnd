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
    unlock unlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map4);

//        unlock = new unlock();
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

        Unlock();
    }

    public void Unlock() {
        if (unlock.getUnlock(4)) {
            palaces.setBackgroundResource(R.drawable.palaces);
            palaces.setEnabled(true);
        } else if (unlock.getUnlock(5)) {
            houses.setBackgroundResource(R.drawable.house);
            houses.setEnabled(true);
        } else if (unlock.getUnlock(6)) {
            mapsamon.setBackgroundResource(R.drawable.mapsamon);
            mapsamon.setEnabled(true);
        } else if (unlock.getUnlock(7)) {
            bowls.setBackgroundResource(R.drawable.bowl_f2);
            bowls.setEnabled(true);
        }
    }
}
