package com.mingmen.mayi.mayibanjia.utils;

/**
 * Created by Administrator on 2018/8/30.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * 轮询工具类
 * @author lambert_lei
 *
 */
public class PollingUtils {
    public static boolean isOpen=false;
    //开启轮询服务
    public static void startPollingService(Context context, int seconds, Class<?> cls,String action) {
        isOpen=true;
        //获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        //包装需要执行Service的Intent
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        Log.e("--------------","startPollingService...");
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();

        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                seconds * 1000, pendingIntent);
        Log.e("result", "it will start weigthing 3...");
    }
    //停止轮询服务
    public static void stopPollingService(Context context, Class<?> cls,String action) {
        isOpen=false;
        Log.e("停止轮询服务", "停止轮询服务");
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);
        context.stopService(intent);
    }
}
