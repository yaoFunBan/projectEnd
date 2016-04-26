package com.example.kimhuang.project;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class unlock {
    private static final String FileName = "unlock.txt";
    private static boolean[] lock = new boolean[16];
    BufferedReader reader;
    BufferedWriter writer;
    static int i = 0;


    public void readFile(Context ctx) {
        String line = null;
        i = 0;
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

            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void writeFile(Context ctx) throws IOException {
        try {
            FileOutputStream oFile = ctx.openFileOutput(FileName, Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(oFile);
            writer.write("test");
            for (i = 0; i < lock.length; i++) {
                writer.write(String.valueOf(lock[i]));
                Log.d("Log" + i, "value = " + lock[i]);
            }



            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUnlock(int index, boolean unlock) {
        this.lock[index] = unlock;
        Log.d("lock " + index, " is " + lock[index]);
    }

    public boolean getUnlock(int index) {
        return lock[index];
    }

    public void showAll() {
        Log.e("show all", "lock : " + lock[3]);

    }
}