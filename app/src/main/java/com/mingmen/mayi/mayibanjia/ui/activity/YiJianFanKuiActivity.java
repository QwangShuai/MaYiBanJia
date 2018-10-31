package com.mingmen.mayi.mayibanjia.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class YiJianFanKuiActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @Override
    public int getLayoutId() {
        return R.layout.activity_yi_jian_fan_kui;
    }

    @Override
    protected void initData() {
        tvTitle.setText("意见反馈");
    }
    @OnClick({R.id.iv_back,R.id.btn_submit})
    protected void OnClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                if(AppUtil.isMobile(etPhone.getText().toString())){
                    HttpManager.getInstance()
                            .with(YiJianFanKuiActivity.this)
                            .setObservable(RetrofitManager.getService()
                                    .yijianfankui(PreferenceUtils.getString(MyApplication.mContext, "token", ""),etPhone.getText().toString(),
                                            etContent.getText().toString()))
                            .setDataListener(new HttpDataListener<String>() {
                                @Override
                                public void onNext(String data) {
                                    ToastUtil.showToast("意见反馈成功,稍后会有客服联系您");
                                    finish();
                                }
                            });
                } else {
                    ToastUtil.showToast("输入的手机号有误");
                }

                break;
        }
    }
}
