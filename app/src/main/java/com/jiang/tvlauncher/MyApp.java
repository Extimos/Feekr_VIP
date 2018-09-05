package com.jiang.tvlauncher;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

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

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
