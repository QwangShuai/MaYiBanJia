package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.bean.DdxqBean;
import com.mingmen.mayi.mayibanjia.ui.activity.DianPuActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.DingDanXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.WeiYiQrCodeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.XuanZeZhiFuFangShiActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/26.
 */

public class DdXqShichangAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
        public static final int TYPE_LEVEL_0 = 0;
        public static final int TYPE_LEVEL_1 = 1;
        public static final int TYPE_LEVEL_2 = 2;
        private DingDanXiangQingActivity activity;
    private ConfirmDialog dialog;
        //    private DingDanXiangQingActivity activity;
    public DdXqShichangAdapter(List<MultiItemEntity> data,DingDanXiangQingActivity activity) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.item_shichang_ddxq);
            addItemType(TYPE_LEVEL_1, R.layout.item_dianpu_ddxq);
            addItemType(TYPE_LEVEL_2, R.layout.item_shangpin_ddxq);
            this.activity = activity;
        }

        @Override
        protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
            switch (helper.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final DdxqBean.MarketBean item0 = (DdxqBean.MarketBean) item;
                    if (item0.getOrderState().equals("401")) {//待付款
                        helper.getView(R.id.rl_rongqi).setVisibility(View.GONE);
                        helper.getView(R.id.tv_daiquhuo).setVisibility(View.GONE);
                    }
//                else if(item0.getOrderState().equals("402")){//待发货
//
//                }
                    else {
                        if(TextUtils.isEmpty(item0.getDriver_name())){
                            helper.getView(R.id.rl_rongqi).setVisibility(View.GONE);
                        } else {
                            // 已发货==已完成
                            helper.getView(R.id.tv_daiquhuo).setVisibility(View.GONE);
                            helper.setText(R.id.tv_peisongyuan, "配送员:" + item0.getDriver_name());
                            helper.setText(R.id.tv_phone, item0.getDriver_phone());
                            helper.setText(R.id.tv_chepaihao, item0.getPlate_number());
                            helper.setText(R.id.tv_fahuoshijian, "发货时间:" + item0.getUpdate_time());
                        }
                    }
                    helper.setText(R.id.tv_shichang, item0.getMarket_name());
                    helper.getView(R.id.iv_dianhua).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CallPhone(item0.getDriver_phone());
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    Log.e("我的2级", "1111111111111");
                    final DdxqBean.MarketBean.DplistBean item1 = (DdxqBean.MarketBean.DplistBean) item;
                    helper.setText(R.id.tv_dianpuming, String.valueOf(item1.getCompany_name()));
//                    if(item1.getScanState().equals("0")){
//                        helper.getView(R.id.ll_baozhuang).setVisibility(View.GONE);
//                    } else {
//                        helper.getView(R.id.ll_saoma).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                activity.saomiaoQrCode();
//                            }
//                        });
//                        helper.getView(R.id.tv_quhuoma).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent it = new Intent(mContext,WeiYiQrCodeActivity.class);
//                                it.putExtra("gyID",item1.getGy_order_id());
//                                mContext.startActivity(it);
//                            }
//                        });
//                        helper.setText(R.id.tv_baozhuanggeshu,item1.getPackCount()+"个");
//                        helper.setText(R.id.tv_saomageshu,item1.getScanCount()+"个");
//                    }

                    helper.getView(R.id.ll_mydianpu).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("dianpuid", item1.getCompany_id());
                            JumpUtil.Jump_intent(mContext, DianPuActivity.class,bundle);
                        }
                    });

                    helper.getView(R.id.ll_phone).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CallPhone(item1.getTelephone());
                        }
                    });
                    break;
                case TYPE_LEVEL_2:
                    final DdxqBean.MarketBean.DplistBean.ListspBean item2 = (DdxqBean.MarketBean.DplistBean.ListspBean) item;
                    Glide.with(mContext).load(item2.getHostPicture()).into((ImageView) helper.getView(R.id.iv_sptu));
                    helper.setText(R.id.tv_spming, item2.getCommodity_name());
                    helper.setText(R.id.tv_guige, item2.getPackStandard());
                    helper.setText(R.id.tv_jiage, item2.getPrice());
                    if(TextUtils.isEmpty(item2.getAppend_money()+"")||item2.getAppend_money()==null){
                        helper.getView(R.id.tv_fujiafei).setVisibility(View.GONE);
                    } else {
                        helper.setText(R.id.tv_fujiafei,"(附加费："+item2.getAppend_money()+")");
                    }
                    helper.setText(R.id.tv_shuliang, "x" + item2.getAcount());
                    if(item2.isEnd()){
                        helper.getView(R.id.ll_tongji).setVisibility(View.VISIBLE);
                        helper.setText(R.id.tv_jianshu,"共"+item2.getShu()+"件商品");
                        activity.setJiaGeShowView((TextView) helper.getView(R.id.tv_zongjia1),(TextView)helper.getView(R.id.tv_zongjia2),item2.getDpprice()+"");
                        helper.getView(R.id.btn_fukuan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog = new ConfirmDialog(mContext,
                                        mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
                                dialog.showDialog("是否确认订单完成");
                                dialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        activity.confirmOrder();
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
                    }
                    helper.getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle=new Bundle();
                            bundle.putString("spid",item2.getCommodity_id());
                            JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
                        }
                    });
                    break;
            }
        }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
