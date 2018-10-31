package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * String
 * Created by Administrator on 2018/7/13/013.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<String> mList;
    private final int layout;
    public DemoAdapter(Context mContext, List<String> list,int layout) {
        this.mContext = mContext;
        this.mList = list;
        this.layout=layout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String data = mList.get(position);
//
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
