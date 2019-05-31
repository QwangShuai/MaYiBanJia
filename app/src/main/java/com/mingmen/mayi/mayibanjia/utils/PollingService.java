package com.mingmen.mayi.mayibanjia.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.mingmen.mayi.mayibanjia.MainActivity;
import com.mingmen.mayi.mayibanjia.R;
import com.mingmen.mayi.mayibanjia.app.MyApplication;
import com.mingmen.mayi.mayibanjia.bean.QiangDanBean;
import com.mingmen.mayi.mayibanjia.http.listener.HttpDataListener;
import com.mingmen.mayi.mayibanjia.http.manager.HttpManager;
import com.mingmen.mayi.mayibanjia.http.manager.RetrofitManager;
import com.mingmen.mayi.mayibanjia.ui.activity.GongYingDuanShouYeActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.QiangDanActivity;
import com.mingmen.mayi.mayibanjia.ui.activity.qiangdan.GongYingDuanQiangDanActivity;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.mingmen.mayi.mayibanjia.app.MyApplication.mContext;

/**
 * Created by Administrator on 2018/8/30.
 */

public class PollingService extends Service {
    public static final String ACTION = "com.ssl.Services";
    private static final String ID = "PUSH_NOTIFY_ID";

    private static final String NAME = "PUSH_NOTIFY_NAME";
    private Timer timer;
    private MediaPlayer mMediaPlayer;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer=MediaPlayer.create(mContext, R.raw.tishiyin);
        timer = new Timer();
        if (PollingUtils.isOpen) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        qiangdan();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(task, 0, 10000);
        } else {
            timer.cancel();
        }


    }


//    class PollingThread extends Thread {
//        //运行状态，下一步骤有大用
//        public boolean isRunning = true;
//        @Override
//        public void run() {
//                while(isRunning){
//                    try {
//                        //休息10秒
//                        Thread.sleep(10000);
//                        qiangdan();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//            }
//        }
//    }

    private void qiangdan() {
        Log.e("yingyingying","正在轮询中。。。");
        HttpManager.getInstance()
                .with(MyApplication.mContext)
                .setObservable(
                        RetrofitManager
                                .getService()
                                .keqiangdan(PreferenceUtils.getString(MyApplication.mContext, "token", "")))
                .setDataListener(new HttpDataListener<List<QiangDanBean>>() {
                    @Override
                    public void onNext(List<QiangDanBean> data) {
                        int size = data == null ? 0 : data.size();
                        if (size == 0) {
                            return;
                        }
                        if(mMediaPlayer!=null&&!mMediaPlayer.isPlaying()){
                            mMediaPlayer.start();
                        }
                        showNotification(mContext, data.size());
                    }
                }, false);
    }

    @Override
    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        new PollingThread().start();
    }

    /**
     * 显示一个普通的通知
     *
     * @param context 上下文
     * @param size
     */
    public void showNotification(Context context, int size) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
            builder.setChannelId(ID);
        }
        Intent intent = new Intent(context, GongYingDuanQiangDanActivity.class);//将要跳转的界面
        builder.setAutoCancel(true);//点击后消失
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知栏消息标题的头像
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
        builder.setTicker("您有新的订单");
        builder.setContentTitle("您有" + size + "条新的订单");
        builder.setContentText("祝您抢单成功");
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        //利用PendingIntent来包装我们的intent对象,使其延迟跳转
        PendingIntent intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(intentPend);
        manager.notify((int) (Math.random() * 100), builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();
        stopService(new Intent(GongYingDuanShouYeActivity.instance,PollingService.class));
//        System.exit(0);
    }
}