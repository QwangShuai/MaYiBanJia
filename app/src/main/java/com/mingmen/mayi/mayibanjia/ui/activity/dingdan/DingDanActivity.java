package com.mingmen.mayi.mayibanjia.ui.activity.dingdan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.PeiSongXiangQingActivity;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.qrCode.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/28/028.
 */

public class DingDanActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.iv_sangedian)
    ImageView ivSangedian;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    ViewPager vpDingdan;
    private OrderAdapter adapter;
    private Context mContext;
    private String qyid="";

    public String getQyid() {
        return qyid;
    }

    public void setQyid(String qyid) {
        this.qyid = qyid;
    }

    private String type="";
    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdan;
    }

    @Override
    protected void initData() {
        mContext = DingDanActivity.this;
        Log.e("getItem: ",PreferenceUtils.getString(MyApplication.mContext,"role","") );
        adapter = new OrderAdapter(getSupportFragmentManager(), DingDanActivity.this);
        if(StringUtil.isValid(getIntent().getStringExtra("roleDdType"))){
            type = getIntent().getStringExtra("roleDdType");
            if(type.equals("1")){
                adapter.setFragments("1");
            } else if(type.equals("4")){
                adapter.setFragments("4");
            } else {
                adapter.setFragments("10");
            }
        }
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        setQyid(getIntent().getStringExtra("qyid"));
        vpDingdan.setOffscreenPageLimit(0);
        /**
         * 跳转传过来的页面，到哪个
         */
        int tosome = getIntent().getIntExtra("to_shop",0);
        vpDingdan.setCurrentItem(tosome);

    }


    @OnClick({R.id.iv_fanhui, R.id.iv_sousuo, R.id.iv_sangedian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.iv_sousuo:

                break;
            case R.id.iv_sangedian:

                break;
        }
    }

}
