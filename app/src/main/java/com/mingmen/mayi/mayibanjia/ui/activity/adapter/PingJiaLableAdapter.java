package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.AllShiChangBean;
import com.mingmen.mayi.mayibanjia.bean.PingJiaLableBean;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBiaoPingJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.view.XCFlowLayout;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/30.
 */

public class PingJiaLableAdapter extends RecyclerView.Adapter<PingJiaLableAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<PingJiaLableBean> mList;
    private boolean[] isSelect;
    private FaBiaoPingJiaActivity activity;

    public PingJiaLableAdapter(Context mContext, List<PingJiaLableBean> list, FaBiaoPingJiaActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
        isSelect = new boolean[mList==null?0:mList.size()];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lable, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PingJiaLableBean bean = mList.get(position);
        holder.tvYijifenlei.setText(bean.getSon_name()+"");
        if(isSelect[position]){
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.bg_bt_false));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.white));
            activity.addViewShow(mList.get(position));
        } else {
            holder.tvYijifenlei.setBackground(mContext.getResources().getDrawable(R.drawable.bg_bt_true));
            holder.tvYijifenlei.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            activity.delViewShow(mList.get(position));
        }

        holder.tvYijifenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelect[position]){
                    isSelect[position] = false;
                    notifyDataSetChanged();
                } else {
                    isSelect[position] = true;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_yijifenlei)
        TextView tvYijifenlei;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
