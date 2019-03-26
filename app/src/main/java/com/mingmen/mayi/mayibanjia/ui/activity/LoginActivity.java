package com.mingmen.mayi.mayibanjia.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.app.UMConfig;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.bean.ZiZhangHuDetailsBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppManager;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PollingService;
import com.mingmen.mayi.mayibanjia.utils.PollingUtils;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.StringUtil;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


/**
 * Created by Administrator on 2018/7/10/010.
 */


public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_dongtaimimadenglu)
    TextView tvDongtaimimadenglu;
    @BindView(R.id.tv_zhuce)
    TextView tvZhuce;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.bt_yzm)
    Button btYzm;
    @BindView(R.id.bt_youke)
    Button btYouke;
    @BindView(R.id.ll_yzm)
    LinearLayout llYzm;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.rl_phone_clear)
    RelativeLayout rlPhoneClear;
    @BindView(R.id.rl_pass_clear)
    RelativeLayout rlPassClear;


    private Context mContext;
    private String pass;
    private String phone;
    private String zzh = "0";
    private boolean isLogin;
    private long exitTime = 0;
    private boolean runningThree = true;
    public static LoginActivity instance;
    private String type = "1";
    public static int ISLOGIN = 1;
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            btYzm.setText((l / 1000) + "秒");
            btYzm.setBackground(getResources().getDrawable(R.drawable.fillet_solid_buliang_5));
        }

        @Override
        public void onFinish() {
            runningThree = true;
            btYzm.setText("重新发送");
            btYzm.setBackground(getResources().getDrawable(R.drawable.fillet_solid_zangqing_5));
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mContext = LoginActivity.this;
        instance = LoginActivity.this;
        ISLOGIN = 1;
        isLogin = PreferenceUtils.getBoolean(MyApplication.mContext, "isLogin", false);
        if(getIntent().getBooleanExtra("login",false)){
            type = "2";
            llYzm.setVisibility(View.VISIBLE);
        }
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPhone.getText().toString().trim().length() == 0) {
                    rlPhoneClear.setVisibility(View.GONE);
                } else {
                    rlPhoneClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etPass.getText().toString().trim().length() == 0) {
                    rlPassClear.setVisibility(View.GONE);
                } else {
                    rlPassClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (isLogin) {
            tiaozhuan(PreferenceUtils.getString(MyApplication.mContext, "juese", ""),
                    PreferenceUtils.getInt(MyApplication.mContext, "random_id", 0));
        }
        if (GongYingDuanShouYeActivity.instance != null) {
            PollingUtils.stopPollingService(GongYingDuanShouYeActivity.instance, PollingService.class, PollingService.ACTION);
            GongYingDuanShouYeActivity.instance.finish();
        }
        if ("魅族".equals(AppUtil.getDeviceBrand())) {

        } else {

            List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
            permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
            permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
            permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入SD卡", R.drawable.permission_ic_storage));
            permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取SD卡", R.drawable.permission_ic_storage));
            permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "网络定位", R.drawable.permission_ic_location));
            permissionItems.add(new PermissionItem(Manifest.permission.SEND_SMS, "短信权限", R.drawable.permission_ic_sms));
            permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone));
            //
            HiPermission.create(LoginActivity.this)
                    .permissions(permissionItems)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            ToastUtil.showToast("用户关闭权限申请");
                        }

                        @Override
                        public void onFinish() {
                            ToastUtil.showToast("所有权限申请完成");
                        }

                        @Override
                        public void onDeny(String permission, int position) {

                        }

                        @Override
                        public void onGuarantee(String permission, int position) {

                        }
                    });

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastUtil.showToast("权限已申请");
            } else {
                ToastUtil.showToast("权限已拒绝");
            }
        } else if (requestCode == 1) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, permissions[i]);
                    if (showRequestPermission) {
                        ToastUtil.showToast("权限未申请");
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @OnClick({R.id.bt_login, R.id.tv_dongtaimimadenglu, R.id.tv_zhuce,R.id.bt_yzm,
            R.id.bt_youke,R.id.ll_phone,R.id.rl_phone_clear,R.id.rl_pass_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if(ISLOGIN==1){
                    pass = etPass.getText().toString().trim();
                    phone = etPhone.getText().toString().trim();
                    if (AppUtil.isMobile(phone)) {
                        if(llYzm.getVisibility()==View.VISIBLE?true:false){
                            if(etYzm.getText().toString().length()==6){
                                type = "2";
                                login();
                            } else {
                                ToastUtil.showToastLong("请确认验证码是否规范输入");
                            }
                        } else {
                            login();
                        }

                    } else {
                        ToastUtil.showToast("手机格式不正确，请重新填写");
                    }
                } else {
                    llYzm.setVisibility(View.VISIBLE);
                    pass = etPass.getText().toString().trim();
                    phone = etPhone.getText().toString().trim();
                    if (AppUtil.isMobile(phone)) {
                        if(llYzm.getVisibility()==View.VISIBLE?true:false){
                            if(etYzm.getText().toString().length()==6){
                                type = "2";
                                login();
                            } else {
                                ToastUtil.showToastLong("请确认验证码是否规范输入");
                            }
                        } else {
                            login();
                        }

                    } else {
                        ToastUtil.showToast("手机格式不正确，请重新填写");
                    }
                }

                break;
            case R.id.tv_dongtaimimadenglu:
                Intent dongtailogin = new Intent(mContext, ZhuCeActivity.class);
                dongtailogin.putExtra("yemian", "2");
                dongtailogin.putExtra("phone", etPhone.getText().toString());
                startActivity(dongtailogin);
                break;
            case R.id.tv_zhuce:
                Intent zhuce = new Intent(mContext, ZhuCeActivity.class);
                zhuce.putExtra("yemian", "1");
                startActivity(zhuce);
                break;
            case R.id.bt_yzm:
                //获取验证码
                if (AppUtil.isMobile(etPhone.getText().toString().trim())){
                    getCode();
                }else{
                    ToastUtil.showToast("手机格式不正确，请重新填写");
                }
                break;
            case R.id.bt_youke:
                youkeLogin();
                break;
            case R.id.ll_phone:
                CallPhone(UMConfig.PHONE);
                break;
            case R.id.rl_phone_clear:
                etPhone.setText("");
                break;
            case R.id.rl_pass_clear:
                etPass.setText("");
                break;
        }
    }

    private void login() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .login(phone, pass, etYzm.getText().toString(), type, "1", StringUtil.getMyUUID(mContext)))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {
                        Log.e("token", bean.getToken() + "===");
                        PreferenceUtils.putString(MyApplication.mContext, "phone", bean.getTelephone());
                        PreferenceUtils.putString(MyApplication.mContext, "host_account_type", bean.getHost_account_type());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        if (StringUtil.isValid(bean.getName())) {
                            PreferenceUtils.putString(MyApplication.mContext, "name", bean.getName());
                        }
                        zzh = bean.getHost_account_type();
                        if(bean.getRole().equals("2")){
                            PreferenceUtils.putInt(MyApplication.mContext, "random_id", bean.getRandom_id());
                            tiaozhuan(bean.getRole(), bean.getRandom_id());
                        } else {
                            tiaozhuan(bean.getRole(), 0);
                        }

                        Log.e("onNext: ", bean.getRole() + "我的天啊" + zzh);


                    }
                    @Override
                    protected Object clone() throws CloneNotSupportedException {
                        llYzm.setVisibility(View.VISIBLE);
                        return super.clone();
                    }
                });
    }
    private void youkeLogin() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .youkeLogin())
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {
                        Log.e("token", bean.getToken() + "===");
                        PreferenceUtils.putString(MyApplication.mContext, "phone", bean.getTelephone());
                        PreferenceUtils.putString(MyApplication.mContext, "host_account_type", bean.getHost_account_type());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        if (StringUtil.isValid(bean.getName())) {
                            PreferenceUtils.putString(MyApplication.mContext, "name", bean.getName());
                        }
                        PreferenceUtils.putInt(MyApplication.mContext, "random_id", bean.getRandom_id());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "youke", true);
                        zzh = bean.getHost_account_type();
                        Log.e("onNext: ", bean.getRole() + "我的天啊" + zzh);
                        tiaozhuan(bean.getRole(), bean.getRandom_id());

                    }
                    @Override
                    protected Object clone() throws CloneNotSupportedException {
                        llYzm.setVisibility(View.VISIBLE);
                        return super.clone();
                    }
                });
    }
    private void tiaozhuan(String juese, int random_id) {
        //登录成功后  跳转
        if ("5".equals(juese)) {
            Intent intent = new Intent(mContext, WuLiuActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            AppManager.getAppManager().finishAllActivity();
        } else if ("4".equals(juese)) {//业务员
//            Intent intent = new Intent(mContext, YeWuYuanMainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent intent = new Intent(mContext, YeWuYuanActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            AppManager.getAppManager().finishAllActivity();
        } else if ("3".equals(juese)) {//物流司机
            Intent intent = new Intent(mContext, SiJiActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            AppManager.getAppManager().finishAllActivity();
        } else if ("2".equals(juese)) {//供应端
            if (random_id == 1) {
                Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                AppManager.getAppManager().finishAllActivity();
            } else {
                Intent intent = new Intent(mContext, GongYingDuanShouYeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                AppManager.getAppManager().finishAllActivity();
            }

        } else if ("1".equals(juese)) {//餐厅端
            final Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (!zzh.equals("0")) {
                HttpManager.getInstance()
                        .with(mContext)
                        .setObservable(
                                RetrofitManager
                                        .getService()
                                        .getZzhAllQuanxian(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                        .setDataListener(new HttpDataListener<ZiZhangHuDetailsBean>() {
                            @Override
                            public void onNext(ZiZhangHuDetailsBean bean) {
                                PreferenceUtils.setQuanxianList(MyApplication.mContext, bean);
                                intent.putExtra("tosome", 3);
                                startActivity(intent);
                                AppManager.getAppManager().finishAllActivity();
                            }
                        });

            } else {
                startActivity(intent);
                AppManager.getAppManager().finishAllActivity();
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(mContext);
//            System.exit(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private void getCode() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                . getcode("2",etPhone.getText().toString().trim()))
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setView(){
        llYzm.setVisibility(View.VISIBLE);
    }
    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
