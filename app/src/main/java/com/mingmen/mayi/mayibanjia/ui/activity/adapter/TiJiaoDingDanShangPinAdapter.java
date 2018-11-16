package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.GWCShangPinBean;
import com.mingmen.mayi.mayibanjia.bean.QueRenDingDanShangPinBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class TiJiaoDingDanShangPinAdapter extends RecyclerView.Adapter<TiJiaoDingDanShangPinAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<QueRenDingDanShangPinBean.DplistBean.ListBean> mList;
    private boolean isSelected;
    private OnItemClickListener mOnItemClickListener;

    public boolean isSelected() {
        return isSelected;
    }


    public interface OnItemClickListener {
        void onClick(View view, int position, List<QueRenDingDanShangPinBean.DplistBean.ListBean> mList);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public TiJiaoDingDanShangPinAdapter(Context mContext, List<QueRenDingDanShangPinBean.DplistBean.ListBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_querendingdan_sp, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final QueRenDingDanShangPinBean.DplistBean.ListBean shoppingBean = mList.get(position);
        if(TextUtils.isEmpty(shoppingBean.getAppend_money()+"")||shoppingBean.getAppend_money()==null){
            holder.tvFujiafei.setVisibility(View.GONE);
        } else {
            holder.tvFujiafei.setText("(附加费："+shoppingBean.getAppend_money()+"元)");
            holder.tvFujiafei.setVisibility(View.VISIBLE);
        }
        holder.tvName.setText(shoppingBean.getCommodity_name());
        holder.tvGuige.setText(shoppingBean.getPack_standard());
        holder.tvDanjia.setText(shoppingBean.getPrice() + "");
        holder.tvShuliang.setText(shoppingBean.getCount() + "");
        Glide.with(mContext).load(shoppingBean.getUrl()).into(holder.ivSptu);
    }

    public List<QueRenDingDanShangPinBean.DplistBean.ListBean> getmList() {
        Log.e("gson2", new Gson().toJson(mList) + "--");
        return mList;
    }

    public void setmList(List<QueRenDingDanShangPinBean.DplistBean.ListBean> list) {
        mList = list;
        notifyDataSetChanged();
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
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        @BindView(R.id.tv_fujiafei)
        TextView tvFujiafei;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

