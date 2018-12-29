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
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
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
    private String token="";
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdan;
    }

    @Override
    protected void initData() {
        mContext = DingDanActivity.this;
        adapter = new OrderAdapter(getSupportFragmentManager(), DingDanActivity.this);
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        setToken(getIntent().getStringExtra("token"));
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
                finish();
                break;
            case R.id.iv_sousuo:

                break;
            case R.id.iv_sangedian:

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("1111","1111");
        adapter.notifyDataSetChanged();
    }
}
