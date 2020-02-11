package com.dds.voip.callback;

/**
 * ***********************************************
 * 包路径：com.dds.voip.callback
 * 类描述：
 * 创建人：Liu Yinglong[PHONE：13281160095]
 * 创建时间：2020-02-10
 * 修改人：
 * 修改时间：2020-02-10
 * 修改备注：
 * ***********************************************
 */
public interface StateCallBack {
    /**
     * 是否登录成功
     */
    void login(boolean isLogin);

    /**
     * 来电话时
     */
    void incomingReceived();

    /**
     * 电话接通时
     */
    void streamsRunning();

    /**
     * 电话挂断时
     */
    void callEnd();

    /**
     * 出错时
     */
    void callError();
}
