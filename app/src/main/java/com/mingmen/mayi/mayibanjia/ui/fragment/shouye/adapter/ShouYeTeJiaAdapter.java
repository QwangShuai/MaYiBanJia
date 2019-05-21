package com.mingmen.mayi.mayibanjia.ui.fragment.shouye.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.ShouYeTeJiaBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.custom.GlideRoundTransform;
import com.mingmen.mayi.mayibanjia.utils.custom.MarqueeTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/5/005.
 */

public class ShouYeTeJiaAdapter extends RecyclerView.Adapter<ShouYeTeJiaAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<ShouYeTeJiaBean> mList;

    public ShouYeTeJiaAdapter(Context mContext, List<ShouYeTeJiaBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spxiangqing_weinituijian, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShouYeTeJiaBean data = mList.get(position);
        holder.tvSpming.setText(data.getClassify_name());
        holder.tvSpming.setMarqueeEnable(true);
        if(StringUtil.isValid(data.getPice())){
            holder.tvDanjia.setText(data.getPice()+"");
            if(StringUtil.isValid(data.getPrice())){
                holder.tvYuandanjia.setText("￥ "+ data.getPrice());
                holder.tvYuandanjia.setVisibility(View.VISIBLE);
            } else {
                holder.tvYuandanjia.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.tvYuandanjia.setVisibility(View.INVISIBLE);
            holder.tvDanjia.setText(data.getPrice());
        }
        if(data.getReal_time_state().equals("0")){
            holder.ivSsd.setVisibility(View.VISIBLE);
        } else {
            holder.ivSsd.setVisibility(View.GONE);
        }

        holder.tvYuandanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        GlideUtils.cachePhotoRound(mContext,holder.ivSptu,data.getPicture_url(),10);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("spid",data.getCommodity_id());
                JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.iv_ssd)
        ImageView ivSsd;
        @BindView(R.id.tv_spming)
        MarqueeTextView tvSpming;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_yuandanjia)
        TextView tvYuandanjia;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
