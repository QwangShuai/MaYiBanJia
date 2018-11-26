package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/9/25.
 */

public class ShangPinGuanLiAdapter extends BaseQuickAdapter<ShangPinGuanLiBean.GoodsListBean,BaseViewHolder> {
    private ShangPinGuanLiActivity activity;
    private ConfirmDialog confirmDialog;
    public ShangPinGuanLiAdapter(ShangPinGuanLiActivity activity) {
        super(R.layout.item_shangpinguanli);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShangPinGuanLiBean.GoodsListBean item) {
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        Glide.with(mContext).load(item.getPicture_url()).into((ImageView) helper.getView(R.id.iv_sptu));
        helper.setText(R.id.tv_spming,item.getCommodity_name());
        helper.setText(R.id.tv_xiaoliang,"已售"+item.getSumGoodsSales());
        helper.setText(R.id.tv_kucun,"库存"+item.getInventory());
        helper.setText(R.id.tv_danjia,"¥ "+item.getPrice());
        helper.setOnClickListener(R.id.bt_bianji, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getApproval_state().equals("0")||item.getApproval_state().equals("2")){
                    ToastUtil.showToast("上架不能编辑，请先下架");
                } else {
                    Intent it = new Intent(mContext, FaBuShangPinActivity.class);
                    it.putExtra("state","1");
                    it.putExtra("bean",item.getCommodity_id());
                    mContext.startActivity(it);
                }
            }
        });
        //上下架状态

        switch (item.getApproval_state()){
            case "0":
                //上架
                helper.setVisible(R.id.tv_xiajia,false);
                helper.getView(R.id.bt_shangjia).setVisibility(View.GONE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.VISIBLE);
                helper.setText(R.id.bt_xiajia,"下架");
                helper.setOnClickListener(R.id.bt_xiajia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("是否下架",item.getCommodity_id(),"4");
                    }
                });
                break;
            case "2":
                //上架
                helper.setVisible(R.id.tv_xiajia,false);
                helper.getView(R.id.bt_shangjia).setVisibility(View.GONE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.VISIBLE);
                helper.setText(R.id.bt_xiajia,"下架");
                helper.setOnClickListener(R.id.bt_xiajia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("是否下架",item.getCommodity_id(),"4");
                    }
                });
                break;
            case "1":
                //下架
                helper.setVisible(R.id.tv_xiajia,true);
                helper.getView(R.id.bt_shangjia).setVisibility(View.VISIBLE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.GONE);
                helper.setText(R.id.bt_shangjia,"上架");
                helper.setOnClickListener(R.id.bt_shangjia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("是否上架",item.getCommodity_id(),"2");
                    }
                });
                break;
            case "4":
                //下架
                helper.setVisible(R.id.tv_xiajia,true);
                helper.getView(R.id.bt_shangjia).setVisibility(View.VISIBLE);
                helper.getView(R.id.bt_xiajia).setVisibility(View.GONE);
                helper.setText(R.id.bt_shangjia,"上架");
                helper.setOnClickListener(R.id.bt_shangjia, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog("是否上架",item.getCommodity_id(),"2");
                    }
                });
                break;

        }
    }
    public void updateState(String id, final String type){
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                . ghdspdel(PreferenceUtils.getString(MyApplication.mContext, "token",""), id,type))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        confirmDialog.dismiss();
                        activity.setType("0");
                        notifyDataSetChanged();
                    }
                },false);
    }

    public void showDialog(String title, final String id, final String type){
        confirmDialog.showDialog(title);
        confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateState(id,type);
            }
        });
        confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
    }
}
