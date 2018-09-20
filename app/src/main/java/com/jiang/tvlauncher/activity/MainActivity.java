package com.jiang.tvlauncher.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jiang.tvlauncher.R;
import com.jiang.tvlauncher.entity.Const;
import com.jiang.tvlauncher.entity.VIP_Entity;
import com.jiang.tvlauncher.servlet.GetVIP_Servlet;
import com.jiang.tvlauncher.servlet.Update_Servlet;
import com.jiang.tvlauncher.utils.DownUtil;
import com.jiang.tvlauncher.utils.LogUtil;
import com.jiang.tvlauncher.utils.ToolUtils;
import com.jiang.tvlauncher.utils.Tools;
import com.ktcp.video.thirdagent.JsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

/**
 * @author: jiangadmin
 * @date: 2017/6/12.
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 主页
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ConstraintLayout constraintLayout;
    TextView textView, ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.mian_view);
        textView = findViewById(R.id.home_message);
        ID = findViewById(R.id.home_id);

        ID.setText("ID:" + ToolUtils.getMyUUID_mini() + "\nMAC:" + ToolUtils.getMacAddress());

        //检测更新
        new Update_Servlet(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onMessage(String ss) {
        finish();
    }

    public void CallBack_Error(VIP_Entity entity) {


//        constraintLayout.setBackgroundResource(R.drawable.bg1);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("抱歉");
        builder.setMessage(entity.getErrormsg());

        if (Tools.isAppInstalled(Const.Viedo)) {
            builder.setPositiveButton("确定", (dialogInterface, i) -> {


                //启动应用
                Tools.StartApp(MainActivity.this, Const.Viedo);

                //关闭APP
                System.exit(0);

            });
        } else if (entity.getResult() != null && entity.getResult().getDownloadUrlBak() != null) {
            builder.setPositiveButton("确定", (dialogInterface, i) -> {

                //下载安装应用

                String dowurl = entity.getResult().getDownloadUrlBak();
                new DownUtil(MainActivity.this).downLoad(dowurl, dowurl.substring(dowurl.lastIndexOf("/") + 1), true);


            });
        }


        builder.setNegativeButton("取消", (dialogInterface, i) -> {
                    //退出应用
                    System.exit(0);
                }
        );

        builder.show();


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

        Const.PARAMS = JsonUtils.addJsonValue(params);
//        SaveUtils.setString(Save_Key.PARAMS, JsonUtils.addJsonValue(params));

        //启动应用
        LogUtil.e(TAG, "启动会员版");

        //检查是否有此应用
        if (Tools.isAppInstalled(Const.TvViedo)) {
            //启动应用
            Tools.StartApp(this, Const.TvViedo);
        } else {
            //安装应用
            String dowurl = bean.getDownloadUrl();
            new DownUtil(this).downLoad(dowurl, dowurl.substring(dowurl.lastIndexOf("/") + 1), true);

        }
    }
}
