package com.example.kimhuang.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PolicySpi;

/**
 * Created by วัชรัตน์ on 21/3/2559.
 */
public class CustomAdater extends BaseAdapter {

    private String[] Status;
    private String[] Mean;
    private String[] Word;
    private Context ctx;
    private LayoutInflater inflater;

    public CustomAdater(Context context, String[] w, String[] m, String[] s) {
        this.ctx = context;
        this.Word = w;
        this.Mean = m;
        this.Status = s;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return Mean.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_listview, null);
        holder.mean = (TextView) rowView.findViewById(R.id.text_mean);
        holder.word = (TextView) rowView.findViewById(R.id.text_word);
        holder.img = (ImageView) rowView.findViewById(R.id.img_correct);

        holder.word.setText(Word[position]);
        holder.mean.setText(Mean[position]);

        if (Status[position].equals("correct")) {
            holder.img.setImageResource(R.drawable.correct);
        } else {
            holder.img.setImageResource(R.drawable.uncorrect);
        }
//        holder.tv.setText(result[position]);
//        holder.img.setImageResource(imageId[position]);
//        rowView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
////                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
//            }
//        });
        return rowView;
    }

    public class Holder {
        TextView mean;
        TextView word;
        ImageView img;
    }
}
