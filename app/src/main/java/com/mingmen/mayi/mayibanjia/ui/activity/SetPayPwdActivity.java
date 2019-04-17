package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.bean.ZzhQuanXianBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/10/010.
 */

public class SetPayPwdActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_pass1)
    EditText etPass1;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    private Context mContext;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    protected void initData() {
        tvTitle.setText("设置支付密码");
        mContext = SetPayPwdActivity.this;
        etPass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("changed", s.toString());
                String pass1 = etPass1.getText().toString().trim();
                if (pass1.length() >= 6 && pass1.length() <= 16) {
                    if (pass1.equals(s.toString().trim())) {
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                        btXiayibu.setEnabled(true);
                    } else {
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                        btXiayibu.setEnabled(false);
                    }
                } else {
                    btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                    btXiayibu.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("changed", s.toString());
                String pass1 = etPass2.getText().toString().trim();
                if (pass1.length() >= 6 && pass1.length() <= 16) {
                    if (pass1.equals(s.toString().trim())) {
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                        btXiayibu.setEnabled(true);
                    } else {
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                        btXiayibu.setEnabled(false);
                    }
                } else {
                    btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                    btXiayibu.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回键
                finish();
                break;
            case R.id.tv_right:
                //标题栏右边按键
                break;
            case R.id.bt_xiayibu:
                //下一步
                setPayPwd();
                break;
        }
    }


    private void setPayPwd() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .setPayPwd(PreferenceUtils.getString(MyApplication.mContext, "token", ""),
                                etPass1.getText().toString().trim()))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToastLong("密码设置成功");
                        finish();
                    }
                }, true);
    }
}
