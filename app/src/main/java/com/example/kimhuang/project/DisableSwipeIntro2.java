package com.example.kimhuang.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DisableSwipeIntro2 extends AppCompatActivity {

    SharedPreferences sp;

    private Button btnNext;
    private Button btnDone;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disable_swip_intro);

        initInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainer, new example())
                .commit();

    }

    private void initInstance() {
        position = 0;
        btnNext = (Button) findViewById(R.id.btnNext);
        btnDone = (Button) findViewById(R.id.btnDone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (position == 0) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainer, new example2())
                             .commit();
                    position += 1;
                } else if (position == 1) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainer, new example3())
                            .commit();
                    position += 1;
                }else if(position == 2) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainer, new example4())
                            .commit();
                    position += 1;
                 btnNext.setVisibility(View.GONE);
                 btnDone.setVisibility(View.VISIBLE);
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(btnDone.getContext(), map.class));
            }
        });
    }
}
