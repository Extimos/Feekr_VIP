package com.jiang.tvlauncher.servlet;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.jiang.tvlauncher.MyApp;
import com.jiang.tvlauncher.activity.MainActivity;
import com.jiang.tvlauncher.dialog.Loading;
import com.jiang.tvlauncher.entity.Const;
import com.jiang.tvlauncher.entity.Update_Entity;
import com.jiang.tvlauncher.utils.DownUtil;
import com.jiang.tvlauncher.utils.HttpUtil;
import com.jiang.tvlauncher.utils.ToolUtils;
import com.jiang.tvlauncher.utils.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/9/12
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 检测更新
 */
public class Update_Servlet extends AsyncTask<String, Integer, Update_Entity> {
    private static final String TAG = "Update_Servlet";

    Activity activity;

    public Update_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Update_Entity doInBackground(String... strings) {
        Map map = new HashMap();
        map.put("versionNum", Tools.getVersionName(MyApp.context));
        map.put("buildNum", String.valueOf(Tools.getVersionCode(MyApp.context)));
        //设备ID
        map.put("terminalId", ToolUtils.getMyUUID_mini());

        String res = HttpUtil.doPost(Const.URL + "cms/appVersionController/findNewHotelApp.do", map);

        Update_Entity entity;
        if (res != null) {
            try {
                entity = new Gson().fromJson(res, Update_Entity.class);
            } catch (Exception e) {
                entity = new Update_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
            }
        } else {
            entity = new Update_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        }
        return entity;
    }

    @Override
    protected void onPostExecute(Update_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (entity.getResult().getBuildNum() > Tools.getVersionCode(MyApp.context)) {
                    new DownUtil(activity).downLoad(entity.getResult().getDownloadUrl(), "Feekr" + entity.getResult().getVersionNum() + ".apk", true);
                } else {
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).CallBack_Update();
                    }
                }
                break;
            default:
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).CallBack_Update();
                }
                break;
        }

        return;
    }
}
