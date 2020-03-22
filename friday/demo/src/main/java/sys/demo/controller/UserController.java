package sys.demo.controller;

import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.demo.Service.UserService;
import sys.demo.base.result.PageTableRequest;
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

    /**
     * 返回具体用户数据
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    @ResponseBody //返回的是json格式的数据
    public SysUser user(@PathVariable String username) {
        log.info("UserController.user(): param ( username = " + username +" )");
        return userService.getUser(username);
    }

    /**
     * 返回用户列表数据
     * @return
     */
    @GetMapping("/list")
    @ResponseBody //返回的是json格式的数据
    public Results<SysUser> getUsers(PageTableRequest request) {
        log.info("UserController.getUsers(): param ( request1 = " + request +" )");
        request.countOffset();
        return userService.getAllUsersByPage(request.getOffset(), request.getLimit());
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String addUser(Model model) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("lzn");
        model.addAttribute(sysUser);
        return "user/user-add";
    }

}
