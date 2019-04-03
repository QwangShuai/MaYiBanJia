package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DianPuZhanShiBean;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * String
 * Created by Administrator on 2018/7/13/013.
 */
//店铺展示商品list
public class DianPuZhanShiAdapter extends RecyclerView.Adapter<DianPuZhanShiAdapter.ViewHolder> {


    private ViewHolder viewHolder;
    private Context mContext;
    private List<DianPuZhanShiBean.CompanyListBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public DianPuZhanShiAdapter(Context mContext, List<DianPuZhanShiBean.CompanyListBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dianpuzhanshi, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {//店铺商品列表
        final DianPuZhanShiBean.CompanyListBean data = mList.get(position);
        Glide.with(mContext).load(data.getHostphoto()).into(holder.ivSptu);
        holder.tvSpming.setMarqueeEnable(true);
        holder.tvSpming.setText(data.getClassify_name());
        holder.tvJiage.setText(data.getPice_one());
//        holder.tvDianpuming.setText(data.getCompany_name());
        holder.tvSpxiaoliang.setText("已售"+data.getCommodity_sales());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",data.getCommodity_id());
                Log.e("spid",data.getCommodity_id()+"---");
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
        if (mOnItemClickListener != null) {
            holder.ivAddcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.ivZoushitu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_spming)
        MarqueeTextView tvSpming;
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.tv_spxiaoliang)
        TextView tvSpxiaoliang;
        @BindView(R.id.iv_addcar)
        ImageView ivAddcar;
        @BindView(R.id.iv_zoushitu)
        ImageView ivZoushitu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
