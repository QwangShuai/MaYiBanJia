package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGGuige;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class StringAdapter extends  RecyclerView.Adapter<StringAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;
    public StringAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void setData(ArrayList<String> guigedatas) {
        this.mList=guigedatas;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public StringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mohuchaxun, parent, false));
        return viewHolder;
    }
//    item_sousuomohuchaxun
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String data = mList.get(position);
        holder.tv_ming.setText(data+"");
        if (mOnItemClickListener!=null){
            holder.tv_ming.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_ming;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            tv_ming=(TextView)view.findViewById(R.id.tv_ming);

        }
    }

}
