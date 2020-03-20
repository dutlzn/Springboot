package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.util.BrowserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    private ServletContext servletContext;//全局唯一，而且工程内部的所有servlet都共享这个对象。所以叫全局应用程序共享对象。

    /**
     * 请求首页
     */
    @RequestMapping("/")
    public String index_1(HttpServletResponse res, HttpServletRequest req) throws Exception{
        return "redirect:/login";
    }
    /**
     *   #请求首页  /index
     */
    @RequestMapping("/index")
    public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
        return "redirect:/login";
    }
    /**
     *    /login
     *    #后台 用户电脑登陆
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletResponse  res, HttpServletRequest req) throws Exception {
        ModelAndView mav = new ModelAndView();
        String UserAgent = req.getHeader("User-Agent");
        System.err.println(UserAgent);
        //判断AppleWebKit 和  Firefox
        if(BrowserUtil.checkUserAgent(UserAgent)){
            mav.setViewName("/pc/login/login");
        }else{
            mav.setViewName("/common/s_mode");
        }
        return mav;
    }



    /**
     * # 后台主页
     */
    @RequestMapping("/admin/main")
    public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req) throws Exception {
        ModelAndView mav = new ModelAndView();
        String UserAgent = req.getHeader("User-Agent");
        //判断AppleWebKit 和  Firefox
        if(BrowserUtil.checkUserAgent(UserAgent)){
            mav.setViewName("/admin/main");
        }else{
            mav.setViewName("/common/s_mode");
        }
        return mav;
    }


}
