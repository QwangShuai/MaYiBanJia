package com.mingmen.mayi.mayibanjia.ui.activity.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ShouCangBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.SPXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.TubiaoActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.JiaRuGouWuCheDialog;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ShouCangAdapter extends BaseQuickAdapter<ShouCangBean,BaseViewHolder> {
    public ShouCangAdapter() {
        super(R.layout.item_shoucang);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShouCangBean item) {
        helper.setText(R.id.tv_spming,item.getCommodity_name());
        helper.setText(R.id.tv_guige,item.getPackStandard());
        helper.setText(R.id.tv_dianming,item.getCompany_name());
        helper.setText(R.id.tv_danjia,item.getPrice());

        if("3".equals(item.getApproval_state())){
            helper.setText(R.id.tv_zhuangtai,"该商品已被商家删除");
        }else if ("4".equals(item.getApproval_state())){
            helper.setText(R.id.tv_zhuangtai,"该商品已下架");
        }else{
            helper.setText(R.id.tv_zhuangtai,"");
        }
        Glide.with(mContext).load(item.getPicture_url()).into((ImageView) helper.getView(R.id.iv_sptu));
        helper.getView(R.id.iv_addcar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        final JiaRuGouWuCheDialog jiarugouwuchedialog = new JiaRuGouWuCheDialog(mContext,
                mContext.getResources().getIdentifier("BottomDialog", "style", mContext.getPackageName()));
        jiarugouwuchedialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.LEFT | Gravity.RIGHT);
        jiarugouwuchedialog.showDialog(item.getInventory(), item.getCommodity_name(), item.getSpec_describe(), item.getRation_one() + "", item.getPice_one() + ""
                , item.getRation_two() + "", item.getPice_two() + "", item.getRation_three() + "", item.getPice_three() + "", item.getPicture_url());

        String guigeid = "";
        if (item.getChoose_specifications() != 0) {
            switch (item.getChoose_specifications()) {
                case 1:
                    guigeid = item.getPack_standard_one();
                    break;
                case 2:
                    guigeid = item.getPack_standard_two();
                    break;
                case 3:
                    guigeid = item.getPack_standard_tree();
                    break;
            }
        }
        final String finalGuigeid = guigeid;
        jiarugouwuchedialog.getBtQueding().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shuliang = jiarugouwuchedialog.getEtShuliang().getText().toString().trim();
                Log.e("data.getShopping_id()", item.getShopping_id() + "---");
                    if (Integer.parseInt(shuliang) >= Integer.parseInt(item.getRation_one())) {
                        addcar(item.getCommodity_id(), shuliang, item.getCompany_id(), "", finalGuigeid);
                    } else {
                        ToastUtil.showToast("不够起订量");
                    }
                Log.e("jiarugouwuche", jiarugouwuchedialog.getEtShuliang().getText().toString().trim());
                jiarugouwuchedialog.getEtShuliang().setText("0");
                jiarugouwuchedialog.cancel();

            }
        });
            }
        });
//
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("3".equals(item.getApproval_state())){
                    ToastUtil.showToast("该商品已被商家删除");
                }else if ("4".equals(item.getApproval_state())){
                    ToastUtil.showToast("该商品已下架");
                }else{
                    Bundle bundle=new Bundle();
                    bundle.putString("spid",item.getCommodity_id());
                    Log.e("spid",item.getCommodity_id()+"---");
                    JumpUtil.Jump_intent(mContext, SPXiangQingActivity.class,bundle);
                }
            }
        });
        helper.getView(R.id.iv_zoushitu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //走势图
                Intent zoushi=new Intent(mContext,TubiaoActivity.class);
                zoushi.putExtra("mark_id",item.getSon_number());//市场id
                zoushi.putExtra("market_name",item.getMarket_name());//市场名
                zoushi.putExtra("classify_id",item.getType_tree_id());//三级分类id
                zoushi.putExtra("classify_name",item.getClassify_name());//三级分类名称
//                Log.e("classify_name",item.getClassify_name()+"-");
                mContext.startActivity(zoushi);
            }
        });

    }


    //添加购物车
    private void addcar(final String spid, String shuliang, String dianpuid, String gouwucheid, String guigeid) {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .addcar(PreferenceUtils.getString(MyApplication.mContext, "token", ""), spid, shuliang, dianpuid, gouwucheid, guigeid))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("添加成功");

                    }
                });
    }


}
