package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WoDeZhangHuActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_xiugai_pwd)
    ImageView ivXiugaiPwd;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_xiugai_phone)
    ImageView ivXiugaiPhone;

    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wo_de_zhang_hu;
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的账户");
        mContext = WoDeZhangHuActivity.this;
        tvPhone.setText(getPhone());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_pwd, R.id.tv_phone, R.id.ll_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_pwd:
                startActivity(new Intent(WoDeZhangHuActivity.this,ChangePwdActivity.class));
                break;
//            case R.id.tv_phone:
//                break;
            case R.id.ll_phone:
                startActivity(new Intent(mContext,ChangePhoneActivity.class));
                break;
        }
    }

    public String getPhone(){
        String myphone = PreferenceUtils.getString(MyApplication.mContext,"phone","");
        if(StringUtil.isValid(myphone)){
            String mobie = myphone.substring(0,3)+"****"+myphone.substring(7,myphone.length());
            return mobie;
        }

        return "手机号怎么没有了呢";
    }
}
