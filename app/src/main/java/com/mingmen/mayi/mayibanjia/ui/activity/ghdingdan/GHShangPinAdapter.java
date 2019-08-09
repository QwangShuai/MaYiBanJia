package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.GHOrderBean;
import com.mingmen.mayi.mayibanjia.ui.activity.AddQrCodeActivity;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/29.
 */

public class GHShangPinAdapter extends RecyclerView.Adapter<GHShangPinAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<GHOrderBean.ZilistBean> mList;

    public GHShangPinAdapter(Context context, List<GHOrderBean.ZilistBean> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new GHShangPinAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ghshangpin, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GHOrderBean.ZilistBean bean = mList.get(position);
        if(!TextUtils.isEmpty(bean.getSpecial_commodity())){//判断是否有备注
            holder.tv_shangpinbeizhu.setText(bean.getSpecial_commodity());
            holder.tv_teshushangpin.setVisibility(View.VISIBLE);
            holder.tv_shangpinbeizhu.setVisibility(View.VISIBLE);
        } else {
            holder.tv_teshushangpin.setVisibility(View.GONE);
            holder.tv_shangpinbeizhu.setVisibility(View.GONE);
        }
        holder.tv_shangpinming.setMarqueeEnable(true);
        holder.tv_shangpinming.setText(bean.getClassify_name());
        holder.tv_jiliang.setText(bean.getAcount_spec());
        holder.tv_jiage.setText("￥:"+String.valueOf(bean.getAll_price())+"元");
        holder.tv_danjia.setText(bean.getPrice()+"元/"+bean.getSpec_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_teshushangpin)
        TextView tv_teshushangpin;
        @BindView(R.id.tv_shangpinming)
        MarqueeTextView tv_shangpinming;
        @BindView(R.id.tv_jiliang)
        TextView tv_jiliang;
        @BindView(R.id.tv_jiage)
        TextView tv_jiage;
        @BindView(R.id.tv_shangpinbeizhu)
        TextView tv_shangpinbeizhu;
        @BindView(R.id.tv_danjia)
        TextView tv_danjia;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
