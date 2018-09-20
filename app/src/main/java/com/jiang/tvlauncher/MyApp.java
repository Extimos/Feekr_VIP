package com.jiang.tvlauncher;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;

/**
 * @author: jiangadmin
 * @date: 2018/9/5
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */

public class MyApp extends Application {
    private static final String TAG = "MyApp";

    public static Context context;

    public static boolean LogShow = true;
    public static Activity activity;

    public static String getSerialNum() {
        return null;
    }

    private static HashMap<String,Object> appCache = new HashMap<String,Object>();

    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        CrashReport.initCrashReport(getApplicationContext(), "78ed5dbc8b", false);

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo)
                .setTicker("feekr正在运行中").setContentInfo("feekr正在运行中")
                .setContentTitle("feekr正在运行中").setContentText("feekr正在运行中")
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();
        manager.notify(1, notification);

    }

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    public static void addCache(String key, Object value){
        appCache.put(key, value);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public static Object getCache(String key){
        return appCache.get(key);
    }

    /**
     * 移除缓存
     * @param key
     * @return
     */
    public static Object removeCache(String key){
        return appCache.remove(key);
    }

    public static void clearCache(){
        appCache.clear();
        return;
    }
}
