package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdxqListBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class DdXqShangpinAdapter extends RecyclerView.Adapter<DdXqShangpinAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DdxqListBean.MarketBean.DplistBean.ListspBean> mList;
//    private OnItemClickListener mOnItemClickListener;

    public DdXqShangpinAdapter(Context mContext, List<DdxqListBean.MarketBean.DplistBean.ListspBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangpin_ddxq, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DdxqListBean.MarketBean.DplistBean.ListspBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getHostPicture()).into(holder.ivSptu);
        //holder.tvSpming.setText(bean.getClassify_name());
        holder.tvSpming.setText(bean.getClassify_name());
        holder.tvGuige.setText(bean.getPackStandard());
        holder.tvJiage.setText(bean.getPrice());
        if(TextUtils.isEmpty(bean.getAppend_money()+"")||bean.getAppend_money()==null){
            holder.tvFujiafei.setVisibility(View.GONE);
        } else {
            holder.tvFujiafei.setText("(附加费："+bean.getAppend_money()+"元)");
            holder.tvFujiafei.setVisibility(View.VISIBLE);
        }
        holder.tvShuliang.setText("x"+bean.getAcount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",bean.getCommodity_id());
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_fujiafei)
        TextView tvFujiafei;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
