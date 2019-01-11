package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.HeDanCaiGouListBean;
import com.mingmen.mayi.mayibanjia.bean.HeDanShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class HeDanShiChangAdapter extends RecyclerView.Adapter<HeDanShiChangAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QueRenDingDanShangPinBean.MarketlistBean> mList;
    private HeDanCaiGouListAdapter adapter;

    public HeDanShiChangAdapter(Context mContext,List<QueRenDingDanShangPinBean.MarketlistBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hedan_one, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QueRenDingDanShangPinBean.MarketlistBean bean = mList.get(position);
        holder.tvShichang.setText(bean.getMarket_name());
        holder.tvSpJine.setText(bean.getMoney()+"");
        holder.tvYfJine.setText(bean.getYunfei()+"");
        adapter= new HeDanCaiGouListAdapter(bean.getCgzhulist());
        holder.rvList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvList.setAdapter(adapter);
        holder.rvList.setFocusable(false);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_shichang)
        TextView tvShichang;
        @BindView(R.id.tv_yf_jine)
        TextView tvYfJine;
        @BindView(R.id.tv_sp_jine)
        TextView tvSpJine;
        @BindView(R.id.rv_list)
        RecyclerView rvList;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
