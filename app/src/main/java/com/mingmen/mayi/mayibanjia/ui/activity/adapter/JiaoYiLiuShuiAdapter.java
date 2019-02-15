package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.JYMXBean;
import com.mingmen.mayi.mayibanjia.bean.JiaoYiMingXiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.JiaoYiXiangQingActivity;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/5.
 */

public class JiaoYiLiuShuiAdapter extends RecyclerView.Adapter<JiaoYiLiuShuiAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<JiaoYiMingXiBean.LieBiaoBean> mList;

    public JiaoYiLiuShuiAdapter(Context mContext, List<JiaoYiMingXiBean.LieBiaoBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jiaoyiliushui, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JiaoYiMingXiBean.LieBiaoBean bean = mList.get(position);
        switch (bean.getState()){
            case "0":
                holder.tvTitle.setText("收款");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                holder.tvMoney.setText("+"+bean.getCollect_money());
                break;
            case "1":
                holder.tvTitle.setText("付款");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.mayihong));
                holder.tvMoney.setText("-"+bean.getPay_money());
                break;
            case "2":
                holder.tvTitle.setText("提现");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.mayihong));
                holder.tvMoney.setText("-"+bean.getPay_money());
                break;
            case "3":
                holder.tvTitle.setText("退款");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                if(StringUtil.isValid(bean.getCollect_money())){
                    holder.tvMoney.setText("+"+bean.getCollect_money());
                } else {
                    holder.tvMoney.setText("-"+bean.getPay_money());
                }

                break;
            case "4":
                holder.tvTitle.setText("提现成功");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                holder.tvMoney.setText("-"+bean.getPay_money());
                break;
            case "5":
                holder.tvTitle.setText("提现失败");
                holder.tvMoney.setTextColor(mContext.getResources().getColor(R.color.zicolor));
                holder.tvMoney.setText("+"+bean.getPay_money());
                break;
        }
        holder.tvTime.setText(bean.getCreate_time());
        holder.llChakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, JiaoYiXiangQingActivity.class);
                it.putExtra("id",bean.getHistory_id());
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.ll_chakan)
        LinearLayout llChakan;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
