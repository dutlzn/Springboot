package com.dutlzn.config;

import com.dutlzn.authentication.code.ImageCodeValidateFilter;
import com.dutlzn.authentication.mobile.MobileAuthenticationConﬁg;
import com.dutlzn.authentication.mobile.MobileAuthenticationFilter;
import com.dutlzn.authentication.mobile.MobileValidateFilter;
import com.dutlzn.authentication.session.CustomLogoutHandler;
import com.dutlzn.properties.SecurityProperties;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    ImageCodeValidateFilter imageCodeValidateFilter;
    @Autowired
    MobileValidateFilter mobileValidateFilter;
    @Autowired
    MobileAuthenticationConﬁg mobileAuthenticationConﬁg;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我
     */
    @Autowired
    DataSource dataSource;
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true) ;
        return jdbcTokenRepository;
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
    


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////       数据库存储的密码一定是加密之后的
//        String password = passwordEncoder().encode("1234");
//        logger.info("加密之后存储的密码：" + password);
//        auth.inMemoryAuthentication().withUser("admin")
//                .password(password).authorities("admin");
        auth.userDetailsService(customUserDetailsService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        http.addFilterBefore(mobileValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageCodeValidateFilter,
                UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage(securityProperties.getAuthentication().getLoginPage())
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl())
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter())
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getAuthentication().getLoginPage()
                ,"/code/image"
                ,"/mobile/page"
                ,"/code/mobile").permitAll()
                .anyRequest().authenticated()
                .and()
        .rememberMe()
        .tokenRepository(jdbcTokenRepository())
        .tokenValiditySeconds(60*60*24*7)
        .and()
        .sessionManagement()
        .invalidSessionStrategy(invalidSessionStrategy)
        .maximumSessions(1)
        .expiredSessionStrategy(sessionInformationExpiredStrategy)
//        .maxSessionsPreventsLogin(true)// 当一个用户达到最大session数，则不允许后面进行登录
        .sessionRegistry(sessionRegistry())
        .and().and()
        .logout().addLogoutHandler(customLogoutHandler)
        .logoutUrl("/user/logout")
        .logoutSuccessUrl("/mobile/page")
                .deleteCookies("JSESSIONID") ;


        http.csrf().disable();

        http.apply(mobileAuthenticationConﬁg);
    }



    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());

    }
}
