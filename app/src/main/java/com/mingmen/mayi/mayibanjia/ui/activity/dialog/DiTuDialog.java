package com.mingmen.mayi.mayibanjia.ui.activity.dialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/10/19.
 */

public class DiTuDialog extends BaseFragmentDialog implements View.OnClickListener{
    @BindView(R.id.tv_dialog_ditu_gaode)
    TextView tvGaode;
    @BindView(R.id.tv_dialog_ditu_baidu)
    TextView tvBaidu;
    private Context context;
    private String mLatitude;
    private String mLongitude;
    private String mAreaName;
    public DiTuDialog(){

    }
    public DiTuDialog setData(Context context,String mLatitude,String mLongitude,String mAreaName) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAreaName = mAreaName;
        this.context = context;
        return this;
    }
    @OnClick({R.id.tv_dialog_ditu_gaode,R.id.tv_dialog_ditu_baidu,R.id.tv_dialog_phone_cancle})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dialog_ditu_gaode:
                tiaozhuanGaode();
                break;
            case R.id.tv_dialog_ditu_baidu:
                tiaozhuanBaidu();
                break;
            case R.id.tv_dialog_phone_cancle:
                dismiss();
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dialog_ditu;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initConfiguration(Configuration configuration){
        configuration.fullWidth()
                .setGravity(Gravity.BOTTOM);
    }
    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    private void tiaozhuanBaidu() {
        if (isAvilible(context, "com.baidu.BaiduMap")) {// 传入指定应用包名

            try {
                Intent intent = Intent.getIntent("intent://map/direction?destination=latlng:"
                        + mLatitude + ","
                        + mLongitude + "|name:" + mAreaName + // 终点
                        "&mode=driving&" + // 导航路线方式
                        "region=北京" + //
                        "&src=新疆和田#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                context.startActivity(intent); // 启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {// 未安装
            ToastUtil.showToast("请先安装百度地图");
            Uri uri = Uri
                    .parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void tiaozhuanGaode(){
        if (isAvilible(context, "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=食十利&poiname="+mAreaName+"&lat="
                        + mLatitude
                        + "&lon="
                        + mLongitude + "&dev=0");
                context.startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtil.showToast("请先安装高德地图");
            Uri uri = Uri
                    .parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }
    }
}
