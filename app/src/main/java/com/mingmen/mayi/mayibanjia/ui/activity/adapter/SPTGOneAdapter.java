package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.SPTGBean;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiChengGongActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class SPTGOneAdapter extends RecyclerView.Adapter<SPTGOneAdapter.ViewHolder> {

    private ShenPiChengGongActivity activity;
    private ViewHolder viewHolder;
    private List<SPTGBean.FllistBean> mList;
    private SPTGOneTwoAdapter adapter;
    private Context mContext;
    private boolean itemIsClick[];
    public SPTGOneAdapter(List<SPTGBean.FllistBean> mList, Context mContext, ShenPiChengGongActivity activity) {
        this.mList = mList;
        this.mContext = mContext;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shenpi_tongguo_one, parent, false));
        itemIsClick = new boolean[mList==null?0:mList.size()];
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SPTGBean.FllistBean bean = mList.get(position);
        holder.tvPinlei.setText(bean.getClassify_name()+"");
        holder.tvShichang.setText(bean.getMarket_name()+"");
        adapter = new SPTGOneTwoAdapter(bean.getSonorderlist(),mContext,activity);
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvList.setAdapter(adapter);
        holder.rvList.setFocusable(false);
        holder.rvList.setNestedScrollingEnabled(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemIsClick[position]) {
                    itemIsClick[position] = false;
                    holder.rvList.setVisibility(View.GONE);
                    holder.llRongqi.setVisibility(View.GONE);
                    holder.ivJinru.setImageResource(R.mipmap.jinru);
                } else {
                    itemIsClick[position] = true;
                    holder.rvList.setVisibility(View.VISIBLE);
                    holder.llRongqi.setVisibility(View.VISIBLE);
                    holder.ivJinru.setImageResource(R.mipmap.xia_kongxin_hui);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pinlei)
        TextView tvPinlei;
        @BindView(R.id.iv_jinru)
        ImageView ivJinru;
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.rv_list)
        RecyclerView rvList;
        @BindView(R.id.ll_rongqi)
        LinearLayout llRongqi;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
