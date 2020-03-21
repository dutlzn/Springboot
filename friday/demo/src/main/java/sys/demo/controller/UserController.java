package sys.demo.controller;

import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.demo.Service.UserService;
import sys.demo.base.result.Results;
import sys.demo.model.SysUser;

import java.util.ArrayList;

//import javax.xml.transform.Result;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController
{
    @Autowired
    private UserService userService;
    @GetMapping("/{username}")
    @ResponseBody //返回的是json格式的数据
    public SysUser user(@PathVariable String username) {
        log.info("UserController.user(): param ( username = " + username +" )");
        return userService.getUser(username);
    }

    @GetMapping("/list")
    @ResponseBody //返回的是json格式的数据
    public Results<SysUser> list() {
        ArrayList<SysUser> list = new ArrayList<SysUser>();
        list.add(userService.getUser("user"));
//        log.info("UserController.user(): param ( username = " + username +" )");
        return Results.success("success", 1000,list);
    }
}
