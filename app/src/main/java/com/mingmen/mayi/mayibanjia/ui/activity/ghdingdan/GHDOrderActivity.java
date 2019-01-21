package com.mingmen.mayi.mayibanjia.ui.activity.ghdingdan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.OnClick;

public class GHDOrderActivity extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.iv_sousuo)
    ImageView ivSousuo;
    @BindView(R.id.tabs_dingdan)
    PagerSlidingTabStrip tabsDingdan;
    @BindView(R.id.vp_dingdan)
    ViewPager vpDingdan;
    private GHAdapter adapter;
    private int ye = 0;
    private String token="";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ghdorder;
    }

    @Override
    protected void initData() {
        adapter = new GHAdapter(getSupportFragmentManager(),GHDOrderActivity.this);
        vpDingdan.setAdapter(adapter);
        tabsDingdan.setViewPager(vpDingdan);
        vpDingdan.setOffscreenPageLimit(0);
        ye = getIntent().getIntExtra("ye",0);
        setToken(getIntent().getStringExtra("token"));
        vpDingdan.setCurrentItem(ye);
    }
    @OnClick({R.id.iv_fanhui, R.id.iv_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.iv_sousuo:

                break;
        }
    }

    public void dayinQrCode(Bitmap bitmap){
    }
}
