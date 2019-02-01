package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_ypwd)
    EditText etYpwd;
    @BindView(R.id.et_npwd)
    EditText etNpwd;
    @BindView(R.id.et_cpwd)
    EditText etCpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;


    private Context mContext;
    private boolean[] isClick = new boolean[3];

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initData() {
        tvTitle.setText("修改密码");
        mContext = ChangePwdActivity.this;
        setIsClick();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                for (int i = 0; i < isClick.length; i++) {
                    if (!isClick[i])
                        return;
                }
                if (etNpwd.getText().toString().equals(etCpwd.getText().toString())) {
                    changPwd();
                } else {
                    ToastUtil.showToastLong("两次输入的密码不一致");
                }
                break;
        }
    }

    public void setIsClick() {
        etYpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(" ")) {
                    etYpwd.setText("");
                } else if (s.toString().trim().length() < 6) {
                    isClick[0] = false;
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_click_false));
                } else {
                    isClick[0] = true;
                    for (int i = 0; i < isClick.length; i++) {
                        if (!isClick[i])
                            return;
                    }
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etNpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(" ")) {
                    etNpwd.setText("");
                } else if (s.toString().trim().length() < 6) {
                    isClick[1] = false;
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_click_false));
                } else {
                    isClick[1] = true;
                    for (int i = 0; i < isClick.length; i++) {
                        if (!isClick[i])
                            return;
                    }
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etCpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(" ")) {
                    etCpwd.setText("");
                } else if (s.toString().trim().length() < 6) {
                    isClick[2] = false;
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_click_false));
                } else {
                    isClick[2] = true;
                    for (int i = 0; i < isClick.length; i++) {
                        if (!isClick[i])
                            return;
                    }
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_3));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void changPwd() {
        Log.e("phone", PreferenceUtils.getString(MyApplication.mContext, "phone", ""));
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .changePwd(PreferenceUtils.getString(MyApplication.mContext, "token", ""),PreferenceUtils.getString(MyApplication.mContext, "phone", ""), etYpwd.getText().toString(),
                                "1", etNpwd.getText().toString(), ""))
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
                }, true);
    }
}
