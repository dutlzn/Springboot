package com.lzn.friday.security;

import com.lzn.friday.dto.LoginUser;
import com.lzn.friday.security.authentication.MyAuthenctiationFailureHandler;
import com.lzn.friday.security.authentication.MyAuthenticationSuccessHandler;
import com.lzn.friday.security.authentication.MyLogoutSeccessHandler;
import com.lzn.friday.security.exception.RestAuthenticationAccessDeniedHandler;
import com.lzn.friday.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
@Configuration
//@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyLogoutSeccessHandler myLogoutSeccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("simm").password("$2a$10$DFIwAy//Ol3X6Q1e5CEue.FfUnJ5Fj709z9oY1pwCWzpca.SpYs72").roles("USER").and()
//                .withUser("simmm").password("$2a$10$DFIwAy//Ol3X6Q1e5CEue.FfUnJ5Fj709z9oY1pwCWzpca.SpYs72").roles("USER","ADMIN");
//
//
//        //        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //解决X-Frame-Options DENY问题
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers(
                        "/login.html",
//                        "/my/**",
                        "/treetable-lay/**",
                        "/xadmin/**",
                        "/ztree/**",
                        "/statics/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
                .and().logout()
                .permitAll().invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSeccessHandler);
        //异常处理
        http.exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler);
    //        super.configure(http);
    }


}
