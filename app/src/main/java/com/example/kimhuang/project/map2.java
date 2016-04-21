package com.example.kimhuang.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by วัชรัตน์ on 3/2/2559.
 */
public class map2 extends Activity implements View.OnClickListener {
    Intent i;
    Button btn_back;
    ImageView boat, city1, mount, city2;
    unlock unlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map2);

//        unlock = new unlock();

        boat = (ImageView) findViewById(R.id.boatM);
        city1 = (ImageView) findViewById(R.id.castled1);
        mount = (ImageView) findViewById(R.id.mountain);
        city2 = (ImageView) findViewById(R.id.city);

        btn_back = (Button) findViewById(R.id.btn_back);
        final Intent n = new Intent(this, map.class);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(n);
            }
        });

        boat.setOnClickListener(this);
        city1.setOnClickListener(this);
        mount.setOnClickListener(this);
        city2.setOnClickListener(this);

        Unlock();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boatM:
                i = new Intent(getApplicationContext(), page2_1.class);
                startActivity(i);

                break;
            case R.id.castled1:
                i = new Intent(getApplicationContext(), page2_2.class);
                startActivity(i);
                break;
            case R.id.mountain:
                i = new Intent(getApplicationContext(), page2_3.class);
                startActivity(i);

                break;
            case R.id.city:
                i = new Intent(getApplicationContext(), page2_4.class);
                startActivity(i);
                break;
        }
    }

    public void Unlock() {
        if (unlock.getUnlock(4)) {
            boat.setBackgroundResource(R.drawable.boat_map);
            boat.setEnabled(true);
        } else if (unlock.getUnlock(5)) {
            city1.setBackgroundResource(R.drawable.city);
            city1.setEnabled(true);
        } else if (unlock.getUnlock(6)) {
            mount.setBackgroundResource(R.drawable.mountain4_4);
            mount.setEnabled(true);
        } else if (unlock.getUnlock(7)) {
            city2.setBackgroundResource(R.drawable.castles1);
            city2.setEnabled(true);
        }
    }
}
