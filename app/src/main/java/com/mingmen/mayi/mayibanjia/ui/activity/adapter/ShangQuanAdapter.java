package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.JsonBean;
import com.mingmen.mayi.mayibanjia.bean.ProvinceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ShangQuanAdapter extends RecyclerView.Adapter<ShangQuanAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ProvinceBean> mList;

    public void setXztype(ProvinceBean xztype) {
        this.xztype = xztype;
        notifyDataSetChanged();
    }

    private ProvinceBean xztype = new ProvinceBean();

    private OnItemClickListener mOnItemClickListener;

    public ShangQuanAdapter(Context mContext, List<ProvinceBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false));
        return viewHolder;
    }

    //    item_sousuomohuchaxun
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ProvinceBean data = mList.get(position);
        Log.e("onBindViewHolder: ",data.getQuymc()+"==="+ xztype.getQuybm());
        if (data.getQuybm()==xztype.getQuybm()){
            holder.llKuang.setBackground(mContext.getResources().getDrawable(R.drawable.fillet_hollow_zangqing_3));
            holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.zangqing));
        } else {
            holder.llKuang.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.zicolor));
        }
        holder.tvMing.setText(data.getQuymc() + "");
        if (mOnItemClickListener != null) {
            holder.tvMing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ming)
        TextView tvMing;
        @BindView(R.id.ll_kuang)
        LinearLayout llKuang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
