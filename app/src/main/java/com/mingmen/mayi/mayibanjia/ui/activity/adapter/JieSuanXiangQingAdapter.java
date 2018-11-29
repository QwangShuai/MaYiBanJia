package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.YunFeiJieSuanBean;
import com.mingmen.mayi.mayibanjia.ui.activity.JieSuanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.YunFeiJieSuanActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/26.
 */

public class JieSuanXiangQingAdapter extends RecyclerView.Adapter<JieSuanXiangQingAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<YunFeiJieSuanBean.DdListBean> mList;
    private JieSuanXiangQingActivity activity;

    public JieSuanXiangQingAdapter(Context context, List<YunFeiJieSuanBean.DdListBean> list, JieSuanXiangQingActivity activity) {
        this.mContext = context;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jiesuanxiangqing, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final YunFeiJieSuanBean.DdListBean bean = mList.get(position);
        holder.tvOrderNumber.setText(bean.getWl_cars_order_number());
        holder.tvJine.setText(bean.getFreight_fee() + "");
        holder.tvEndTime.setText(bean.getChange_time());
        holder.tvQuhuodizhi.setText(bean.getSpecific_address());
        holder.tvSonghuodizhi.setText(bean.getCtAddress());
        holder.tvLianxiren.setText(bean.getDriverName());
        holder.tvDianhua.setText(bean.getDriverPhone());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_number)
        TextView tvOrderNumber;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.tv_quhuodizhi)
        TextView tvQuhuodizhi;
        @BindView(R.id.tv_songhuodizhi)
        TextView tvSonghuodizhi;
        @BindView(R.id.tv_jine)
        TextView tvJine;
        @BindView(R.id.tv_lianxiren)
        TextView tvLianxiren;
        @BindView(R.id.tv_dianhua)
        TextView tvDianhua;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
