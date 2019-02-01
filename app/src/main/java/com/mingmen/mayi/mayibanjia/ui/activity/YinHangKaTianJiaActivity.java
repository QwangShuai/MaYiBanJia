package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.YinHangKaRzBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ChiKaShuoMingDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.JumpUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YinHangKaTianJiaActivity extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_wenhao)
    ImageView ivWenhao;
    @BindView(R.id.tv_yinhang)
    TextView tvYinhang;
    @BindView(R.id.iv_jinru)
    ImageView ivJinru;
    @BindView(R.id.et_kahao)
    EditText etKahao;
    @BindView(R.id.rl_kahao)
    RelativeLayout rlKahao;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.et_shoujihao)
    EditText etShoujihao;
    @BindView(R.id.rl_yinhang)
    RelativeLayout rlYinhang;
    private Context mContext;
    private String son_number = "";
    private String phone = "";
    private String bank_account = "";
    private String id_number = "";
    private String principal = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_yin_hang_ka_tian_jia;
    }

    @Override
    protected void initData() {
        mContext = YinHangKaTianJiaActivity.this;
        principal = getIntent().getStringExtra("principal");
        tvName.setText(principal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_yinhang, R.id.iv_fanhui, R.id.btn_next, R.id.iv_wenhao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.iv_wenhao:
                new ChiKaShuoMingDialog().show(getSupportFragmentManager());
                break;
            case R.id.rl_yinhang:
                startActivityForResult(new Intent(mContext, XuanZeYinHangActivity.class), 1);
                break;
            case R.id.btn_next:
                if (isAddBankCard()) {
                    getRz();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1 && requestCode == 1) {
            String result = data.getExtras().getString("son_number");
            String son_name = data.getExtras().getString("son_name");
            if (!TextUtils.isEmpty(result)) {
                son_number = result;
                tvYinhang.setText(son_name);
                rlKahao.setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean isAddBankCard() {
        boolean b = false;
        phone = etShoujihao.getText().toString().trim();
        id_number = etShenfenzheng.getText().toString().trim();
        bank_account = etKahao.getText().toString().trim();
        if (!AppUtil.isMobile(phone)) {
            ToastUtil.showToast("请检查手机号");
        } else if (!StringUtil.isLegalId(id_number)) {
            ToastUtil.showToast("身份证号格式不正确");
        } else if (TextUtils.isEmpty(bank_account)) {
            ToastUtil.showToast("银行账号不能为空");
        } else if (TextUtils.isEmpty(son_number)) {
            ToastUtil.showToast("请选择开户行");
        } else {
            b = true;
        }
        return b;
    }

    public void addBankCard() {//添加银行卡
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .addBankCard(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bank_account, principal,
                                id_number, phone, son_number))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        ToastUtil.showToast("成功");
                        finish();
                    }
                });
    }
    public void getRz() {//添加银行卡
        HttpManager.getInstance().with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getYinhangZzrz(PreferenceUtils.getString(MyApplication.mContext, "token", ""), bank_account, principal,
                                id_number, phone))
                .setDataListener(new HttpDataListener<YinHangKaRzBean>() {
                    @Override
                    public void onNext(YinHangKaRzBean data) {
                        Log.e("onNext: ",new Gson().toJson(data));
                        if(data.getRespCode().equals("0000")){
                            addBankCard();
                        } else {
                            ToastUtil.showToastLong(data.getRespMessage());
                        }

                    }
                });
    }
}
