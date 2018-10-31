package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DingDanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * DingDanBean.ListBean
 * Created by Administrator on 2018/7/13/013.
 */
//订单商品
public class DingDanShangPinAdapter extends RecyclerView.Adapter<DingDanShangPinAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DingDanBean.ListBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public DingDanShangPinAdapter(Context mContext, List<DingDanBean.ListBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dingdanshangpin, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DingDanBean.ListBean data = mList.get(position);
        Glide.with(mContext).load(data.getUrl()).into(holder.ivSptu);
        holder.tvName.setText(data.getCommodity_name());
        holder.tvDanjia.setText(data.getPrice()+"");
        holder.tvDianpuming.setText(data.getCompany_name());
        holder.tvGuige.setText(data.getSpec_name());
        holder.tvShuliang.setText(data.getAcount()+"");
        if (mOnItemClickListener != null) {
//            holder.btZaimai.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickListener.onClick(v, position);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_dianpuming)
        TextView tvDianpuming;
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
