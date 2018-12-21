package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneBindActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.bt_yanzhengma)
    Button btYanzhengma;
    @BindView(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;

    private Context mContext;
    private boolean runningThree = true;
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            btYanzhengma.setText((l / 1000) + "秒");
            btYanzhengma.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
        }

        @Override
        public void onFinish() {
            runningThree = true;
            btYanzhengma.setText("重新发送");
            btYanzhengma.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_change_phone_bind;
    }

    @Override
    protected void initData() {
        tvTitle.setText("绑定新手机号");
        mContext = ChangePhoneBindActivity.this;
        etYanzhengma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length()==6){
                    btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                    btXiayibu.setEnabled(true);
                }else{
                    btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                    btXiayibu.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_yanzhengma, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.bt_yanzhengma:
                if(AppUtil.isMobile(etPhone.getText().toString().trim())){
                    getCode();
                } else {
                    ToastUtil.showToast("手机格式不正确，请重新填写");
                }
                break;
            case R.id.bt_xiayibu:
                if(AppUtil.isMobile(etPhone.getText().toString().trim())){
                    querenYanzhengma();
                } else {
                    ToastUtil.showToast("手机格式不正确，请重新填写");
                }
                break;
        }
    }
    private void getCode() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                . getcode("4", etPhone.getText().toString().toString()))
                .setDataListener(new HttpDataListener<String>() {

                    @Override
                    public void onNext(String data) {
                        downTimer.start();
                        runningThree = false;
                        ToastUtil.showToast("已发送验证码");

                    }
                });

    }

    private void querenYanzhengma() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                . yanzhengcode(etPhone.getText().toString().toString(),etYanzhengma.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        HttpManager.getInstance()
                                .with(mContext)
                                .setObservable(
                                        RetrofitManager
                                                .getService()
                                                . changePhone(PreferenceUtils.getString(MyApplication.mContext,"token",""),etPhone.getText().toString().trim()))
                                .setDataListener(new HttpDataListener<String>() {
                                    @Override
                                    public void onNext(String data) {
                                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", false);
                                        Intent intent = new Intent(mContext, LoginActivity.class);
                                        startActivity(intent);
                                        if(MainActivity.instance!=null){
                                            MainActivity.instance.finish();
                                        }
                                        finish();
                                    }
                                },true);
                    }
                },false);


    }
}
