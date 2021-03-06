package com.example.kimhuang.project;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by วัชรัตน์ on 21/3/2559.
 */
public class CustomAdater extends BaseExpandableListAdapter implements ExpandableListAdapter {
    private int[] sound;
    private String[] status;
    private static final int GROUP_ITEM_RESOURCE = R.layout.custom_head_exp;
    private static final int CHILD_ITEM_RESOURCE = R.layout.childrow;
    MediaPlayer mediaPlayer[];

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public CustomAdater(Context context, List<String> expandableListTitle,
                        HashMap<String, List<String>> expandableListDetail, String[] status, int[] sound) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.status = status;
        this.sound = sound;

        mediaPlayer = new MediaPlayer[sound.length];

        for (int i = 0; i < sound.length; i++) {
//            Log.e("Show", "Sound : " + sound[i]);
            mediaPlayer[i] = MediaPlayer.create(context, sound[i]);
        }
    }

    // Item group
    @Override
    public int getGroupCount() {
        return expandableListTitle.size();
    }

    int i = 0;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_head_exp, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.word);
        ImageView imgCorect = (ImageView) convertView.findViewById(R.id.status);
        ImageView btnSound = (ImageView) convertView.findViewById(R.id.soundtrack);


        listTitleTextView.setText(listTitle);

        if (status[groupPosition].equals("correct")) {
            imgCorect.setBackgroundResource(R.drawable.correct);
        } else {
            imgCorect.setBackgroundResource(R.drawable.uncorrect);
        }


        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Show", "Sound : " + sound[groupPosition]);
                mediaPlayer[groupPosition].start();
            }
        });
        return convertView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListTitle.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    // Item childen
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).size();
//        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).get(childPosition);
//        return this.expandableListDetail
//        return 1;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.childrow, null);
        }

        TextView listDetailTextView = (TextView) convertView
                .findViewById(R.id.mean);


        listDetailTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
