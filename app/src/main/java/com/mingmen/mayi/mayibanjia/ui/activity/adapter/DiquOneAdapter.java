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
import com.mingmen.mayi.mayibanjia.bean.SongDaShiJianBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class DiquOneAdapter extends RecyclerView.Adapter<DiquOneAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<JsonBean> mList;//省
    private List<JsonBean.CitylistBean> cityList;//市
    private List<JsonBean.CitylistBean.QulistBean> quList;//区
    private int xztype;
    private int level = 1;

    public void setXztype(int xztype) {
        this.xztype = xztype;
        notifyDataSetChanged();
    }

    private OnItemClickListener mOnItemClickListener;

    public DiquOneAdapter(Context mContext, List<JsonBean> list) {
        this.mContext = mContext;
        this.mList = list;
        this.level = 1;
    }
    public DiquOneAdapter(Context mContext, List<JsonBean.CitylistBean> list,int level) {
        this.mContext = mContext;
        this.cityList = list;
        this.level = 2;
    }
    public DiquOneAdapter(Context mContext, List<JsonBean.CitylistBean.QulistBean> list,String level) {
        this.mContext = mContext;
        this.quList = list;
        this.level = 3;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_string, parent, false));
        return viewHolder;
    }

    //    item_sousuomohuchaxun
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (level){
            case 1:
                JsonBean data = mList.get(position);
                Log.e("onBindViewHolder: ", xztype+"");
                if (data.getQuybm()==xztype){
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                } else {
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
                }
                holder.tvMing.setText(data.getQuymc() + "");
                break;
            case 2:
                JsonBean.CitylistBean city_data = cityList.get(position);
                Log.e("onBindViewHolder: ", xztype+"");
                if (city_data.getQuybm()==xztype){
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                } else {
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
                }
                holder.tvMing.setText(city_data.getQuymc() + "");
                break;
            case 3:
                JsonBean.CitylistBean.QulistBean qu_data = quList.get(position);
                Log.e("onBindViewHolder: ", xztype+"");
                if (qu_data.getQuybm()==xztype){
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.zangqing));
                } else {
                    holder.tvMing.setTextColor(mContext.getResources().getColor(R.color.lishisousuo));
                }
                holder.tvMing.setText(qu_data.getQuymc() + "");
                break;
        }

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
        if(level==1){
            return mList==null?0:mList.size();
        } else if(level==2){
            return cityList==null?0:cityList.size();
        } else if(level==3){
            return quList==null?0:quList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ming)
        TextView tvMing;
//        @BindView(R.id.ll_kuang)
//        LinearLayout llKuang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
