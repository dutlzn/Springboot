package com.dutlzn.security;

import com.dutlzn.authentication.mobile.SmsSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class MobileSmsCodeSender implements SmsSend {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean sendSms(String mobile, String msg) {
        logger.info("web应用新的短信验证码接口--向手机号"
        +mobile+"发送了验证码为:"+msg);
        return false;
    }
}
