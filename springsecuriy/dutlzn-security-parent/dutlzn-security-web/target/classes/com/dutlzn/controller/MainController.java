package com.dutlzn.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    @RequestMapping({"/index","/",""})
    public String index(Map<String, Object> map){
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if(principal != null && principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            map.put("username", userDetails.getUsername());
        }
        return "index";
    }
}
