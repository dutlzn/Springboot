package com.dutlzn.authentication.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsCodeSendoer implements  SmsSend {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean sendSms(String mobile, String msg) {
        String sendContent = String.format(
                "你好，验证码%s,请勿传给别人.",msg
        );
        logger.info("向手机号"+mobile+"发送的短信为:"+sendContent);
        return true;
    }
}
