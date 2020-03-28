package com.lzn.friday.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class WelcomeController {
    @GetMapping(value = "/add")
    public String welcome() {
        return "welcome";
    }

}
