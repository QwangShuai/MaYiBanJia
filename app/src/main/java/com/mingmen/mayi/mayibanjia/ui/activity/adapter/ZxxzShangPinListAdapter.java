package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShangPinSouSuoBean;
import com.mingmen.mayi.mayibanjia.bean.ZxxzShangpinBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TubiaoActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ZxxzShangPinListAdapter extends RecyclerView.Adapter<ZxxzShangPinListAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ZxxzShangpinBean> mList;
    private OnItemClickListener mOnItemClickListener;


    public ZxxzShangPinListAdapter(Context mContext, List<ZxxzShangpinBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangpin_zxxz, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ZxxzShangpinBean data = mList.get(position);
        holder.tvSpming.setText(data.getClassify_name());
        if(data.getGoods().equals("1")){
            holder.tvYuanjiage.setVisibility(View.VISIBLE);
            holder.tvYuanjiage.setText(data.getPice());
            holder.tvYuanjiage.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvYuanjiage.setVisibility(View.GONE);
        }
        holder.tvGuigeMiaoshu.setVisibility(StringUtil.isValid(data.getPack_standard())?View.VISIBLE:View.GONE);
        if(StringUtil.isValid(data.getPack_standard())){
            holder.tvGuigeMiaoshu.setText(data.getPack_standard());
        }
        if(StringUtil.isValid(data.getAttention_state())&&data.getAttention_state().equals("0")){
            holder.tvGuanzhu.setVisibility(View.VISIBLE);
        } else {
            holder.tvGuanzhu.setVisibility(View.GONE);
        }

        if(StringUtil.isValid(data.getGold_supplier())&&data.getGold_supplier().equals("0")){
            holder.tvDian.setVisibility(View.GONE);
            holder.ivJpgys.setVisibility(View.VISIBLE);
        } else {
            holder.tvDian.setVisibility(View.VISIBLE);
            holder.ivJpgys.setVisibility(View.GONE);
        }
        holder.tvDianming.setText(data.getCompany_name()+"("+data.getMarket_name()+")");
        holder.tvJiage.setText(data.getPrice());
        holder.tvSpxiaoliang.setText("已售"+data.getCommodity_sales());
        holder.ivJishida.setVisibility(View.GONE);
        GlideUtils.cachePhoto(mContext,holder.ivSptu,data.getPicture_url());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(v,position);
            }
        });
        holder.tvXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",data.getCommodity_id());
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
//        @BindView(R.id.tv_spming)
//        MarqueeTextView tvSpming;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.tv_guige_miaoshu)
        TextView tvGuigeMiaoshu;
/*        @BindView(R.id.tv_guige)
        TextView tvGuige;*/
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_guanzhu)
        TextView tvGuanzhu;
        @BindView(R.id.tv_xiangqing)
        TextView tvXiangqing;
        @BindView(R.id.tv_jiage)
        TextView tvJiage;
        @BindView(R.id.tv_yuanjiage)
        TextView tvYuanjiage;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_spxiaoliang)
        TextView tvSpxiaoliang;
        @BindView(R.id.tv_dian)
        TextView tvDian;
        @BindView(R.id.iv_jishida)
        ImageView ivJishida;
        @BindView(R.id.iv_jpgys)
        ImageView ivJpgys;
        @BindView(R.id.cl_kuang)
        RelativeLayout clKuang;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
