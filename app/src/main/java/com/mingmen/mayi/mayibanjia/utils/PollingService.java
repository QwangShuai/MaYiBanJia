package com.mingmen.mayi.mayibanjia.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.GongYingDuanShouYeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.QiangDanActivity;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.mingmen.mayi.mayibanjia.app.MyApplication.mContext;

/**
 * Created by Administrator on 2018/8/30.
 */

public class PollingService extends Service {
    public static final String ACTION = "com.lambertlei.Services";
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("result", "轮询服务被创建onCreate");
        Timer timer = new Timer();
        if (PollingUtils.isOpen){
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        Log.e("--------------","New message!...");
                        qiangdan();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(task,0,10000);
        }else{
            timer.cancel();
        }


    }

    private void qiangdan() {
        Log.e("PreferenceUtils.getString(MyApplicat)",PreferenceUtils.getString(MyApplication.mContext,"token",""));
         HttpManager.getInstance()
                 .with(mContext)
                        .setObservable(
                    RetrofitManager
                            .getService()
                            .keqiangdan(PreferenceUtils.getString(MyApplication.mContext,"token","")))
                    .setDataListener(new HttpDataListener<List<QiangDanBean>>() {
                @Override
                public void onNext(List<QiangDanBean> data) {
                    Log.e("data",data.size()+"---");
                    if (data.size()==0){
                        return;
                    }
                    showNotification(mContext,data.size());
                }
            },false);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("result", "onStart");
    }
    /**
     * 显示一个普通的通知
     *
     * @param context 上下文
     * @param size
     */
    public void showNotification(Context context, int size) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, QiangDanActivity.class);//将要跳转的界面
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("您有新的订单");
        builder.setContentTitle("您有"+size+"条新的订单");
        builder.setContentText("祝您抢单成功");
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        PendingIntent intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentPend);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify((int) (Math.random()*100), builder.build());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("result", "轮询服务销毁了");
    }
}