package com.dutlzn.authentication.mobile;

public interface SmsSend {
    /**
     * 发送短信
     * @param mobile 手机号
     * @param msg 发送消息
     * @return true表示发送成功
     */
    boolean sendSms(String mobile,String msg);
}
