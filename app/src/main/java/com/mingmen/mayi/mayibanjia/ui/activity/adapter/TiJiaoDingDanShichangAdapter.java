package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TiJiaoDingDanShichangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QueRenDingDanShangPinBean.MarketlistBean> shichanglist;
    private TiJiaoDingDanDianPuAdapter adapter;
    private HeDanCaiGouListAdapter hedanAdapter;
    private View inflate;
    private int type = 0;
    public TiJiaoDingDanShichangAdapter(Context mContext, List<QueRenDingDanShangPinBean.MarketlistBean> list,int type) {
        this.mContext = mContext;
        this.shichanglist = list;
        this.type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type){
            case 0:
                inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_querendingdan_sc, parent, false);
                return new CaiGou(inflate);
            case 1:
                inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hedan_one, parent, false);
                return new HeDan(inflate);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CaiGou) {
            BindCaiGou((CaiGou) holder, position);
        }
        else if (holder instanceof HeDan) {
            BindHeDan((HeDan) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return shichanglist == null ? 0 : shichanglist.size();
    }

    public class CaiGou extends RecyclerView.ViewHolder {
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

        CaiGou(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public class HeDan extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.tv_yf_jine)
        TextView tvYfJine;
        @BindView(R.id.tv_sp_jine)
        TextView tvSpJine;
        @BindView(R.id.rv_list)
        RecyclerView rvList;

        HeDan(View view) {
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

    private void BindCaiGou (final CaiGou holder, int position){
        final QueRenDingDanShangPinBean.MarketlistBean bean = shichanglist.get(position);
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

    private void BindHeDan (final HeDan holder, int position){
        final QueRenDingDanShangPinBean.MarketlistBean bean = shichanglist.get(position);
        holder.tvShichang.setText(bean.getMarket_name());
        holder.tvSpJine.setText(bean.getMoney()+"");
        holder.tvYfJine.setText(bean.getYunfei()+"");
        hedanAdapter= new HeDanCaiGouListAdapter(bean.getCgzhulist());
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvList.setAdapter(hedanAdapter);
        holder.rvList.setFocusable(false);
        hedanAdapter.notifyDataSetChanged();
    }
}
