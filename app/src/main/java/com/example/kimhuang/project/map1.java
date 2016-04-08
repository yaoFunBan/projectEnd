package com.example.kimhuang.project;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class map1 extends AppCompatActivity {
    ImageView palaces, house, shellsung, alga1;
    Button btn_back;
    public boolean unlock1_4 = false;
    public boolean unlock1_2 = false;
    public boolean unlock1_3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map1);

        //palaces
        palaces = (ImageView) findViewById(R.id.palaces);
        final Intent i = new Intent(this, page1_1.class);
        palaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        //house
        house = (ImageView) findViewById(R.id.house);
        final Intent c = new Intent(this, page1_2.class);
        if (unlock1_2) {
            house.setBackgroundResource(R.drawable.house);
            house.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(c);
                }
            });
        }

        //trees
        shellsung = (ImageView) findViewById(R.id.shellsung);
        final Intent a = new Intent(this, page1_3.class);
        if (unlock1_3) {
            shellsung.setBackgroundResource(R.drawable.shellsung2);
            shellsung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(a);
                }
            });
        }

        //alga
        final Intent e = new Intent(this, page1_4.class);
        alga1 = (ImageView) findViewById(R.id.alga1);
        if (unlock1_4) {
            alga1.setBackgroundResource(R.drawable.alga1);
            alga1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(e);
                }
            });
        }

        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, map.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }
        });
    }
}
