package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdShichangBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/15.
 */

public class DdShichangAdapter extends RecyclerView.Adapter<DdShichangAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DdShichangBean> mList;

    public DdShichangAdapter(Context context, List<DdShichangBean> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shichang_dd, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DdShichangBean bean = mList.get(position);
        Log.e("数据咋回事啊",new Gson().toJson(bean));
        holder.tvShichang.setText(bean.getMarket_name());
        holder.tvZonge.setText("商品总额：  ￥"+bean.getPrice());
        holder.tvYunfei.setText(bean.getFreight_fee());
        if(TextUtils.isEmpty(bean.getFreight_fee())||bean.getFreight_fee()==null){
            holder.llYunfei.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.tv_zonge)
        TextView tvZonge;
        @BindView(R.id.tv_yunfei)
        TextView tvYunfei;
        @BindView(R.id.ll_yunfei)
        LinearLayout llYunfei;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
