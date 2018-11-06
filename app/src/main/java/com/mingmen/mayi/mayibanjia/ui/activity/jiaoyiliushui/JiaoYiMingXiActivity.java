package com.mingmen.mayi.mayibanjia.ui.activity.jiaoyiliushui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiaoYiMingXiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tabs_jiaoyi)
    PagerSlidingTabStrip tabsJiaoyi;
    @BindView(R.id.vp_jiaoyi)
    ViewPager vpJiaoyi;


    private JYMXAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiao_yi_ming_xi;
    }

    @Override
    protected void initData() {
        tvTitle.setText("交易明细");
        adapter = new JYMXAdapter(getSupportFragmentManager(), JiaoYiMingXiActivity.this);
        vpJiaoyi.setAdapter(adapter);
        tabsJiaoyi.setViewPager(vpJiaoyi);
        vpJiaoyi.setOffscreenPageLimit(0);
        vpJiaoyi.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
