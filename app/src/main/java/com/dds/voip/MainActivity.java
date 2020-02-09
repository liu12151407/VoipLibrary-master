package com.dds.voip;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.trustmobi.voip.R;

/**
 * ***********************************************
 * 包路径：com.dds.voip
 * 类描述：
 * 创建人：Liu Yinglong[PHONE：13281160095]
 * 创建时间：2020-02-09
 * 修改人：
 * 修改时间：2020-02-09
 * 修改备注：
 * ***********************************************
 */

public class MainActivity extends AppCompatActivity {

    private Button bt_server;
    private Button bt_call;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SipHelper.getInstance().setActivity(this);
        SipHelper.getInstance().checkPermission();
        VoipUtil.setNarrowCallBack(this);
        bt_server = findViewById(R.id.bt_server);
        bt_call = findViewById(R.id.bt_call);
        bt_login = findViewById(R.id.bt_login);
        bt_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SipHelper.getInstance().startService();
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SipHelper.getInstance().login();
            }
        });
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SipHelper.getInstance().call();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsCompat.REQUEST_SYSTEM_ALERT_WINDOW) {
            VoipUtil.openNarrow();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (VoipUtil.isIncall(this)) {
            ToastUtils.showShort("通话中...");
        } else {
            ToastUtils.showShort("通话结束...");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SipHelper.getInstance().close();
    }
}
