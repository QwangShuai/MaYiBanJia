package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_change_phone)
    Button btnChangePhone;

    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initData() {
        tvTitle.setText("验证身份");
        mContext = ChangePhoneActivity.this;
        tvPhone.setText("已绑定的手机号："+getPhone());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_change_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.btn_change_phone:
                startActivity(new Intent(mContext,YanZhengPhoneActivity.class));
                break;
        }
    }
    public String getPhone(){
        String myphone = PreferenceUtils.getString(MyApplication.mContext,"phone","");
        String mobie = myphone.substring(0,3)+"****"+myphone.substring(7,myphone.length());
        return mobie;
    }
}
