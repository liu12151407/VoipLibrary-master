package com.dds.voip;

import android.app.Activity;
import android.content.Context;

import com.dds.voip.callback.NarrowCallbackDefault;
import com.dds.voip.callback.VoipCallBack;
import com.trustmobi.voip.BuildConfig;

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

public class VoipUtil {

    public static String serverUrl = "106.13.215.99:2088";

    // 开启Voip服务
    public static void startService(Context context) {
        //设置输出日志
        VoipHelper.getInstance().setDebug(BuildConfig.DEBUG);
        //开启Voip服务
        VoipHelper.getInstance().startVoip(context);

    }

    //登录服务器
    public static void login(String userId, String password) {
        VoipHelper.getInstance().register(userId, password, serverUrl);
    }

    // 拨打电话
    public static void outgoing(Context context, String callName, boolean isVideoEnable) {
        VoipHelper.getInstance().call(context, callName, isVideoEnable, 0);
    }

    //关闭Voip服务
    public static void stopService(Context context) {
        VoipHelper.getInstance().unRegister();
        VoipHelper.getInstance().stopVoip(context);

    }

    //设置开启悬浮窗的回调
    public static void setNarrowCallBack(Activity activity) {
        VoipHelper.getInstance().setNarrowCallback(new NarrowCallbackDefault(activity));

    }

    //設置頭像和昵稱的回調
    public static void setBussinessCallback(VoipCallBack callBack) {
        VoipHelper.getInstance().setVoipCallBack(callBack);
    }

    //开启权限之后开启悬浮窗
    public static void openNarrow() {
        VoipHelper.getInstance().createNarrow();
    }

    public static boolean isIncall(Context context) {
        return VoipHelper.getInstance().isInCall(context);
    }
}
