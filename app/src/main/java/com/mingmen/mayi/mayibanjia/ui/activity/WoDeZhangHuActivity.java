package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

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
    @BindView(R.id.tv_set_paypwd)
    TextView tvSetPaypwd;
    @BindView(R.id.ll_set_paypwd)
    LinearLayout llSetPaypwd;
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
        selectPayPwd();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.ll_pwd, R.id.ll_phone, R.id.ll_pwd_zf, R.id.ll_set_paypwd})
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
            case R.id.ll_pwd_zf:
                startActivity(new Intent(mContext,ChangePayPwdActivity.class));
                break;
            case R.id.ll_set_paypwd:
                startActivity(new Intent(mContext,SetPayPwdActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPhone();
        selectPayPwd();
    }

    public String getPhone(){
        String myphone = PreferenceUtils.getString(MyApplication.mContext,"phone","");
        if(StringUtil.isValid(myphone)){
            String mobie = myphone.substring(0,3)+"****"+myphone.substring(7,myphone.length());
            return mobie;
        }
        return "手机号怎么没有了呢";
    }

    private void selectPayPwd() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .selectPayPwd(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {

                    @Override
                    public void onNext(String data) {
                        if(data.equals("0")){
                            tvSetPaypwd.setText("已设置");
                            tvSetPaypwd.setTextColor(mContext.getResources().getColor(R.color.green_6dd46d));
                            llSetPaypwd.setEnabled(false);
                        } else {
                            tvSetPaypwd.setText("未设置");
                            tvSetPaypwd.setTextColor(mContext.getResources().getColor(R.color.red_ff3300));
                            llSetPaypwd.setEnabled(true);
                        }
                    }
                });
    }

}
