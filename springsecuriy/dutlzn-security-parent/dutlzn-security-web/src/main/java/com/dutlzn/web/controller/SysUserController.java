package com.dutlzn.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * html页面路径前缀
     */
    private static final String HTML_PREFIX = "system/user/";

    /**
     * 前往用户列表页面
     */
    @GetMapping(value = {"/", ""}) // /user/  /user
    public String user() {
        return HTML_PREFIX + "user-list";
    }
}
