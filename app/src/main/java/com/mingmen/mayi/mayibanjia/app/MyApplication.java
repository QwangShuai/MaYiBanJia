package com.mingmen.mayi.mayibanjia.app;

/**
 * Created by jqt on 2016/12/16.
 */

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyApplication extends Application {
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

        doInit();
//        registerToWX();
    }

    private void doInit() {


//        KSConfig.init(this, "VsxTFFv8kZig+mxJF1dRbYFzLO0+kFxB", new KsInitListener(){
//            @Override
//            public void onSuccess() {
//            }
//
//            @Override
//            public void onError(int code, String message) {
//            }
//        });
    }

//    private void registerToWX() {
//        //第二个参数是指你应用在微信开放平台上的AppID
//        mWxApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
//        // 将该app注册到微信
//        mWxApi.registerApp(Constants.APP_ID);
//    }


}
