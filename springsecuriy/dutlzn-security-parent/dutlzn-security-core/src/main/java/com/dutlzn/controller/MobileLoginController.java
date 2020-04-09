package com.dutlzn.controller;

import com.dutlzn.authentication.mobile.SmsSend;
import com.dutlzn.base.result.MyResult;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 手机登录控制层
 */
@Controller
public class MobileLoginController {
    public static final String SESSION_KEY = "SESSION_KEY_MOBILE_CODE";
    /**
     * 前往手机验证码登陆页
     * @return
     */
    @RequestMapping("/mobile/page")
    public String toMobilePage(){
        return "login-mobile";
    }

    @Autowired
    SmsSend smsSend;

    @ResponseBody //响应json 字符串
    @RequestMapping("/code/mobile")
    public MyResult smsCode(HttpServletRequest request) {
        // 1生成手机验证码
        String code = RandomStringUtils.randomNumeric(4);
        // 2将验证码保存到session中
        request.getSession().setAttribute(SESSION_KEY,code);
        // 3发送验证码到用户手机上
        String mobile = request.getParameter("mobile");
        smsSend.sendSms(mobile, code);
        return MyResult.ok();
    }
}
