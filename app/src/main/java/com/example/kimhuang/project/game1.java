package com.example.kimhuang.project;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class game1 extends AppCompatActivity implements View.OnClickListener {
    private TextView wordAns;
    Button btn_pause, btnClose;
    Switch swMusic, swEffect;
    CountDownTimer cdt;
    TextView tvTimer, str1, str2, str3;
    //Dialog
    AlertDialog.Builder builder;
    Dialog dialog;
    Button dialogset, dialogexit, dialoghome, dialogclose;
    RelativeLayout ball1, ball2, ball3;
    //Databas
    SQLiteDatabase gameDb;
    datahomony game1;
    Cursor mCursor, wCursor;
    static int i = 0;
    //time
    int time = 50000, tempTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game1);

        tvTimer = (TextView) findViewById(R.id.tvTimer);
        wordAns = (TextView) findViewById(R.id.quustion);
        str1 = (TextView) findViewById(R.id.str1);
        str2 = (TextView) findViewById(R.id.str2);
        str3 = (TextView) findViewById(R.id.str3);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        ball1 = (RelativeLayout) findViewById(R.id.ball1);
        ball2 = (RelativeLayout) findViewById(R.id.ball2);
        ball3 = (RelativeLayout) findViewById(R.id.ball3);


        //decaler database
        game1 = new datahomony(this);
        gameDb = game1.getWritableDatabase();
        game1.onUpgrade(gameDb, 1, 1);

//        //animation ball
//        YoYo.with(Techniques.StandUp)
//                .duration(5000)
//                .playOn(findViewById(R.id.ball1));


        //READ DATA (เป็นการอ่านค่าในตาราง database โดยกำหนดให้ mCursor เลื่อนอ่านข้อมูลในแต่ละคอลัมไปเรื่อยๆ)
        mCursor = gameDb.rawQuery("SELECT * FROM " + game1.TableName, null);
        mCursor.moveToFirst();
        wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));


        //CountDownTimer (โดยจะลดลงครั้งละ 1 วินาที)
        CountDownTimer cdt = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                //เมื่อเวลาเริ่มนับ Cursor จะทำการอ่านค่าจาก Columnไปเรื่อยๆ
                mCursor.moveToPosition(i);
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));

                //ให้วลานับถอยหลังทีละ 1 วินาที
                tempTime = (int) millisUntilFinished;
                tvTimer.setText(String.valueOf(tempTime));
                String strTime = String.format("%1.0f"
                        , (double) millisUntilFinished / 1000);
                tvTimer.setText(String.valueOf(strTime));
            }

            public void onFinish() {
                // Finish
            }
        }.start();


        //button_pause
        btn_pause = (Button) findViewById(R.id.btn_pause);
        builder = new AlertDialog.Builder(this);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.btndialog);
                //TODO findViewBy
                dialogexit = (Button) dialog.findViewById(R.id.btn_exit);
                dialoghome = (Button) dialog.findViewById(R.id.btn_home);
                dialogset = (Button) dialog.findViewById(R.id.btn_setting);
                dialogclose = (Button) dialog.findViewById(R.id.btn_close);

                //button_exit
                dialogexit.setOnClickListener(new View.OnClickListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                });

                //button_home
                dialoghome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), map1.class);
                        startActivity(i);
                    }
                });

                //button_setting
                dialogset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayDiaglogSetting();
                    }
                });

                //button_close
                dialogclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        //event click (เรียกใช้ method onClick)
        ball1.setOnClickListener(this);
        ball2.setOnClickListener(this);
        ball3.setOnClickListener(this);


    }

    //DiaglogSetting
    public void displayDiaglogSetting() {
        final Dialog dsetting = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dsetting.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dsetting.setContentView(R.layout.setting_dialog);

        btnClose = (Button) dsetting.findViewById(R.id.btn_closes);
        swMusic = (Switch) dsetting.findViewById(R.id.sw_music);
        swEffect = (Switch) dsetting.findViewById(R.id.sw_effect);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dsetting.cancel();
            }
        });

        swMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        swEffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Window window = dsetting.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dsetting.show();
    }


    //OnClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case (R.id.ball1):
                incrementmCurser();
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
                str1.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
                break;
            case (R.id.ball2):
                incrementmCurser();
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
                str2.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
                Toast.makeText(getApplicationContext(), "" + mCursor.getPosition(), Toast.LENGTH_LONG).show();
                break;
            case (R.id.ball3):
                incrementmCurser();
                wordAns.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColHomony)));
                str3.setText(mCursor.getString(mCursor.getColumnIndex(game1.ColSemantic)));
                Toast.makeText(getApplicationContext(), "" + mCursor.getPosition(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void incrementmCurser() {
        i++;
        mCursor.moveToPosition(i);
    }

}
