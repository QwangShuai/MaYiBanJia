package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.JieSuanJirGuoBean;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.JieSuanJieGuoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.JieSuanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.YunFeiDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class JieSuanJieGuoAdapter extends RecyclerView.Adapter<JieSuanJieGuoAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<JieSuanJirGuoBean> mList;
    private JieSuanJieGuoActivity activity;
    private boolean[] isSelect;

    public JieSuanJieGuoAdapter(Context context, List<JieSuanJirGuoBean> list, JieSuanJieGuoActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_yunfeijiesuan, parent, false));
        isSelect = new boolean[mList == null ? 0 : mList.size()];
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final JieSuanJirGuoBean bean = mList.get(position);
        holder.tvQuanbuYunfei.setText(bean.getFreight_fee()+"");
        holder.tvShijiYunfei.setText(bean.getActual_freight()+"");
        holder.tvState.setText(bean.getApproval_state().equals("0")?"已完成":"结算中");
        holder.tvSubmitTime.setText(bean.getCreate_time()+"");
        if(bean.getApproval_state().equals("0")){
            holder.tvEndTime.setText(bean.getChange_time()+"");
        } else {
            holder.llEndTime.setVisibility(View.GONE);
        }
        holder.btChakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, JieSuanXiangQingActivity.class);
                it.putExtra("wl_cars_order_id",bean.getWl_cars_order_id());
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_quanbu_yunfei)
        TextView tvQuanbuYunfei;
        @BindView(R.id.tv_shiji_yunfei)
        TextView tvShijiYunfei;
        @BindView(R.id.tv_submit_time)
        TextView tvSubmitTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.bt_chakan)
        Button btChakan;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.ll_end_time)
        LinearLayout llEndTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
