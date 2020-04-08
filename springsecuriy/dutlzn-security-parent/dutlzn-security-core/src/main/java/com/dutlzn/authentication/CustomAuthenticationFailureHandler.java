package com.dutlzn.authentication;

import com.dutlzn.base.result.MyResult;
import com.dutlzn.properties.LoginResponseType;
import com.dutlzn.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
    @Autowired
    SecurityProperties securityProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //认证失败状态码401
        if(LoginResponseType.JSON.equals(securityProperties.getAuthentication().getLoginType())){
            MyResult result = MyResult.build(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(result.toJsonString());
        }else{
            //重写向回认证页面，注意加上?error
            super.setDefaultFailureUrl(
                    securityProperties.getAuthentication().getLoginPage()+"?error");
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }

    }
}
