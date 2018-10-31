package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;
import com.mingmen.mayi.mayibanjia.ui.activity.dingdan.BaseDingDanFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DingDanBean
 * Created by Administrator on 2018/7/13/013.
 */

public class DingDanXiangQingAdapter extends RecyclerView.Adapter<DingDanXiangQingAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<DingDanBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public DingDanXiangQingAdapter(Context mContext, List<DingDanBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dingdan, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DingDanBean dingdan = mList.get(position);
        holder.tvDingdanbianhao.setText("订单编号："+dingdan.getOrder_number());
        holder.tvZhuangtai.setText(zhuangtai(holder,Integer.parseInt(dingdan.getState())));
        String zongjia = dingdan.getTotal_price() + "";
        if (zongjia.contains("\\.")) {
            holder.tvZongjia1.setText(zongjia.split("\\.")[0]);
            holder.tvZongjia2.setText(zongjia.split("\\.")[1]);
        }else{
            holder.tvZongjia1.setText(zongjia);
            holder.tvZongjia2.setText("00");
        }
//
        int zongshu=0;
        for (int i = 0; i < dingdan.getList().size(); i++) {
            zongshu= zongshu+dingdan.getList().get(i).getAcount();
        }
        holder.tvJianshu.setText("共"+zongshu+"件商品");

        DingDanShangPinAdapter shangpinadapter = new DingDanShangPinAdapter(mContext, dingdan.getList());
        holder.rvShangpinliebiao.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvShangpinliebiao.setAdapter(shangpinadapter);



        if (mOnItemClickListener != null) {
            holder.btFukuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.btPingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.btQuxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.btShouhuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.btZaimai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.ll_saoma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
            holder.ll_rongqi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
        }
    }

    private String zhuangtai(ViewHolder holder, int zhuantai){
        switch (zhuantai){
            case 401:
                holder.btQuxiao.setVisibility(View.VISIBLE);
                holder.btFukuan.setVisibility(View.GONE);
                return "待买家付款";
//已付款409  已评价 408  未评价  407 已取消  406  已收货 405 已发货404  待发货 402 待付款 401
            case 402:
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "待卖家发货";
            //待发货
            case 404:
                holder.btShouhuo.setVisibility(View.VISIBLE);
                return "卖家已发货";
            //已发货
            case 405:
                holder.btPingjia.setVisibility(View.VISIBLE);
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "买家已收货";
            case 407:
                holder.btPingjia.setVisibility(View.VISIBLE);
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "未评价";
            case 409:
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "买家已付款";
            case 406:
                holder.btZaimai.setVisibility(View.VISIBLE);
                return "已取消";
        }
        return "";
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_dingdanbianhao)
        TextView tvDingdanbianhao;
        @BindView(R.id.tv_zhuangtai)
        TextView tvZhuangtai;
        @BindView(R.id.iv_shanchu)
        ImageView ivShanchu;
        @BindView(R.id.rv_shangpinliebiao)
        RecyclerView rvShangpinliebiao;
        @BindView(R.id.tv_jianshu)
        TextView tvJianshu;
        @BindView(R.id.tv_zongjia1)
        TextView tvZongjia1;
        @BindView(R.id.tv_zongjia2)
        TextView tvZongjia2;
        @BindView(R.id.bt_quxiao)
        Button btQuxiao;
        @BindView(R.id.bt_fukuan)
        Button btFukuan;
        @BindView(R.id.bt_shouhuo)
        Button btShouhuo;
        @BindView(R.id.bt_zaimai)
        Button btZaimai;
        @BindView(R.id.bt_pingjia)
        Button btPingjia;
        @BindView(R.id.ll_saoma)
        LinearLayout ll_saoma;
        @BindView(R.id.ll_rongqi)
        LinearLayout ll_rongqi;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
