package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.HeDanCaiGouListBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class HeDanCaiGouListAdapter extends RecyclerView.Adapter<HeDanCaiGouListAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private List<QueRenDingDanShangPinBean.MarketlistBean.CgzhulistBean> mList;

    public HeDanCaiGouListAdapter(List<QueRenDingDanShangPinBean.MarketlistBean.CgzhulistBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hedan_two, null, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QueRenDingDanShangPinBean.MarketlistBean.CgzhulistBean bean = mList.get(position);
        Log.e("我的数据啊",new Gson().toJson(bean));
        holder.tvCgmc.setText(bean.getPurchase_name());
        holder.tvJine.setText(bean.getZongjia());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cgmc)
        TextView tvCgmc;
        @BindView(R.id.tv_jine)
        TextView tvJine;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
