package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.SPTGBean;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiChengGongActivity;
import com.mingmen.mayi.mayibanjia.utils.GlideUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/12.
 */

public class SPTGOneTwoAdapter extends RecyclerView.Adapter<SPTGOneTwoAdapter.ViewHolder> {


    private ShenPiChengGongActivity activity;
    private PopupWindow mPopWindow;
    private ViewHolder viewHolder;
    private List<SPTGBean.FllistBean.SonorderlistBean> mList;
    //    private ShenPiLevelOneAdapter adapter;
    private Context mContext;
    public SPTGOneTwoAdapter(List<SPTGBean.FllistBean.SonorderlistBean> mList, Context mContext,ShenPiChengGongActivity activity) {
        this.mList = mList;
        this.mContext = mContext;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shenpi_tongguo_two, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SPTGBean.FllistBean.SonorderlistBean bean = mList.get(position);
        GlideUtils.cachePhoto(mContext,holder.ivSptu,bean.getPicture_url());
        holder.tvShangpin.setText(bean.getClassify_name() + "");
        holder.tvDianming.setText(bean.getCompany_name());
        if(StringUtil.isValid(bean.getSpecial_commodity())){
            holder.llTeshu.setVisibility(View.VISIBLE);
            holder.llTeshu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupWindow(bean.getSpecial_commodity(), holder.tvTeshu);
                }
            });
        }
        holder.tvGuige.setText(bean.getPrice()+"");
        holder.tvShuliang.setText("x "+bean.getCount());
        holder.tvJine.setText(bean.getAll_price()+"元");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_teshu)
        TextView tvTeshu;
        @BindView(R.id.ll_teshu)
        LinearLayout llTeshu;
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_shangpin)
        TextView tvShangpin;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_shuliang)
        TextView tvShuliang;
        @BindView(R.id.tv_jine)
        TextView tvJine;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private void showPopupWindow(String yaoqiu, View xianshiView) {
        View view = View.inflate(activity, R.layout.pop_teshuyaoqiu, null);
        mPopWindow = new PopupWindow(view);
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        mPopWindow.setWidth(width * 4 / 6);
        mPopWindow.setHeight(height * 2 / 7);

        TextView tv_yaoqiu = (TextView) view.findViewById(R.id.tv_yaoqiu);
        tv_yaoqiu.setText(yaoqiu);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.showAsDropDown(xianshiView);
    }
}
