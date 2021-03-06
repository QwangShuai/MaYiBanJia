package com.mingmen.mayi.mayibanjia.ui.fragment.gouwuche.adapter;

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
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.TuiJianBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.ShangPinListAdapter;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class GWCWeiNiTuiJianAdapter extends RecyclerView.Adapter<GWCWeiNiTuiJianAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<TuiJianBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public GWCWeiNiTuiJianAdapter(Context mContext, List<TuiJianBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gouwuche_weinituijian_shangpin, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TuiJianBean data = mList.get(position);
        //holder.tvSpming.setText(data.getClassify_name());
        holder.tvSpming.setText(data.getClassify_name());//商品名称改为三级分类
        holder.tvSpming.setMarqueeEnable(true);
        holder.tvDanjia.setText(data.getPrice()+"");
        if(data.getReal_time_state().equals("0")){
            holder.ivJishida.setVisibility(View.VISIBLE);
        } else {
            holder.ivJishida.setVisibility(View.GONE);
        }

        //holder.tvGuige.setText(data.getPack_standard()+"");
        GlideUtils.cachePhoto(mContext,holder.ivSptu,data.getPicture_url());
        holder.ivSptu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",data.getCommodity_id());
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
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_spming)
        MarqueeTextView tvSpming;
/*        @BindView(R.id.tv_guige)
        TextView tvGuige;*/
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.iv_addcar)
        ImageView ivAddcar;
        @BindView(R.id.iv_jishida)
        ImageView ivJishida;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
