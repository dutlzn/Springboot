package com.dutlzn.config;

import com.dutlzn.authentication.mobile.SmsCodeSendoer;
import com.dutlzn.authentication.mobile.SmsSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主要为容器中添加bean实例
 */
@Configuration
public class SecurityConfigBean {
    @Bean
    @ConditionalOnBean(SmsSend.class)
    public SmsSend smsSend(){
        return new SmsCodeSendoer();
    }
}
