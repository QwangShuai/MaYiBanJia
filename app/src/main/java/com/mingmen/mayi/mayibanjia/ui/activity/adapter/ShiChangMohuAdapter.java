package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShiChangBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ShiChangMohuAdapter extends  RecyclerView.Adapter<ShiChangMohuAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<ShiChangBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public ShiChangMohuAdapter(Context mContext, List<ShiChangBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public ShiChangMohuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mohuchaxun, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ShiChangBean shiChangBean = mList.get(position);
        holder.tv_shichangming.setText(shiChangBean.getMarket_name());

//        holder.tv_shanghushuliang.setText("商户数量："+shiChangBean.get);
        if (mOnItemClickListener!=null){
            holder.tv_shichangming.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_shichangming;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_shichangming=(TextView)view.findViewById(R.id.tv_shichangming);

        }
    }

}
