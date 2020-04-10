package com.dutlzn.authentication.mobile;

import com.dutlzn.authentication.CustomAuthenticationFailureHandler;
import com.dutlzn.authentication.exception.ValidateCodeExceptiom;
import com.dutlzn.controller.MobileLoginController;
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

/**
 * 校验用户输入的手机
 */
@Component
public class MobileValidateFilter extends OncePerRequestFilter {
    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/mobile/form".equals(request.getRequestURI())
        && "post".equalsIgnoreCase(request.getMethod())){
            try{
                validate(request);
            } catch(AuthenticationException e){
                customAuthenticationFailureHandler.onAuthenticationFailure(
                        request,
                        response,
                        e
                );
                return ;
            }
        }

        filterChain.doFilter(request,response);
    }

    private void validate(HttpServletRequest request) {
        //先获取session中的验证码
        String sessionCode = (String)request.getSession().getAttribute(MobileLoginController.SESSION_KEY);
        //获取用户输入的验证码
        String inputCode = request.getParameter("code");
        //判断是否正确
        if(StringUtils.isBlank(inputCode)){
            throw new ValidateCodeExceptiom("短信验证码不能为空");
        }
        if(!inputCode.equalsIgnoreCase(sessionCode)){
            throw new ValidateCodeExceptiom("短信验证码输入错误");
        }
    }
}
