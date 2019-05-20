package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.QiYeLieBiaoBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.DingDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ZhuceShangjiaAdapter extends RecyclerView.Adapter<ZhuceShangjiaAdapter.ViewHolder> {


    private ViewHolder viewHolder;
    private Context mContext;
    private List<QiYeLieBiaoBean> mList;
    private OnItemClickListener mOnItemClickListener;
    private Intent it;

    public ZhuceShangjiaAdapter(Context mContext, List<QiYeLieBiaoBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhuceshangjia, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final QiYeLieBiaoBean data = mList.get(position);
        holder.tvMingzi.setText(data.getCompany_name());
        if(StringUtil.isValid(data.getLeiBieName())){
            holder.tvLeibie.setText(data.getLeiBieName());
        } else {
            holder.tvLeibie.setVisibility(View.GONE);
        }
        if (0!=data.getEvaluation()){
            holder.rbPingfen.setRating(data.getEvaluation());
        } else {
            holder.rbPingfen.setVisibility(View.GONE);
        }
        if(StringUtil.isValid(data.getGuiMoId())){
            holder.tvGuimo.setText("餐位数：" + data.getGuiMoId());
        } else {
            holder.tvGuimo.setText("市场："+ data.getSon_number());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getRole().equals("1")){

                } else {
                    Bundle bundle = new Bundle();
                    Intent jindian = new Intent(mContext, DianPuActivity.class);
                    bundle.putString("dianpuid", data.getCompany_id());
                    jindian.putExtras(bundle);
                    mContext.startActivity(jindian);
                }

            }
        });
        GlideUtils.cachePhoto(mContext,holder.ivTouxiang,data.getPhoto());
/*        if (data.getSpecific_address() != null) {
            holder.tvDizhi.setText(data.getQuYMC() + data.getQuYMCa() + data.getQuYMCb() + data.getQuYMCc() + data.getSpecific_address());
        } else {
            holder.tvDizhi.setText(data.getQuYMC() + data.getQuYMCa() + data.getQuYMCb() + data.getQuYMCc());
        }*/
        holder.tvDizhi.setText(data.getSpecific_address());

//        if (mOnItemClickListener != null) {

            holder.llPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallPhone(data.getTelephone() + "");
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_touxiang)
        ImageView ivTouxiang;
        @BindView(R.id.tv_mingzi)
        TextView tvMingzi;
        @BindView(R.id.ll_phone)
        LinearLayout llPhone;
        @BindView(R.id.tv_leibie)
        TextView tvLeibie;
        @BindView(R.id.tv_guimo)
        TextView tvGuimo;
        @BindView(R.id.rb_pingfen)
        RatingBar rbPingfen;
        @BindView(R.id.tv_pingfen)
        TextView tvPingfen;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
