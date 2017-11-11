package com.example.administrator.tablayouttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tablayouttest.R;

/**
 * Created by Administrator on 2017/11/7.
 */

public class SubjectRecyclerAdapter extends RecyclerView.Adapter<SubjectRecyclerAdapter.MyViewHolder> {
    private String[]mstr;
    private int[]mint;
    private Context context;

    public SubjectRecyclerAdapter(String[] mstr, int[] mint, Context context) {
        this.mstr = mstr;
        this.mint = mint;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item01, null, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setImageResource(mint[position]);
        holder.tv_main.setText(mstr[position]);
    }

    @Override
    public int getItemCount() {
        return mstr.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_main;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);
            tv_main = (TextView) view.findViewById(R.id.tv_main);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
