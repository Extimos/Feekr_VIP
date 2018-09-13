package com.jiang.tvlauncher.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jiang.tvlauncher.R;
import com.jiang.tvlauncher.entity.Const;
import com.jiang.tvlauncher.entity.Save_Key;
import com.jiang.tvlauncher.entity.VIP_Entity;
import com.jiang.tvlauncher.servlet.GetVIP_Servlet;
import com.jiang.tvlauncher.servlet.Update_Servlet;
import com.jiang.tvlauncher.utils.DownUtil;
import com.jiang.tvlauncher.utils.LogUtil;
import com.jiang.tvlauncher.utils.SaveUtils;
import com.jiang.tvlauncher.utils.ToolUtils;
import com.jiang.tvlauncher.utils.Tools;
import com.ktcp.video.thirdagent.JsonUtils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView textView,ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.home_message);
        ID = findViewById(R.id.home_id);

        ID.setText(ToolUtils.getMyUUID_mini());
        //检测更新
        new Update_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void CallBack_Error(String err) {
//      err+= "\n设备ID：" + ToolUtils.getMyUUID_mini() ;
        textView.setText( err);
    }

    public void CallBack_Update() {
        //获取账号
        new GetVIP_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * 获得会员账号返回
     *
     * @param bean
     */
    public void CallBack_Vip(VIP_Entity.ResultBean bean) {

        HashMap<String, Object> params = new HashMap<>();

        Const.ktcp_vuid = String.valueOf(bean.getVuid());
        Const.ktcp_vtoken = bean.getVtoken();

        params.put("vuid", bean.getVuid());
        params.put("vtoken", bean.getVtoken());
        params.put("accessToken", bean.getAccessToken());
        params.put("errTip", "");

        SaveUtils.setString(Save_Key.PARAMS, JsonUtils.addJsonValue(params));

        //启动应用
        LogUtil.e(TAG, "启动会员版");

        //检查是否有此应用
        if (Tools.isAppInstalled(Const.TvViedo)) {
            //启动应用
            Tools.StartApp(this, Const.TvViedo);
        } else {
            //安装应用
            String dowurl = bean.getDownloadUrl();
            new DownUtil(this).downLoad(dowurl, dowurl.substring(dowurl.indexOf("tv_video")), true);

        }
    }
}
