//package com.example.demo.config;
//
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//@EnableTransactionManagement(proxyTargetClass = true) // 开启jpa事务管理
//@EnableJpaRepositories(basePackages = "com.example.demo.repository")
//@EntityScan(basePackages = "com.example.demo.entity")
//public class JPAconfig {
//    @Bean
//    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//}
