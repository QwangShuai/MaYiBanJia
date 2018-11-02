package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan.GHDOrderActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PollingService;
import com.mingmen.mayi.mayibanjia.utils.PollingUtils;
import com.mingmen.mayi.mayibanjia.utils.dayinji.PrintfManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/8/30.
 */

public class GongYingDuanShouYeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.ll_qiangdan)
    LinearLayout llQiangdan;
    @BindView(R.id.ll_dingdan)
    LinearLayout llDingdan;
    @BindView(R.id.ll_shangpinguanli)
    LinearLayout llShangpinguanli;
    @BindView(R.id.ll_yonghupingjia)
    LinearLayout llYonghupingjia;
    @BindView(R.id.ll_caiwuduizhang)
    LinearLayout llCaiwuduizhang;
    @BindView(R.id.ll_shezhi)
    LinearLayout llShezhi;
    @BindView(R.id.ll_tejiashangpin)
    LinearLayout llTejiashangpin;
    @BindView(R.id.tv_tianjiashangpin)
    TextView tvTianjiashangpin;
    private Context mContext;
    public static GongYingDuanShouYeActivity instance=null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongyingduanshouye;
    }

    @Override
    protected void initData() {
        mContext=GongYingDuanShouYeActivity.this;
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("店铺运营");
        Log.e("--------------","main...");
        if (!PollingUtils.isOpen){
            PollingUtils.startPollingService(mContext,1,PollingService.class, PollingService.ACTION);
        }
        instance=this;
    }



    @OnClick({R.id.ll_qiangdan, R.id.ll_dingdan, R.id.ll_shangpinguanli, R.id.ll_yonghupingjia, R.id.ll_caiwuduizhang, R.id.ll_shezhi, R.id.ll_tejiashangpin, R.id.tv_tianjiashangpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_qiangdan:
                Intent qiangdan=new Intent(mContext,QiangDanActivity.class);
                startActivity(qiangdan);
                break;
            case R.id.ll_dingdan://订单入口
                Intent gonghuodingdan=new Intent(mContext,GHDOrderActivity.class);
                startActivity(gonghuodingdan);
                break;
            case R.id.ll_shangpinguanli:
                Intent shangpinguanli=new Intent(mContext,ShangPinGuanLiActivity.class);
                startActivity(shangpinguanli);
                break;
            case R.id.ll_yonghupingjia:
                break;
            case R.id.ll_caiwuduizhang:
                break;
            case R.id.ll_shezhi:
                Intent shezhi=new Intent(mContext,GongYingDuanSheZhiActivity.class);
                startActivity(shezhi);
                finish();
                break;
            case R.id.ll_tejiashangpin:
                break;
            case R.id.tv_tianjiashangpin:
                Intent caigouintent=new Intent(mContext,FaBuShangPinActivity.class);
                caigouintent.putExtra("state","0");
                startActivity(caigouintent);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop polling service
        System.out.println("Stop polling service...");
        PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
    }

}
