package com.dutlzn.authentication.code;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码相关异常类
 */
public class ValidateCodeExceptiom extends AuthenticationException {
    public ValidateCodeExceptiom(String msg,Throwable t){
        super(msg,t);
    }

    public ValidateCodeExceptiom(String msg){
        super(msg);
    }
}
