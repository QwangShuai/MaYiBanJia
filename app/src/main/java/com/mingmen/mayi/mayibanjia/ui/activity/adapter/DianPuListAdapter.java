package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */
// 搜索结果页面店铺列表
public class DianPuListAdapter extends  RecyclerView.Adapter<DianPuListAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<DianPuBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public DianPuListAdapter(Context mContext, List<DianPuBean> list) {
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
    public DianPuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dianpuliebiao, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DianPuBean bean = mList.get(position);
        //店铺名
        holder.tv_dianming.setText(bean.getCompany_name());
        Glide.with(mContext).load(bean.getPhoto()).into(holder.iv_tu);
        holder.rb_pingfen.setRating(bean.getEvaluation());
        holder.tv_pingfen.setText(String.valueOf(bean.getEvaluation()));
        holder.tv_yishou.setText("已售"+bean.getCommodity_sales());
        holder.tv_shichangming.setText(bean.getMarket_name());
        holder.tv_dizhi.setText(bean.getSpecific_address());

        if (mOnItemClickListener!=null){
            holder.iv_tu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
        }
        //进店点击事件
       holder.tv_jindian.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent jindian=new Intent(mContext,DianPuActivity.class);
               Bundle bundle=new Bundle();
               bundle.putString("dianpuid",bean.getCompany_id());
               jindian.putExtras(bundle);
               mContext.startActivity(jindian);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_tu;
        TextView tv_dianming;
        TextView tv_pingfen;
        TextView tv_yishou;
        RatingBar rb_pingfen;
        TextView tv_shichangming;
        TextView tv_dizhi;
        TextView tv_jindian;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            iv_tu=(ImageView) view.findViewById(R.id.iv_tu);
            tv_dianming=(TextView) view.findViewById(R.id.tv_dianming);
            tv_pingfen=(TextView)view.findViewById(R.id.tv_pingfen);
            tv_yishou=(TextView)view.findViewById(R.id.tv_yishou);
            rb_pingfen=(RatingBar) view.findViewById(R.id.rb_pingfen);
            tv_shichangming=(TextView)view.findViewById(R.id.tv_shichangming);
            tv_dizhi=(TextView)view.findViewById(R.id.tv_dizhi);
            tv_jindian=(TextView)view.findViewById(R.id.tv_jindian);
        }
    }

}
