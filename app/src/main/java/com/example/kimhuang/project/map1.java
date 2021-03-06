package com.example.kimhuang.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public class map1 extends AppCompatActivity {
    Button palaces, house, shellsung, alga1;
    Button btn_back;
    unlock unlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map1);

        unlock = new unlock();

        //palaces
        palaces = (Button) findViewById(R.id.palaces);
        final Intent i = new Intent(this, page1_1.class);
        palaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

//        //house
        house = (Button) findViewById(R.id.house);
        final Intent c = new Intent(this, page1_2.class);
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(c);
            }
        });


//        //trees
        shellsung = (Button) findViewById(R.id.shellsung);
        final Intent a = new Intent(this, page1_3.class);
        shellsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a);
            }
        });


//        alga
        final Intent e = new Intent(this, page1_4.class);
        alga1 = (Button) findViewById(R.id.alga1);
        alga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(e);
            }
        });

        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, map.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }

        });


        Unlock();
    }

    //ล็อคด่านอื่นๆ
    public void Unlock() {
        unlock.readFile(getApplicationContext());
        boolean chlock[] = unlock.getUnlock1();
        for (int i = 0; i < chlock.length; i++) {
            if (chlock[1]) {
                house.setBackgroundResource(R.drawable.house);
                house.setEnabled(true);
            }
            if (chlock[2]) {
                shellsung.setBackgroundResource(R.drawable.shellsung2);
                shellsung.setEnabled(true);
            }
            if (chlock[3]) {
                alga1.setBackgroundResource(R.drawable.alga1);
                alga1.setEnabled(true);
            }
        }
    }
}
