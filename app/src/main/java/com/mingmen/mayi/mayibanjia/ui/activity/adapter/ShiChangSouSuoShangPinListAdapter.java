package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShiChangSouSuoShangPinBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ShiChangSouSuoShangPinListAdapter extends  RecyclerView.Adapter<ShiChangSouSuoShangPinListAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<ShiChangSouSuoShangPinBean> mList;
    private OnItemClickListener mOnItemClickListener;
    public ShiChangSouSuoShangPinListAdapter(Context mContext, List<ShiChangSouSuoShangPinBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }
    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
    @Override
    public ShiChangSouSuoShangPinListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shichangliebiao, parent, false));
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ShiChangSouSuoShangPinBean data = mList.get(position);
        holder.tv_shichangming.setText(data.getMarket_name());
        holder.tv_shanghushuliang.setText("商户数量："+data.getShuliang());
        holder.tv_zuigaojiajiage.setText(data.getBig());
        holder.tv_zuigaojiaguige.setText(data.getPackOneName());
        holder.tv_zuidijiajiage.setText(data.getSmall());
        holder.tv_zuidijiaguige.setText(data.getPackOneName());
        holder.tv_dizhi.setText(data.getSpecific_address());
        if (mOnItemClickListener!=null){
            holder.tv_chakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_shichangming;
        TextView tv_shanghushuliang;
        TextView tv_zuigaojiajiage;
        TextView tv_zuigaojiaguige;
        TextView tv_zuidijiaguige;
        TextView tv_zuidijiajiage;
        TextView tv_dizhi;
        TextView tv_chakan;
        LinearLayout ll_dizhi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tv_shichangming=view.findViewById(R.id.tv_shichangming);
            tv_shanghushuliang=view.findViewById(R.id.tv_shanghushuliang);
            tv_zuigaojiajiage=view.findViewById(R.id.tv_zuigaojiajiage);
            tv_zuigaojiaguige=view.findViewById(R.id.tv_zuigaojiaguige);
            tv_zuidijiajiage=view.findViewById(R.id.tv_zuidijiajiage);
            tv_zuidijiaguige=view.findViewById(R.id.tv_zuidijiaguige);
            tv_dizhi=view.findViewById(R.id.tv_dizhi);
            tv_chakan=view.findViewById(R.id.tv_chakan);
            ll_dizhi= view.findViewById(R.id.ll_dizhi);
        }
    }

}
