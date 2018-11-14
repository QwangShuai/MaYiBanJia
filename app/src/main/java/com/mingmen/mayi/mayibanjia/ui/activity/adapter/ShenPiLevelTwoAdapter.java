package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean;
import com.mingmen.mayi.mayibanjia.bean.XiTongTuiJianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShenPiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.GengDuoShangJiaDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FIVE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_FOUR;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_ONE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_THREE;
import static com.mingmen.mayi.mayibanjia.bean.CaiGouDanBean.TYPE_TWO;

/**
 * Created by Administrator on 2018/11/12.
 */

public class ShenPiLevelTwoAdapter extends RecyclerView.Adapter<ShenPiLevelTwoAdapter.ViewHolder> {


    private ShenPiActivity activity;
    private ViewHolder viewHolder;
    private List<CaiGouDanBean.ListBean.CcListBeanLevel> mList;

    public ShenPiLevelTwoAdapter(ShenPiActivity activity, List<CaiGouDanBean.ListBean.CcListBeanLevel> mList) {
        this.activity = activity;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shenpi_zhengchang_child, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("真是难受的一比", new Gson().toJson(mList));
        final CaiGouDanBean.ListBean.CcListBeanLevel ccListBeanLevel = mList.get(position);
        if (ccListBeanLevel.getCcListBean().getCommodity_id() != null) {
            holder.rlKuang.setVisibility(View.VISIBLE);
        } else {
            holder.rlKuang.setVisibility(View.GONE);
        }
        Log.e("是否选中了呢",ccListBeanLevel.isXuanzhong()+"---");
        if (ccListBeanLevel.isXuanzhong()){
            holder.rlKuang.setBackgroundColor(activity.getResources().getColor(R.color.hei20));
        } else {
            holder.rlKuang.setBackgroundColor(activity.getResources().getColor(R.color.white));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getIvQuanxuan().setSelected(false);
                activity.getTvBiaoqian().setText("");
                activity.getTvBiaoqian().setHint("请选择");
                for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setXuanzhong(false);
                }
                mList.get(position).setXuanzhong(true);
                activity.setMoreShangjia();
                activity.setViewShow(mList.get(position));
            }
        });
        Log.e("这是我的附加费", "" + ccListBeanLevel.getCcListBean().getAppend_money());
        if (ccListBeanLevel.getCcListBean().getAppend_money() != null && !TextUtils.isEmpty(ccListBeanLevel.getCcListBean().getAppend_money() + ""))
            holder.tvFujiafei.setText("附加费：￥" + ccListBeanLevel.getCcListBean().getAppend_money());
        else
            holder.tvFujiafei.setVisibility(View.GONE);
        holder.tvSpming.setText(ccListBeanLevel.getCcListBean().getCommodity_name() + "");
        holder.tvDianming.setText(ccListBeanLevel.getCcListBean().getCompany_name() + "");
        holder.tvGuige.setText(ccListBeanLevel.getCcListBean().getPack_standard() + "");
        holder.tvDanjia.setText(ccListBeanLevel.getCcListBean().getPrice() + "");
        holder.tvSpming.setText("已售" + ccListBeanLevel.getCcListBean().getCommodity_sales());
        if (ccListBeanLevel.getCcListBean().isxianshi())
            holder.biaoqian.setVisibility(View.VISIBLE);
        else
            holder.biaoqian.setVisibility(View.GONE);
        holder.biaoqian.setText(ccListBeanLevel.getCcListBean().getBiaoqian() + "");
        holder.btShangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GengDuoShangJiaDialog()
                        .setId(mList.get(position).getCcListBean().getSon_order_id(), mList.get(position).getCcListBean().getMarket_id())
                        .setCallBack(new GengDuoShangJiaDialog.CallBack() {
                            @Override
                            public void xuanzhong(XiTongTuiJianBean.CcListBean msg) {
                                msg.setIsxianshi(true);
                                msg.setBiaoqian("推荐商家");
                                mList.get(position).setCcListBean(msg);
//                                activity.setMoreShangjia(msg);
                                activity.setMoreShangjia();
                            }
                        })
                        .show(activity.getSupportFragmentManager());
            }
        });
        holder.btXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("spid", mList.get(position).getCcListBean().getCommodity_id());
                JumpUtil.Jump_intent(activity, SPXiangQingActivity.class, bundle);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_dian)
        TextView tvDian;
        @BindView(R.id.tv_dianming)
        TextView tvDianming;
        @BindView(R.id.tv_guige)
        TextView tvGuige;
        @BindView(R.id.tv_renminbi)
        TextView tvRenminbi;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.tv_spxiaoliang)
        TextView tvSpxiaoliang;
        @BindView(R.id.tv_fujiafei)
        TextView tvFujiafei;
        @BindView(R.id.ll_sp)
        LinearLayout llSp;
        @BindView(R.id.biaoqian)
        TextView biaoqian;
        @BindView(R.id.bt_xiangqing)
        Button btXiangqing;
        @BindView(R.id.bt_shangjia)
        Button btShangjia;
        @BindView(R.id.rl_kuang)
        RelativeLayout rlKuang;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void setXuanzhong(int pos){//全选设置选中状态
        for (int i = 0;i<mList.size();i++){
            mList.get(i).setXuanzhong(false);
        }
        mList.get(pos).setXuanzhong(true);
    }
}
