package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DianPuBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/22.
 */

public class DianPuGuanZhuAdapter extends RecyclerView.Adapter<DianPuGuanZhuAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DianPuBean> mList;

    public DianPuGuanZhuAdapter(Context mContext, List<DianPuBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dianpushoucang, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DianPuBean bean = mList.get(position);
        GlideUtils.cachePhoto(mContext,holder.ivTu,bean.getPhoto());
        holder.tvDianming.setText(bean.getCompany_name());
        holder.rbPingfen.setRating(bean.getEvaluation());
        holder.rbPingfen.setSelected(false);
        holder.tvPingfen.setText(String.valueOf(bean.getEvaluation()));
        holder.tvGuanzhu.setText(bean.getAttention_number()+"人关注");
        holder.tvShichangming.setText(bean.getMarket_name());
        holder.tvDizhi.setText(bean.getSpecific_address());
        if(StringUtil.isValid(bean.getRealtime())&&bean.getRealtime().equals("0")){
            holder.ivJishida.setVisibility(View.VISIBLE);
        } else {
            holder.ivJishida.setVisibility(View.GONE);
        }
        //进店点击事件
        holder.clRq.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.iv_tu)
        ImageView ivTu;
        @BindView(R.id.iv_jishida)
        ImageView ivJishida;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.rb_pingfen)
        RatingBar rbPingfen;
        @BindView(R.id.tv_pingfen)
        TextView tvPingfen;
        @BindView(R.id.tv_guanzhu)
        TextView tvGuanzhu;
        @BindView(R.id.tv_shichangming)
        TextView tvShichangming;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.ll_didian)
        LinearLayout llDidian;
        @BindView(R.id.tv_jindian)
        ImageView tvJindian;
        @BindView(R.id.cl_rq)
        ConstraintLayout clRq;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
