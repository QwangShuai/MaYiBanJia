package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.AddressListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class ShouHuoDiZhiAdapter extends RecyclerView.Adapter<ShouHuoDiZhiAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<AddressListBean> mList;
    private OnItemClickListener mOnItemClickListener;

    public ShouHuoDiZhiAdapter(Context mContext, List<AddressListBean> list) {
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
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AddressListBean data = mList.get(position);
//        String zi = string.getCompany_name().toString().trim();
//        holder.tv_ming.setText(zi);
        holder.tvName.setText(data.getLinkman());
        holder.tvPhone.setText(data.getContact_type());
//        holder.tvDizhi.setText(data.getProvince_name()+data.getCity_name()+data.getRegion_name()+data.getStreet_name()+data.getSpecific_address());
        holder.tvDizhi.setText(data.getProvince_name()+data.getCity_name()+data.getRegion_name()+data.getSpecific_address());
        if (Integer.parseInt(data.getDefault_address())!=0){
            holder.tvMoren.setVisibility(View.GONE);
        }else{
            holder.tvMoren.setVisibility(View.VISIBLE);
        }



        if (mOnItemClickListener != null) {
            holder.llBianji.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.llShanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v, position);
                }
            });
            holder.llKuang.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_moren)
        TextView tvMoren;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.ll_bianji)
        LinearLayout llBianji;
        @BindView(R.id.ll_shanchu)
        LinearLayout llShanchu;
        @BindView(R.id.ll_kuang)
        LinearLayout llKuang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
