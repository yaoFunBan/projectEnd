package com.example.kimhuang.project;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class example5 extends Fragment {
    ImageView figer5,shellsung;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.example5, container, false);

        //figen
        figer5 = (ImageView) rootView.findViewById(R.id.figer5);
        ((AnimationDrawable) figer5.getBackground()).start();

        //chicken
        shellsung = (ImageView) rootView.findViewById(R.id.shellsung);
        ((AnimationDrawable) shellsung.getBackground()).start();
        return rootView;
    }
}
