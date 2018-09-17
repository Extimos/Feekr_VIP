package com.jiang.tvlauncher.servlet;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jiang.tvlauncher.activity.MainActivity;
import com.jiang.tvlauncher.dialog.Loading;
import com.jiang.tvlauncher.entity.Const;
import com.jiang.tvlauncher.entity.VIP_Entity;
import com.jiang.tvlauncher.utils.HttpUtil;
import com.jiang.tvlauncher.utils.LogUtil;
import com.jiang.tvlauncher.utils.ToolUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jiangyao
 * @date: 2018/5/14
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 获取会员账号
 */
public class GetVIP_Servlet extends AsyncTask<String, Integer, VIP_Entity> {
    private static final String TAG = "GetVIP_Servlet";

    Activity activity;

    public GetVIP_Servlet(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected VIP_Entity doInBackground(String... strings) {
        Map map = new HashMap();

        map.put("terminalId", ToolUtils.getMyUUID_mini()); //终端设备ID
        map.put("mac", ToolUtils.getMacAddress());       //设备mac地址

        String res = HttpUtil.doPost(Const.URL + "tencent/tencentVideoController/getVuidInfoByAgent.do", map);

        VIP_Entity entity;
        //空判断
        if (!TextUtils.isEmpty(res) && res.contains(",\"result\":\"\"")) {
            res = res.replaceAll(",\"result\":\"\"", "");
        }

        if (TextUtils.isEmpty(res)) {
            entity = new VIP_Entity();
            entity.setErrorcode(-1);
            entity.setErrormsg("连接服务器失败");
        } else {
            try {
                entity = new Gson().fromJson(res, VIP_Entity.class);
            } catch (Exception e) {
                entity = new VIP_Entity();
                entity.setErrorcode(-2);
                entity.setErrormsg("数据解析失败");
                LogUtil.e(TAG, e.getMessage());
            }
        }
        return entity;
    }

    @Override
    protected void onPostExecute(VIP_Entity entity) {
        super.onPostExecute(entity);
        Loading.dismiss();

        switch (entity.getErrorcode()) {
            case 1000:
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).CallBack_Vip(entity.getResult());
                }
                break;
            default:
                if (activity instanceof MainActivity) {
                    ((MainActivity) activity).CallBack_Error(entity);
                }
                break;

        }

    }
}
