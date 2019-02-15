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

public class ZhuCeActivity extends BaseActivity {
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
    @BindView(R.id.et_pass1)
    EditText etPass1;
    @BindView(R.id.et_pass2)
    EditText etPass2;
    @BindView(R.id.bt_xiayibu)
    Button btXiayibu;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.ll_zhuce)
    LinearLayout llZhuce;
    private Context mContext;
    private boolean runningThree = true;
    private String yemian = "1";
    private String zzh="";
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
        return R.layout.activity_zhuce;
    }

    @Override
    protected void initData() {
        yemian = getIntent().getStringExtra("yemian");
        if(yemian.equals("1")){
            tvTitle.setText("注册");
        } else {
            tvTitle.setText("登录");
            llZhuce.setVisibility(View.GONE);
        }
        mContext=ZhuCeActivity.this;
        if(yemian.equals("1")){
            etPass2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e("changed",s.toString());
                    String pass1 = etPass1.getText().toString().trim();
                    if (pass1.length()>=8&&pass1.length()<=16){
                        if (pass1.equals(s.toString().trim())){
                            if (etYanzhengma.getText().toString().trim().length()==6){
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                                btXiayibu.setEnabled(true);
                            }else{
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                                btXiayibu.setEnabled(false);
                            }
                        }else{
                            btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                            btXiayibu.setEnabled(false);
                        }
                    }else{
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
                    Log.e("changed",s.toString());
                    String pass1 = etPass2.getText().toString().trim();
                    if (pass1.length()>=8&&pass1.length()<=16){
                        if (pass1.equals(s.toString().trim())){
                            if (etYanzhengma.getText().toString().trim().length()==6){
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                                btXiayibu.setEnabled(true);
                            }else{
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                                btXiayibu.setEnabled(false);
                            }
                        }else{
                            btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                            btXiayibu.setEnabled(false);
                        }
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

        etYanzhengma.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("changed",s.toString());
                if(yemian.equals("1")){
                    String pass1 = etPass1.getText().toString().trim();
                    String pass2 = etPass2.getText().toString().trim();
                    if (pass1.length()>=8&&pass1.length()<=16){
                        if (pass1.equals(pass2)){
                            if (s.toString().trim().length()==6){
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                                btXiayibu.setEnabled(true);
                            }else{
                                btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                                btXiayibu.setEnabled(false);
                            }
                        }else{
                            btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                            btXiayibu.setEnabled(false);
                        }
                    }else{
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                        btXiayibu.setEnabled(false);
                    }
                } else {
                    if (s.toString().trim().length()==6){
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
                        btXiayibu.setEnabled(true);
                    }else{
                        btXiayibu.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
                        btXiayibu.setEnabled(false);
                    }
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_right, R.id.bt_yanzhengma, R.id.bt_xiayibu, R.id.tv_xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回键
                finish();
                break;
            case R.id.tv_right:
                //标题栏右边按键
                break;
            case R.id.bt_yanzhengma:
                //获取验证码
                if (AppUtil.isMobile(etPhone.getText().toString().trim())){
                    getCode();
                }else{
                    ToastUtil.showToast("手机格式不正确，请重新填写");
                }
                break;
            case R.id.bt_xiayibu:
                //下一步
//                btXiayibu.setEnabled(false);
                if(yemian.equals("1")){
                    querenYanzhengma();
                } else {
                    login();
                }

//                String pass1 = etPass1.getText().toString().trim();
//                String pass2 = etPass2.getText().toString().trim();
//                String yanzhengma = etYanzhengma.getText().toString().trim();
//                String phone = etPhone.getText().toString().trim();
//                Intent intent=new Intent(mContext,XuanZeJueSeActivity.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("phone",phone);
//                bundle.putString("pass1",pass1);
//                bundle.putString("pass2",pass2);
//                bundle.putString("yanzhengma",yanzhengma);
//                intent.putExtras(bundle);
//                startActivity(intent);

                break;
            case R.id.tv_xieyi:
                //协议内容
                break;
        }
    }

    private void querenYanzhengma() {
            HttpManager.getInstance()
                    .with(ZhuCeActivity.this)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    . yanzhengcode(PreferenceUtils.getString(MyApplication.mContext, "token", ""),etPhone.getText().toString().trim(),etYanzhengma.getText().toString().trim()))
                    .setDataListener(new HttpDataListener<String>() {
                        @Override
                        public void onNext(String data) {
                            Log.e("data",data);
                            String pass1 = etPass1.getText().toString().trim();
                            String pass2 = etPass2.getText().toString().trim();
                            String yanzhengma = etYanzhengma.getText().toString().trim();
                            String phone = etPhone.getText().toString().trim();
                            if (!pass1.equals(pass2)){
                                ToastUtil.showToast("两次密码输入不一致");
                                return ;
                            }else{
                                if (pass1.isEmpty()){
                                    ToastUtil.showToast("请输入密码");
                                    return ;
                                }else if(yanzhengma.isEmpty()){
                                    ToastUtil.showToast("请输入验证码");
                                    return ;
                                }else if (AppUtil.isMobile(etPhone.getText().toString().trim())){
                                    Intent intent=new Intent(mContext,GHDWanShanXinXiActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("phone",phone);
                                    bundle.putString("pass1",pass1);
                                    bundle.putString("pass2",pass2);
                                    bundle.putString("yanzhengma",yanzhengma);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }else{
                                    ToastUtil.showToast("手机格式不正确，请重新填写");
                                }
                            }
                        }
                    },true);


    }

    private void getCode() {
            HttpManager.getInstance()
                    .with(ZhuCeActivity.this)
                    .setObservable(
                            RetrofitManager
                                    .getService()
                                    . getcode(yemian,etPhone.getText().toString().trim()))
                    .setDataListener(new HttpDataListener<String>() {

                        @Override
                        public void onNext(String data) {
                            Log.e("getcode",data);
                            downTimer.start();
                            runningThree = false;
                            ToastUtil.showToast("已发送验证码");

                        }
                    });

    }
    private void login() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .login(etPhone.getText().toString().trim(), "", etYanzhengma.getText().toString(), "2","1", StringUtil.getMyUUID(mContext)))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {

                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        PreferenceUtils.putInt(MyApplication.mContext,"random_id",bean.getRandom_id());
                        PreferenceUtils.putString(MyApplication.mContext,"host_account_type",bean.getHost_account_type());
                        zzh = bean.getHost_account_type();
                        tiaozhuan(bean.getRole(),bean.getRandom_id());


                    }
                },true);
    }

    private void tiaozhuan(String juese,int random_id) {
        //登录成功后  跳转
        if ("5".equals(juese)) {
            Intent intent = new Intent(mContext, WuLiuActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if ("4".equals(juese)) {//业务员
            Intent intent = new Intent(mContext, YeWuYuanMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if ("3".equals(juese)) {//物流司机
            Intent intent = new Intent(mContext, SiJiActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if ("2".equals(juese)) {//供应端
            if(random_id==0){
                Intent intent = new Intent(mContext, GongYingDuanShouYeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

        } else if ("1".equals(juese)) {//餐厅端
            final Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(!zzh.equals("0")){
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .getZzhAllQuanxian(PreferenceUtils.getString(MyApplication.mContext,"token","")))
                        .setDataListener(new HttpDataListener<ZiZhangHuDetailsBean>() {
                            @Override
                            public void onNext(ZiZhangHuDetailsBean bean) {
                                PreferenceUtils.setQuanxianList(MyApplication.mContext,bean);
                                intent.putExtra("tosome",3);
                                startActivity(intent);
                                finish();
                            }
                        });

            } else {
                startActivity(intent);
                finish();
            }
        }
    }
}
