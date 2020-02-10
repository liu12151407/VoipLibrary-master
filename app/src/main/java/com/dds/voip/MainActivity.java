package com.dds.voip;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.dds.voip.callback.StateCallBack;
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
    private Button bt_test;
    private TextView tv_info;

    private StateCallBack stateCallBack;

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
        bt_test = findViewById(R.id.bt_test);
        tv_info = findViewById(R.id.tv_info);
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
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        stateCallBack = new StateCallBack() {
            /**
             * 来电话时
             */
            @Override
            public void incomingReceived() {
                tv_info.setText("来电");
                LogUtils.i("SipHelper", "来电话时");
            }

            /**
             * 电话接通时
             */
            @Override
            public void streamsRunning() {
                tv_info.setText("通话中...");
                LogUtils.i("SipHelper", "电话接通时");
            }

            /**
             * 电话挂断时
             */
            @Override
            public void callEnd() {
                tv_info.setText("电话挂断");
                LogUtils.i("SipHelper", "电话挂断时");
            }

            /**
             * 出错时
             */
            @Override
            public void callError() {
                tv_info.setText("服务器异常");
                LogUtils.i("SipHelper", "出错时");
            }
        };

//        countDown();
    }


    /**
     * 倒计时
     */
    private CountDownTimer timer;

    /**
     * 倒计时显示
     */
    private void countDown() {
        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String crTime = millisUntilFinished / 1000 + "";
                switch (crTime) {
                    case "3":
                        SipHelper.getInstance().startService();
                        break;
                    case "1":
                        SipHelper.getInstance().login();
                        break;
                    default:
                        break;
                }
                tv_info.setText("服务器连接中..." + crTime);
            }

            @Override
            public void onFinish() {
                tv_info.setText("服务器连接成功");
                VoipUtil.setStateCallBack(stateCallBack);
                SipHelper.getInstance().call();
            }
        }.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsCompat.REQUEST_SYSTEM_ALERT_WINDOW) {
            VoipUtil.openNarrow();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            SipHelper.getInstance().close();
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        } catch (Exception e) {
        }
    }

}
