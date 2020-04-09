package com.dutlzn.authentication.code;

import com.dutlzn.authentication.CustomAuthenticationFailureHandler;
import com.dutlzn.controller.CustomLoginController;
import com.dutlzn.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("imageCodeValidateFilter")
public class ImageCodeValidateFilter extends OncePerRequestFilter {
    @Autowired
    SecurityProperties securityProperties;
    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //如果是post方式的登录请求，则检验输入的验证码是否正确
        if(securityProperties.getAuthentication().getLoginProcessingUrl()
        .equals(httpServletRequest.getRequestURI())
            && httpServletRequest.getMethod().equalsIgnoreCase("post")){
            try{
                //校验验证码合法性
                validate(httpServletRequest);
            } catch (AuthenticationException e){
                //失败处理器进行处理异常
                customAuthenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
        //放行通过
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(HttpServletRequest httpServletRequest) {
        //先获取session中的验证码
        String sessionCode = (String)httpServletRequest.getSession().getAttribute(CustomLoginController.SESSION_KEY);
        //获取用户输入的验证码
        String inputCode = httpServletRequest.getParameter("code");
        //判断是否正确
        if(StringUtils.isBlank(inputCode)){
            throw new ValidateCodeExceptiom("验证码不能为空");
        }

        if(!inputCode.equalsIgnoreCase(sessionCode)){
            throw new ValidateCodeExceptiom("验证码输入错误");
        }

    }
}
