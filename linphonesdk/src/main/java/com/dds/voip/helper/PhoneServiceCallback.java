package com.dds.voip.helper;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;

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
public abstract class PhoneServiceCallback {
    /**
     * 注册状态
     *
     * @param registrationState
     */
    public void registrationState(LinphoneCore.RegistrationState registrationState) {
    }

    /**
     * 注册状态
     *
     * @param registrationState
     */
    public void unRegistrationState(LinphoneCore.RegistrationState registrationState) {
    }

    /**
     * 来电状态
     *
     * @param linphoneCall
     */
    public void incomingCall(LinphoneCall linphoneCall) {
    }

    /**
     * 电话接通
     */
    public void callConnected() {
    }

    /**
     * 电话被挂断
     */
    public void callReleased() {
    }

    /**
     * 电话接通
     */
    public void callStart() {
    }
}