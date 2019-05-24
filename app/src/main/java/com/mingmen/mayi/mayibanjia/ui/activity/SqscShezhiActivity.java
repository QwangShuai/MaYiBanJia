package com.mingmen.mayi.mayibanjia.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.PhoneBean;
import com.mingmen.mayi.mayibanjia.bean.ZhangHuRenZhengBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.ConfirmDialog;
import com.mingmen.mayi.mayibanjia.ui.activity.dialog.PhoneDialog;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SqscShezhiActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_zhanghu)
    ImageView ivZhanghu;
    @BindView(R.id.ll_zhanghu)
    LinearLayout llZhanghu;
    @BindView(R.id.iv_yhzh)
    ImageView ivYhzh;
    @BindView(R.id.tv_zzrz)
    TextView tvZzrz;
    @BindView(R.id.iv_zzrz)
    ImageView ivZzrz;
    @BindView(R.id.ll_yhpj)
    LinearLayout llYhpj;
    @BindView(R.id.ll_wdpj)
    LinearLayout llWdpj;
    @BindView(R.id.iv_kfdh)
    ImageView ivKfdh;
    @BindView(R.id.iv_cjwt)
    ImageView ivCjwt;
    @BindView(R.id.iv_yjfk)
    ImageView ivYjfk;
    @BindView(R.id.tv_tuichu)
    TextView tvTuichu;
    private Context mContext;
    private ConfirmDialog confirmDialog;
    private String sh_state="待审核";
    private String khd = "sqsc";

    @Override
    public int getLayoutId() {
        return R.layout.activity_sqsc_shezhi;
    }

    @Override
    protected void initData() {
        tvTitle.setText("设置");
        mContext=this;
        confirmDialog = new ConfirmDialog(mContext,
                mContext.getResources().getIdentifier("CenterDialog", "style", mContext.getPackageName()));
        khd = getIntent().getStringExtra("khd");
        if(khd.equals("ct")){
            llYhpj.setVisibility(View.GONE);
        } else if(khd.equals("gy")){
            llWdpj.setVisibility(View.GONE);
        }
        getRenzheng();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_title, R.id.iv_back, R.id.tv_right, R.id.iv_zhanghu, R.id.iv_yhzh,
            R.id.tv_zzrz, R.id.iv_zzrz, R.id.ll_yhpj, R.id.ll_wdpj, R.id.iv_kfdh, R.id.iv_cjwt,
            R.id.iv_yjfk, R.id.tv_tuichu})
    public void onViewClicked(View view) {
        Intent it;
        switch (view.getId()) {
            case R.id.tv_title:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                break;
            case R.id.iv_zhanghu:
                it = new Intent(mContext,WoDeZhangHuActivity.class);
                startActivity(it);
                break;
            case R.id.iv_yhzh:
                Jump_intent(YinHangKaActivity.class, new Bundle());
                break;
            case R.id.tv_zzrz:
                it = new Intent(mContext, ZiZhiRenZhengActivity.class);
                it.putExtra("id", "");
                it.putExtra("state",sh_state);
                it.putExtra("yemian","1");
                startActivity(it);
                break;
            case R.id.iv_zzrz:
                it = new Intent(mContext, ZiZhiRenZhengActivity.class);
                it.putExtra("id", "");
                it.putExtra("state",sh_state);
                it.putExtra("yemian","1");
                startActivity(it);
                break;
            case R.id.ll_yhpj:
                //查看评价
                Jump_intent(YongHuPingJiaActivity.class,new Bundle());
                break;
            case R.id.ll_wdpj:
                Jump_intent(WoDePingJiaActivity.class, new Bundle());
                break;
            case R.id.iv_kfdh:
                getPhone();
                break;
            case R.id.iv_cjwt:
                ToastUtil.showToastLong("emmmmmmmmm");
                break;
            case R.id.iv_yjfk:
                startActivity(new Intent(mContext, YiJianFanKuiActivity.class));
                break;
            case R.id.tv_tuichu:
                confirmDialog.showDialog("是否确定退出当前账号");
                confirmDialog.getTvSubmit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitLogin();
                    }
                });
                confirmDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                break;
        }
    }
    private void getRenzheng() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getGerenrenzheng(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<ZhangHuRenZhengBean>() {
                    @Override
                    public void onNext(ZhangHuRenZhengBean bean) {
                        sh_state = bean.getZz().toString();
                        tvZzrz.setText(bean.getZz().toString());
                    }
                });
    }

    private void exitLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .exitLogin(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<String>() {
                    @Override
                    public void onNext(String data) {
                        confirmDialog.dismiss();
                        goLogin(mContext,"login");
                    }
                });
    }

    public void getPhone() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(RetrofitManager.getService()
                        .getPhone(PreferenceUtils.getString(MyApplication.mContext, "token", ""), ""))
                .setDataListener(new HttpDataListener<PhoneBean>() {

                    @Override
                    public void onNext(PhoneBean bean) {
                        new PhoneDialog().setData(bean.getKefu(),
                                bean.getYewuyuan(), bean.getKefuphone(), bean.getYwyphone()).show(getSupportFragmentManager());
                    }
                }, false);

    }

    @Override
    protected void onResume() {
        getRenzheng();
        super.onResume();
    }
}
