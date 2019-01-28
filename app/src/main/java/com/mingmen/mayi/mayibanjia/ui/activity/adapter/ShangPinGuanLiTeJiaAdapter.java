package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShangPinGuanLiBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.FaBuShangPinActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.ShangPinGuanLiTeJiaActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiTeJiaAdapter extends BaseQuickAdapter<ShangPinGuanLiBean.GoodsListBean, BaseViewHolder> {
    private ShangPinGuanLiTeJiaActivity activity;
    private ConfirmDialog confirmDialog;
    private String goods = "0";

//    public String getGoods() {
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

    public ShangPinGuanLiTeJiaAdapter(ShangPinGuanLiTeJiaActivity activity, String goods) {
        super(R.layout.item_shangpinguanli);
        this.activity = activity;
        this.goods = goods;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShangPinGuanLiBean.GoodsListBean item) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        Glide.with(mContext).load(item.getPicture_url()).into((ImageView) helper.getView(R.id.iv_sptu));
        helper.setText(R.id.tv_spming, item.getClassify_name());
        helper.setText(R.id.tv_xiaoliang, "已售" + item.getSumGoodsSales());
        helper.setText(R.id.tv_kucun, "库存" + item.getInventory());
        helper.setText(R.id.tv_danjia, "¥ " + item.getPrice());
        helper.setOnClickListener(R.id.bt_bianji, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick()) {
                    if (item.getApproval_state().equals("0") || item.getApproval_state().equals("2")) {
                        ToastUtil.showToast("上架不能编辑，请先下架");
                    } else {
                        Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                        it.putExtra("state", "1");
                        it.putExtra("goods", goods);
                        it.putExtra("bean", item.getCommodity_id());
                        mContext.startActivity(it);
                    }
                    //上下架状态
                } else {
                    ToastUtil.showToastLong("请注意，您只有阅览权限");
                }

            }
        });
        switch (item.getApproval_state()) {
            case "0":
                //上架
                helper.setVisible(R.id.tv_xiajia, false);
                helper.getView(R.id.bt_shangjia).setVisibility(View.GONE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.VISIBLE);
                helper.setText(R.id.bt_xiajia, "下架");

                helper.setOnClickListener(R.id.bt_xiajia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否下架", item.getCommodity_id(), "4");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "2":
                //上架
                helper.setVisible(R.id.tv_xiajia, false);
                helper.getView(R.id.bt_shangjia).setVisibility(View.GONE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.VISIBLE);
                helper.setText(R.id.bt_xiajia, "下架");

                helper.setOnClickListener(R.id.bt_xiajia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否下架", item.getCommodity_id(), "4");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "1":
                //下架
                helper.setVisible(R.id.tv_xiajia, true);
                helper.getView(R.id.bt_shangjia).setVisibility(View.VISIBLE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.GONE);
                helper.setText(R.id.bt_shangjia, "上架");

                helper.setOnClickListener(R.id.bt_shangjia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否上架", item.getCommodity_id(), "2");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;
            case "4":
                //下架
                helper.setVisible(R.id.tv_xiajia, true);
                helper.getView(R.id.bt_shangjia).setVisibility(View.VISIBLE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.GONE);
                helper.setText(R.id.bt_shangjia, "上架");
                helper.setOnClickListener(R.id.bt_shangjia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isClick()) {
                            showDialog("是否上架", item.getCommodity_id(), "2");
                        } else {
                            ToastUtil.showToastLong("请注意，您只有阅览权限");
                        }

                    }
                });
                break;

        }


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
}
