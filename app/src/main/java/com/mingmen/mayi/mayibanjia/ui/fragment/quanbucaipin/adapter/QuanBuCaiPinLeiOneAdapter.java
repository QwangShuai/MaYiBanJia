package com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.ui.activity.QuanBuCaiPinActivity;
import com.mingmen.mayi.mayibanjia.ui.fragment.quanbucaipin.QuanBuCaiPinFragment;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class QuanBuCaiPinLeiOneAdapter extends RecyclerView.Adapter<QuanBuCaiPinLeiOneAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<FCGName> mList;
    private QuanBuCaiPinFragment fragment;
    private QuanBuCaiPinActivity activity;
    private String xuanzhongId = "";
    private boolean isTeshu;

    public String getXuanzhongId() {
        return xuanzhongId;
    }

    public void setXuanzhongId(String xuanzhongId) {
        this.xuanzhongId = xuanzhongId;
        notifyDataSetChanged();
    }

    public QuanBuCaiPinLeiOneAdapter(Context mContext, List<FCGName> list, QuanBuCaiPinFragment fragment) {
        this.mContext = mContext;
        this.mList = list;
        this.fragment = fragment;
    }
    public QuanBuCaiPinLeiOneAdapter(Context mContext, List<FCGName> list) {
        this.mContext = mContext;
        this.mList = list;
        isTeshu = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qbcp_bq, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FCGName data = mList.get(position);
        holder.tv1.setText(data.getClassify_name());
        setTextViewColor(holder.tv1, data.getClassify_id());
        Log.e("onBindViewHolder: ", data.getClassify_id());
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTeshu){
                    ToastUtil.showToastLong("您选择了" + data.getClassify_name());
                } else {
                    fragment.setXuanzhongId(data.getClassify_id());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public int getPageCount(){
        if(mList.size()%10==0){
            return mList.size()/10;
        } else {
            return mList.size()/10+1;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv1)
        TextView tv1;
//        @BindView(R.id.rl_1)
//        RelativeLayout rl1;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void setTextViewColor(TextView tv, String id) {
        if (id.equals(xuanzhongId)) {
            tv.setTextColor(mContext.getResources().getColor(R.color.white));
            tv.setBackground(mContext.getResources().getDrawable(R.drawable.zangqing_yuan));
        } else {
            tv.setTextColor(mContext.getResources().getColor(R.color.zicolor));
            tv.setBackground(mContext.getResources().getDrawable(R.drawable.white_yuan));
        }
    }
}
