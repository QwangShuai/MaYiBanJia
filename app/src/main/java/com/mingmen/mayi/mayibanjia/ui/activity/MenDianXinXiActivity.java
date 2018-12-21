package com.mingmen.mayi.mayibanjia.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenDianXinXiActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_touxiang)
    ImageView ivTouxiang;
    @BindView(R.id.tv_zizhi_state)
    TextView tvZizhiState;
    @BindView(R.id.ll_zizhi)
    LinearLayout llZizhi;
    @BindView(R.id.tv_yinhangka_state)
    TextView tvYinhangkaState;
    @BindView(R.id.ll_yinhangka)
    LinearLayout llYinhangka;

    @Override
    public int getLayoutId() {
        return R.layout.activity_men_dian_xin_xi;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.ll_zizhi, R.id.ll_yinhangka})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.ll_zizhi:
                break;
            case R.id.ll_yinhangka:
                break;
        }
    }
}
