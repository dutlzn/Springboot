package com.dutlzn.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
//session 和 cookies
//https://baijiahao.baidu.com/s?id=1619095369231494766&wfr=spider&for=pc
@Controller
public class CustomLoginController {
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/login/page")
    public String toLogin() {
        return "login";
    }

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * 获取图形验证码
     */
    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1 获取验证码字符串
        String code =defaultKaptcha.createText();
        logger.info("生成的图形验证码是：" + code);
        //2字符串把它放到session中
        request.getSession().setAttribute(SESSION_KEY,code);
        //3获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        //4将验证码图片把他写进去
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
    }
}
