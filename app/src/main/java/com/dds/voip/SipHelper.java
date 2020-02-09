package com.dds.voip;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

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
public class SipHelper {
    private static SipHelper mInstance;

    private SipHelper() {
    }

    public static SipHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SipHelper();
        }
        return mInstance;
    }

    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * 服务器地址，含端口
     */
    private String serverIP = "106.13.215.99:2088";
    /**
     * 外呼号
     */
    private String seatNumber = "6000";
    /**
     * 密码
     */
    private String passWord = "60001234";
    /**
     * 对方号码
     */
    private String otherNumber = "6066";

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    /**
     * 权限判断
     */
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.RECORD_AUDIO) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CONTACTS)
            ) {
                //已经禁止提示了
                Toast.makeText(getActivity(), "您已禁止该权限，需要重新开启。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_CONTACTS
                        },
                        1000);

            }

        }
    }

    /**
     * 连接服务器
     */
    public void startService() {
        VoipUtil.startService(getActivity());
    }

    /**
     * 登录
     */
    public void login() {
        VoipUtil.login(getServerIP(), getSeatNumber(), getPassWord());
    }

    /**
     * 断开服务器
     */
    public void close() {
        VoipUtil.stopService(getActivity());
    }

    /**
     * 拨打电话
     */
    public void call() {
        VoipUtil.outgoing(getActivity(), getOtherNumber(), false);
    }


}
