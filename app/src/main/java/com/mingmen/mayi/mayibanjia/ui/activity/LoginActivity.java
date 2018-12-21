package com.mingmen.mayi.mayibanjia.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.ZhuCeChengGongBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.base.BaseActivity;
import com.mingmen.mayi.mayibanjia.utils.AppUtil;
import com.mingmen.mayi.mayibanjia.utils.PreferenceUtils;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private Context mContext;
    private String pass;
    private String phone;
    private boolean isLogin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mContext = LoginActivity.this;
        isLogin = PreferenceUtils.getBoolean(MyApplication.mContext, "isLogin", false);
        if (isLogin) {
            tiaozhuan(PreferenceUtils.getString(MyApplication.mContext, "juese", ""),
                    PreferenceUtils.getInt(MyApplication.mContext,"random_id",0));
        }

        if ("魅族".equals(AppUtil.getDeviceBrand())) {

        } else {

            List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
            permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
            permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.drawable.permission_ic_location));
            permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入SD卡", R.drawable.permission_ic_storage));
            permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取SD卡", R.drawable.permission_ic_storage));
            permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "网络定位", R.drawable.permission_ic_location));
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


    @OnClick({R.id.bt_login, R.id.tv_dongtaimimadenglu, R.id.tv_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                pass = etPass.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                if (AppUtil.isMobile(phone)) {
                    login();
                } else {
                    ToastUtil.showToast("手机格式不正确，请重新填写");
                }
                break;
            case R.id.tv_dongtaimimadenglu:
                Intent dongtailogin=new Intent(mContext, ZhuCeActivity.class);
                dongtailogin.putExtra("yemian","2");
                startActivity(dongtailogin);
                break;
            case R.id.tv_zhuce:
                Intent zhuce = new Intent(mContext, ZhuCeActivity.class);
                zhuce.putExtra("yemian","1");
                startActivity(zhuce);
                break;
        }
    }

    private void login() {
        HttpManager.getInstance()
                .with(mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .login(phone, pass, "", "1"))
                .setDataListener(new HttpDataListener<ZhuCeChengGongBean>() {
                    @Override
                    public void onNext(ZhuCeChengGongBean bean) {
                        Log.e("token", bean.getToken() + "===");
                        PreferenceUtils.putString(MyApplication.mContext,"phone",bean.getTelephone());
                        PreferenceUtils.putBoolean(MyApplication.mContext, "isLogin", true);
                        PreferenceUtils.putString(MyApplication.mContext, "token", bean.getToken());
                        PreferenceUtils.putString(MyApplication.mContext, "juese", bean.getRole());
                        PreferenceUtils.putInt(MyApplication.mContext,"random_id",bean.getRandom_id());
                        tiaozhuan(bean.getRole(),bean.getRandom_id());


                    }
                });
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
            Intent intent = new Intent(mContext, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
