package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBuShangPinActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiAdapter extends RecyclerView.Adapter<ShangPinGuanLiAdapter.ViewHolder> {
    
    private ShangPinGuanLiActivity activity;
    private ConfirmDialog confirmDialog;
    private String goods = "0";
    private ViewHolder viewHolder;
    private List<ShangPinGuanLiBean.GoodsListBean> mList;
    private Context mContext;//    public String getGoods() {
//        return goods;
//    }
//
//    public void setGoods(String goods) {
//        this.goods = goods;
//        notifyDataSetChanged();
//    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    private boolean isClick = true;

    public ShangPinGuanLiAdapter(Context mContext,ShangPinGuanLiActivity activity, String goods,List<ShangPinGuanLiBean.GoodsListBean> mList) {
        this.mContext = mContext;
        this.activity = activity;
        this.goods = goods;
        this.mList = mList;
    }

    public void updateState(String id, final String type) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .ghdspdel(PreferenceUtils.getString(MyApplication.mContext, "token", ""), id, type))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        confirmDialog.dismiss();
                        activity.setType("0");
                        notifyDataSetChanged();
                    }
                }, false);
    }

    public void showDialog(String title, final String id, final String type) {
        confirmDialog.showDialog(title);
        confirmDialog.getTvSubmit().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateState(id, type);
            }
        });
        confirmDialog.getTvCancel().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shangpinguanli, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShangPinGuanLiBean.GoodsListBean bean = mList.get(position);
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        Glide.with(mContext).load(bean.getPicture_url()).into(holder.ivSptu);
        holder.tvSpming.setText(bean.getClassify_name());
        holder.tvXiaoliang.setText( "已售" + bean.getSumGoodsSales());
        holder.tvKucun.setText( "库存" + bean.getInventory());
        holder.tvDanjia.setText("¥ " + bean.getPrice());
        holder.btBianji.setVisibility(View.VISIBLE);
        holder.btBianji.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick()) {
                    if (bean.getApproval_state().equals("0") || bean.getApproval_state().equals("2")) {
                        ToastUtil.showToast("上架不能编辑，请先下架");
                    } else {
                        Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                        it.putExtra("state", "1");
                        it.putExtra("goods", goods);
                        it.putExtra("bean", bean.getCommodity_id());
                        mContext.startActivity(it);
                    }
                    //上下架状态
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }

            }
        });
        holder.btAddGuige.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick()) {
                    Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                    it.putExtra("state", "0");
                    it.putExtra("goods", goods);
                    it.putExtra("guige", "1");
                    it.putExtra("bean", bean.getCommodity_id());
                    mContext.startActivity(it);
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }
            }
        });
//        holder.btShangjia.setVisibility(View.VISIBLE);
//        holder.btXiajia.setVisibility(View.VISIBLE);
        holder.btAddGuige.setVisibility(View.VISIBLE);
        switch (bean.getApproval_state()) {
            case "0":
                //上架
                holder.tvXiajia.setVisibility(View.GONE);
                holder.btShangjia.setVisibility(View.GONE);
                holder.btXiajia.setVisibility(View.VISIBLE);
                holder.btXiajia.setText("下架");

                holder.btXiajia.setOnClickListener( new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否下架", bean.getCommodity_id(), "4");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "2":
                //上架
                holder.tvXiajia.setVisibility(View.GONE);
                holder.btShangjia.setVisibility(View.GONE);
                holder.btXiajia.setVisibility(View.VISIBLE);
                holder.btXiajia.setText( "下架");

                holder.btXiajia.setOnClickListener( new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否下架", bean.getCommodity_id(), "4");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "1":
                //下架
                holder.tvXiajia.setVisibility(View.VISIBLE);
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btXiajia.setVisibility(View.GONE);
                holder.btShangjia.setText( "上架");

                holder.btShangjia.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否上架", bean.getCommodity_id(), "2");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "4":
                //下架
                holder.tvXiajia.setVisibility(View.VISIBLE);
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btXiajia.setVisibility(View.GONE);
                holder.btShangjia.setText( "上架");
                holder.btShangjia.setOnClickListener( new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否上架", bean.getCommodity_id(), "2");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "5":
                holder.btBianji.setVisibility(View.GONE);
                holder.btAddGuige.setVisibility(View.GONE);
                holder.btShangjia.setVisibility(View.VISIBLE);
                holder.btShangjia.setText("审核中");
                holder.btXiajia.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bt_add_guige)
        Button btAddGuige;
        @BindView(R.id.iv_sptu)
        ImageView ivSptu;
        @BindView(R.id.tv_xiajia)
        TextView tvXiajia;
        @BindView(R.id.tv_spming)
        TextView tvSpming;
        @BindView(R.id.tv_xiaoliang)
        TextView tvXiaoliang;
        @BindView(R.id.tv_kucun)
        TextView tvKucun;
        @BindView(R.id.tv_danjia)
        TextView tvDanjia;
        @BindView(R.id.bt_bianji)
        Button btBianji;
        @BindView(R.id.bt_xiajia)
        Button btXiajia;
        @BindView(R.id.bt_shangjia)
        Button btShangjia;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
