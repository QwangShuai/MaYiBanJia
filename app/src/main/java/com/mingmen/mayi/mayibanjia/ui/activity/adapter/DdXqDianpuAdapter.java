package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdxqBean;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class DdXqDianpuAdapter extends RecyclerView.Adapter<DdXqDianpuAdapter.ViewHolder>{
    private ViewHolder viewHolder;
    private Context mContext;
    private List<DdxqBean.MarketBean> mList;
//    private OnItemClickListener mOnItemClickListener;

    public DdXqDianpuAdapter(Context mContext, List<DdxqBean.MarketBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dianpu_ddxq, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
