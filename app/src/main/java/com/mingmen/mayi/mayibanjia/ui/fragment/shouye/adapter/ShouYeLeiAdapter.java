package com.mingmen.mayi.mayibanjia.ui.fragment.shouye.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.myinterface.MainCallBack;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class ShouYeLeiAdapter extends RecyclerView.Adapter<ShouYeLeiAdapter.ViewHolder> {
    private ViewHolder viewHolder;
    private Context mContext;
    private List<List<ShouYeLeiBean>> mList;
    private MainActivity activity;

    public ShouYeLeiAdapter(Context mContext, List<List<ShouYeLeiBean>> list, MainActivity activity) {
        this.mContext = mContext;
        this.mList = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shouyefenlei, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final List<ShouYeLeiBean> data = mList.get(position);
        if (data.get(0) != null) {
            Glide.with(mContext).load(data.get(0).getPicture_url()).into(holder.iv1);
            holder.tv1.setText(data.get(0).getClassify_name());
            holder.ll1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(0).getClassify_name(),data.get(0).getClassify_id());
                }
            });
        }
        if (data.get(1) != null) {
            Glide.with(mContext).load(data.get(1).getPicture_url()).into(holder.iv2);
            holder.tv2.setText(data.get(1).getClassify_name());
            holder.ll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(1).getClassify_name(),data.get(1).getClassify_id());
                }
            });
        }
        if (data.get(2) != null) {
            Glide.with(mContext).load(data.get(2).getPicture_url()).into(holder.iv3);
            holder.tv3.setText(data.get(2).getClassify_name());
            holder.ll3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(2).getClassify_name(),data.get(2).getClassify_id());
                }
            });
        }
        if (data.get(3) != null) {
            Glide.with(mContext).load(data.get(3).getPicture_url()).into(holder.iv4);
            holder.tv4.setText(data.get(3).getClassify_name());
            holder.ll4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(3).getClassify_name(),data.get(3).getClassify_id());
                }
            });
        }
        if (data.get(4) != null) {
            Glide.with(mContext).load(data.get(4).getPicture_url()).into(holder.iv5);
            holder.tv5.setText(data.get(4).getClassify_name());
            holder.ll5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(4).getClassify_name(),data.get(4).getClassify_id());
                }
            });
        }
        if (data.get(5) != null) {
            Glide.with(mContext).load(data.get(5).getPicture_url()).into(holder.iv6);
            holder.tv6.setText(data.get(5).getClassify_name());
            holder.ll6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(5).getClassify_name(),data.get(5).getClassify_id());
                }
            });
        }
        if (data.get(6) != null) {
            Glide.with(mContext).load(data.get(6).getPicture_url()).into(holder.iv7);
            holder.tv7.setText(data.get(6).getClassify_name());
            holder.ll7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(6).getClassify_name(),data.get(6).getClassify_id());
                }
            });
        }
        if (data.get(7) != null) {
            Glide.with(mContext).load(data.get(7).getPicture_url()).into(holder.iv8);
            holder.tv8.setText(data.get(7).getClassify_name());
            holder.ll8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.changeView(data.get(7).getClassify_name(),data.get(7).getClassify_id());
                }
            });
        }


//        Glide.with(mContext).load(""+string.getFile_url())
//                .into(holder.iv1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.ll1)
        LinearLayout ll1;
        @BindView(R.id.iv2)
        ImageView iv2;
        @BindView(R.id.tv2)
        TextView tv2;
        @BindView(R.id.ll2)
        LinearLayout ll2;
        @BindView(R.id.iv3)
        ImageView iv3;
        @BindView(R.id.tv3)
        TextView tv3;
        @BindView(R.id.ll3)
        LinearLayout ll3;
        @BindView(R.id.iv4)
        ImageView iv4;
        @BindView(R.id.tv4)
        TextView tv4;
        @BindView(R.id.ll4)
        LinearLayout ll4;
        @BindView(R.id.iv5)
        ImageView iv5;
        @BindView(R.id.tv5)
        TextView tv5;
        @BindView(R.id.ll5)
        LinearLayout ll5;
        @BindView(R.id.iv6)
        ImageView iv6;
        @BindView(R.id.tv6)
        TextView tv6;
        @BindView(R.id.ll6)
        LinearLayout ll6;
        @BindView(R.id.iv7)
        ImageView iv7;
        @BindView(R.id.tv7)
        TextView tv7;
        @BindView(R.id.ll7)
        LinearLayout ll7;
        @BindView(R.id.iv8)
        ImageView iv8;
        @BindView(R.id.tv8)
        TextView tv8;
        @BindView(R.id.ll8)
        LinearLayout ll8;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
