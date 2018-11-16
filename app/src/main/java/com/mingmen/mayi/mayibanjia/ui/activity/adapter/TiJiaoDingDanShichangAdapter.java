package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiBean;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class TiJiaoDingDanShichangAdapter extends RecyclerView.Adapter<TiJiaoDingDanShichangAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QueRenDingDanShangPinBean> shichanglist;
    private TiJiaoDingDanDianPuAdapter adapter;
    public TiJiaoDingDanShichangAdapter(Context mContext, List<QueRenDingDanShangPinBean> list) {
        this.mContext = mContext;
        this.shichanglist = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_querendingdan_sc, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QueRenDingDanShangPinBean bean = shichanglist.get(position);
        adapter = new TiJiaoDingDanDianPuAdapter(mContext,bean.getDplist());
        holder.rvDianpu.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvDianpu.setAdapter(adapter);
        holder.rvDianpu.setFocusable(false);


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
        holder.tvZonge.setText("商品总额：  ￥"+bean.getMoney());
        if(bean.getYunfei()!=null){
            holder.tvYunfei.setText(bean.getYunfei()+"");
        }
        holder.tvShichang.setText(bean.getMarket_name());
    }

    @Override
    public int getItemCount() {
        return shichanglist == null ? 0 : shichanglist.size();
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
        @BindView(R.id.rv_dianpu)
        RecyclerView rvDianpu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setYunfei(List<YunFeiBean> data) {
        for (int i=0;i<data.size();i++){
            shichanglist.get(i).setYunfei(BigDecimal.valueOf(data.get(i).getMoney()));
            notifyDataSetChanged();
        }

    }
}
