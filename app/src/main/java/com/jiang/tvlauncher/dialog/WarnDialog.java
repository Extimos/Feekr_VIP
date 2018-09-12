package com.jiang.tvlauncher.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jiang.tvlauncher.R;

/**
 * @author: jiangadmin
 * @date: 2017/8/29.
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 警告弹框
 */

public class WarnDialog extends Dialog {
    private static final String TAG = "WarnDialog";

    TextView message;

    public WarnDialog(@NonNull Context context) {
        super(context);
        show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_warning);

        message = findViewById(R.id.dialog_message);
    }

    public void setMessage(String s) {
        message.setText(s);
    }

    @Override
    public void onBackPressed() {
//        System.exit(0);
        super.onBackPressed();

    }

}
