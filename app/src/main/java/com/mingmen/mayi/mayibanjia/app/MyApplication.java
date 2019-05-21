package com.mingmen.mayi.mayibanjia.app;

/**
 * Created by jqt on 2016/12/16.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.mingmen.mayi.mayibanjia.utils.CrashHandler;
import com.mingmen.mayi.mayibanjia.utils.ToastUtil;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyApplication extends MultiDexApplication {
    ExecutorService cachedThreadPool = null;

    public ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }


    private Handler handler = new Handler();

    public Handler getHandler() {
        return handler;
    }

    static MyApplication instance = null;

    public static MyApplication getInstance(){
        return instance;
    }
    public static Context mContext;
    public static  UploadManager uploadManager;
//    private List<Activity> oList;//用于存放所有启动的Activity的集合

//    public static IWXAPI mWxApi;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance = this;
        cachedThreadPool = Executors.newCachedThreadPool();
        //————http上传,指定zone的具体区域——
        //Zone.zone0:华东
        //Zone.zone1:华北
        //Zone.zone2:华南
        //Zone.zoneNa0:北美
        //———http上传，自动识别上传区域——
        //Zone.httpAutoZone
        //———https上传，自动识别上传区域——
        //Zone.httpsAutoZone
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .recorder(null)           // recorder分片上传时，已上传片记录器。默认null
                .recorder(null, null)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone1)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
        UMShareAPI.get(this);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5b5579fbb27b0a608200000d"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        {
            PlatformConfig.setWeixin(UMConfig.WECHAT_APPID, UMConfig.WECHAT_APPSECRET);
        }
        initUpdate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
        doInit();
    }

    private void doInit() {

    }

    private void initUpdate() {

        XUpdate.get()

                .debug(true)

                .isWifiOnly(true)     //默认设置只在wifi下检查版本更新

                .isGet(true)          //默认设置使用get请求检查版本

                .isAutoMode(false)    //默认设置非自动模式，可根据具体使用配置

                .param("versionCode", UpdateUtils.getVersionCode(this)) //设置默认公共请求参数

                .param("appKey", getPackageName())

                .setOnUpdateFailureListener(new OnUpdateFailureListener() { //设置版本更新出错的监听

                    @Override

                    public void onFailure(UpdateError error) {
                            ToastUtil.showToastLong(error.toString());
                    }

                })

                .setIUpdateHttpService(new OKHttpUpdateHttpService()) //这个必须设置！实现网络请求功能。

                .init(this);   //这个必须初始化



    }
    public static Context getContext() {
        return mContext;
    }
}
