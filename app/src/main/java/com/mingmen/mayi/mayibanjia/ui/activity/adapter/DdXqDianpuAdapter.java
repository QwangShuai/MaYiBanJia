package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdxqListBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class DdXqDianpuAdapter extends RecyclerView.Adapter<DdXqDianpuAdapter.ViewHolder> {

    private ViewHolder viewHolder;
    private Context mContext;
    private List<DdxqListBean.MarketBean.DplistBean> mList;
    private DdXqShangpinAdapter adapter;
    private ConfirmDialog dialog;
//    private OnItemClickListener mOnItemClickListener;

    public DdXqDianpuAdapter(Context mContext, List<DdxqListBean.MarketBean.DplistBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dianpu_ddxq, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DdxqListBean.MarketBean.DplistBean dplistBean = mList.get(position);
        adapter = new DdXqShangpinAdapter(mContext, dplistBean.getListsp());
        holder.rvShangpin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.rvShangpin.setAdapter(adapter);
        holder.rvShangpin.setFocusable(false);
        holder.tvDianpuming.setText(dplistBean.getCompany_name());
        holder.llMydianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("dianpuid", dplistBean.getCompany_id());
                JumpUtil.Jump_intent(mContext, DianPuActivity.class, bundle);
            }
        });
        holder.llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallPhone(dplistBean.getTelephone());
            }
        });
        if (dplistBean.getCt_state().equals("406")){
            if(dplistBean.getState().equals("404")){
                holder.llTongji.setVisibility(View.VISIBLE);
                holder.tvJianshu.setText(dplistBean.getShu());
                DingDanXiangQingActivity.instance.setJiaGeShowView(holder.tvZongjia1,holder.tvZongjia2,dplistBean.getTotal());
                holder.btnFukuan.setText("确认付款");
                holder.btnFukuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog = new ConfirmDialog(mContext,
                                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                        dialog.showDialog("是否确认订单完成");
                        dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                DingDanXiangQingActivity.instance.confirmOrder(dplistBean.getCompany_id());
                            }
                        });
                        dialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                    }
                });
            }else if(dplistBean.getState().equals("405")){
                holder.llTongji.setVisibility(View.VISIBLE);
                holder.tvJianshu.setText(dplistBean.getShu());
                DingDanXiangQingActivity.instance.setJiaGeShowView(holder.tvZongjia1,holder.tvZongjia2,dplistBean.getTotal());
                holder.btnFukuan.setText("已支付");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dianpuming)
        TextView tvDianpuming;
        @BindView(R.id.ll_mydianpu)
        LinearLayout llMydianpu;
        @BindView(R.id.ll_phone)
        LinearLayout llPhone;
        @BindView(R.id.rv_shangpin)
        RecyclerView rvShangpin;
        @BindView(R.id.tv_jianshu)
        TextView tvJianshu;
        @BindView(R.id.tv_zongjia1)
        TextView tvZongjia1;
        @BindView(R.id.tv_zongjia2)
        TextView tvZongjia2;
        @BindView(R.id.btn_fukuan)
        Button btnFukuan;
        @BindView(R.id.ll_tongji)
        LinearLayout llTongji;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
