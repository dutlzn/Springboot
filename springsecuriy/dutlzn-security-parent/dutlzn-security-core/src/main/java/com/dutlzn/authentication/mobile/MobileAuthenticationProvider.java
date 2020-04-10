package com.dutlzn.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MobileAuthenticationProvider implements AuthenticationProvider {
    UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken mobileAuthenticationToken
                =(MobileAuthenticationToken)authentication;
        //获取用户输入的手机号
        String mobile = (String)mobileAuthenticationToken.getPrincipal();
        //查询数据库
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(mobile);
        if(userDetails == null){
            throw new AuthenticationServiceException("该手机未注册");
        }

        MobileAuthenticationToken authenticationToken =
                new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public void setUserDetailsService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
}
