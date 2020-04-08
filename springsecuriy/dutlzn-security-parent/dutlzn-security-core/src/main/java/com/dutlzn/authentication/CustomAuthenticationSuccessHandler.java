package com.dutlzn.authentication;

import com.dutlzn.base.result.MyResult;
import com.dutlzn.properties.LoginResponseType;
import com.dutlzn.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * 决定响应json还是跳转页面，或者认证成功后进行其他处理
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//
//    }

    @Autowired
    SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        认证成功之后，相应json数据给前端
        if(LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())){
            MyResult result = MyResult.ok("认证成功");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(result.toJsonString());
        }else{
            //重定向到上次请求的地址上，引发跳转到认证页面的地址
//           logger.info("authentication: " + JSON.toJSONString(authentication));
             super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }
}
