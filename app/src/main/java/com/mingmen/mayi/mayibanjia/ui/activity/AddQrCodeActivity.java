package com.mingmen.mayi.mayibanjia.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.AddQrCodeBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.adapter.AddQrCodeAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dabaoshangpin.DaBaoAdapter;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ThreeDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.ui.view.PagerSlidingTabStrip;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQrCodeActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tabs_add_qr_code)
    PagerSlidingTabStrip tabsAddQrCode;
    @BindView(R.id.vp_add_qr_code)
    ViewPager vpAddQrCode;

    public String getGy_order_id() {
        return gy_order_id;
    }

    public void setGy_order_id(String gy_order_id) {
        this.gy_order_id = gy_order_id;
    }

    private String gy_order_id = "";
    private String son_order_id = "";

    public String getSon_order_id() {
        return son_order_id;
    }

    public void setSon_order_id(String son_order_id) {
        this.son_order_id = son_order_id;
    }

    private DaBaoAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_qr_code;
    }

    @Override
    protected void initData() {
        tvTitle.setText("打包商品");
        setGy_order_id(getIntent().getStringExtra("id"));
        setSon_order_id(getIntent().getStringExtra("son_order_id"));
        adapter = new DaBaoAdapter(getSupportFragmentManager(),AddQrCodeActivity.this);
        vpAddQrCode.setAdapter(adapter);
        tabsAddQrCode.setViewPager(vpAddQrCode);
        vpAddQrCode.setOffscreenPageLimit(0);
        vpAddQrCode.setCurrentItem(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
