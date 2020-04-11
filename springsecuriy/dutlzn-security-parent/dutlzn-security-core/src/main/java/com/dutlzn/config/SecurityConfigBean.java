package com.dutlzn.config;

import com.dutlzn.authentication.mobile.SmsCodeSendoer;
import com.dutlzn.authentication.mobile.SmsSend;
import com.dutlzn.authentication.session.CustomInvalidSessionStrategy;
import com.dutlzn.authentication.session.CustomSessionInformationExpiredStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 主要为容器中添加bean实例
 */
@Configuration
public class  SecurityConfigBean {

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomSessionInformationExpiredStrategy();
    }


    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new CustomInvalidSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(SmsSend.class)
    public SmsSend smsSend(){
        return new SmsCodeSendoer();
    }
}
