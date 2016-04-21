package com.example.kimhuang.project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class unlock {
    private static final String FileName = "unlock.txt";
    private static boolean[] lock = new boolean[16];
    BufferedReader reader;
    static int i = 0;


    public void readFile(Context ctx) {
        String line = null;
        try {
            final InputStream file = ctx.getAssets().open(FileName);
            reader = new BufferedReader(new InputStreamReader(file));
            line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                lock[i] = Boolean.parseBoolean(line);
                Log.d("StackOverflow", "lock : " + lock[i]);
                i++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeFile(Context ctx) {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(ctx.openFileOutput("config.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        } catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
    }

    public void setUnlock(int index, boolean unlock) {
        lock[index] = unlock;
    }

    public boolean getUnlock(int index) {
        return lock[index];
    }

    public void showAll() {
        for (i = 0; i < lock.length; i++) {
            Log.e("show all", "lock : " + lock[i]);
        }
    }


}