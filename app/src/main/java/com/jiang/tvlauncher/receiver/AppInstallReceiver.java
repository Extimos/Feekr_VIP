package com.jiang.tvlauncher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jiang.tvlauncher.utils.LogUtil;

/**
 * @author: jiangadmin
 * @date: 2017/7/3.
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 应用安装/卸载广播监听
 */

public class AppInstallReceiver extends BroadcastReceiver {
    private static final String TAG = "AppInstallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //安装
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();

            LogUtil.e(TAG, "安装成功");
//            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
//            if (launchIntent != null) {
//                context.startActivity(launchIntent);
//            }
        }
        //卸载
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();


        }
        //替换
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {

            LogUtil.e(TAG, "替换成功");
        }
    }
}