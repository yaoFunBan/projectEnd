package com.example.kimhuang.project;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class example2 extends Fragment {
    ImageView figer2, women1;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.example2, container, false);
        //arrow
        figer2 = (ImageView) rootView.findViewById(R.id.figer2);
        ((AnimationDrawable) figer2.getBackground()).start();

        //women1
        women1 = (ImageView) rootView.findViewById(R.id.women1);
        ((AnimationDrawable) women1.getBackground()).start();
        return rootView;
    }
}
