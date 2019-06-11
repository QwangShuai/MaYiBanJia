package com.mingmen.mayi.mayibanjia.ui.fragment.shouye.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.FCGName;
import com.mingmen.mayi.mayibanjia.bean.ShouYeLeiBean;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.myinterface.MainCallBack;
import com.mingmen.mayi.mayibanjia.ui.view.CircleImageView;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
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
    private List<FCGName> mList;
    private MainActivity activity;

    public ShouYeLeiAdapter(Context mContext, List<FCGName> list, MainActivity activity) {
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
        final FCGName data = mList.get(position);
        Log.e( "onBindViewHolder: ","为什么死循环了"+position );
        GlideUtils.cachePhoto(mContext,holder.iv1,data.getPicture_url());
            holder.tv1.setText(data.getClassify_name());
            holder.ll1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToastLong("点击了"+data.getClassify_name());
                    activity.changeView(data.getClassify_name(),data.getClassify_id());
                }
            });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public int getPageCount(){
        if(mList.size()%10==0){
            return mList.size()/10;
        } else {
            return mList.size()/10+1;
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv1)
        CircleImageView iv1;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.ll1)
        LinearLayout ll1;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
