package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdxqBean;
import com.mingmen.mayi.mayibanjia.bean.DdxqListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class DdXqShichangAdapter extends RecyclerView.Adapter<DdXqShichangAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DdxqListBean.MarketBean> mList;
    private DdXqDianpuAdapter adapter;
//    private OnItemClickListener mOnItemClickListener;

    public DdXqShichangAdapter(Context mContext, List<DdxqListBean.MarketBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shichang_ddxq,null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.e("我的数据不知道拿到没",new Gson().toJson(mList));
        final DdxqListBean.MarketBean item0 = mList.get(position);
        adapter = new DdXqDianpuAdapter(mContext,item0.getDplist());
        holder.rvDianpu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvDianpu.setAdapter(adapter);
        holder.rvDianpu.setFocusable(false);
        holder.tvZonge.setText("商品总额：  ￥"+item0.getPrice());
        holder.tvYunfei.setText(item0.getFreight_fee());
        if (item0.getOrderState().equals("401")) {//待付款
            holder.rlRongqi.setVisibility(View.GONE);
            holder.tvDaiquhuo.setVisibility(View.GONE);
            holder.tvFahuoshijian.setVisibility(View.GONE);

        } else if(item0.getOrderState().equals("402")){
            holder.rlRongqi.setVisibility(View.GONE);
            holder.tvFahuoshijian.setVisibility(View.GONE);
        }
        else {
            if(TextUtils.isEmpty(item0.getDriver_name())){
                holder.rlRongqi.setVisibility(View.GONE);
                holder.tvDaiquhuo.setText("待配送员取商品");
            } else {
                // 已发货==已完成
                holder.tvDaiquhuo.setVisibility(View.GONE);
                holder.tvPeisongyuan.setText("配送员:" + item0.getDriver_name());
                holder.tvPhone.setText(item0.getDriver_phone());
                holder.tvChepaihao.setText(item0.getPlate_number());
                holder.tvFahuoshijian.setText("发货时间:" + item0.getUpdate_time());
            }
        }
        holder.tvShichang.setText(item0.getMarket_name());
        holder.ivDianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPhone(item0.getDriver_phone());
            }
        });
        holder.btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btShow.getText().toString().equals("展开")){
                    holder.rvDianpu.setVisibility(View.VISIBLE);
                    holder.btShow.setText("收起");
                } else {
                    holder.rvDianpu.setVisibility(View.GONE);
                    holder.btShow.setText("展开");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.bt_show)
        TextView btShow;
        @BindView(R.id.tv_zonge)
        TextView tvZonge;
        @BindView(R.id.tv_yunfei)
        TextView tvYunfei;
        @BindView(R.id.tv_daiquhuo)
        TextView tvDaiquhuo;
        @BindView(R.id.tv_peisongyuan)
        TextView tvPeisongyuan;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.iv_dianhua)
        ImageView ivDianhua;
        @BindView(R.id.ll_rongqi)
        LinearLayout llRongqi;
        @BindView(R.id.tv_chepaihao)
        TextView tvChepaihao;
        @BindView(R.id.tv_fahuoshijian)
        TextView tvFahuoshijian;
        @BindView(R.id.rl_rongqi)
        RelativeLayout rlRongqi;
        @BindView(R.id.rv_dianpu)
        RecyclerView rvDianpu;
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
