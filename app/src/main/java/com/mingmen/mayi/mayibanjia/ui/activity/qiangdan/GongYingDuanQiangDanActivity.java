package com.mingmen.mayi.mayibanjia.ui.activity.qiangdan;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GongYingDuanQiangDanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tabs_jiaoyi)
    PagerSlidingTabStrip tabsJiaoyi;
    @BindView(R.id.vp_qiangdan)
    ViewPager vpQiangdan;

    private QDFragmentAdapter adapter;
    private int ye = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gong_ying_duan_qiang_dan;
    }

    @Override
    protected void initData() {
        tvTitle.setText("抢单");
        adapter = new QDFragmentAdapter(getSupportFragmentManager(),GongYingDuanQiangDanActivity.this);
        vpQiangdan.setAdapter(adapter);
        tabsJiaoyi.setViewPager(vpQiangdan);
        vpQiangdan.setOffscreenPageLimit(0);
        vpQiangdan.setCurrentItem(getIntent().getIntExtra("ye",0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
        }
    }
}
