package com.dutlzn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("mobileUserDetailsService")
public class MobileUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        logger.info("请求的手机号:"+mobile);
        //1 通过手机号查询用户信息
        //2如果有这个用户，则查询用户此权限
        //3封装用户信息
        return new User("admin","",true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
