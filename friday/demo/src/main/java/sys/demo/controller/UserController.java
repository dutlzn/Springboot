package sys.demo.controller;

import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sys.demo.Service.UserService;
import sys.demo.base.result.PageTableRequest;
import sys.demo.base.result.ResponseCode;
import sys.demo.base.result.Results;
import sys.demo.dto.UserDto;
import sys.demo.model.SysUser;
import sys.demo.util.MD5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @PostMapping("/add")
    @ResponseBody
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
        SysUser sysUser = null ;
//        手机唯一
        sysUser = userService.getUserByPhone(userDto.getTelephone());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(), ResponseCode.PHONE_REPEAT.getMessage());
        }
        //用户名 唯一
        sysUser = userService.getUserByName(userDto.getUsername());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(), ResponseCode.USERNAME_REPEAT.getMessage());
        }


        userDto.setStatus(1);
        userDto.setPassword(MD5.crypt(userDto.getPassword()));
        return userService.save(userDto, roleId);
    }

    String pattern = "yyyy-MM-dd";
    @InitBinder //表单到方法的数据绑定
    public void initBinder(WebDataBinder binder, WebRequest request){
        // 自定义转换器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern),true));
    }

    @GetMapping("/edit")
    public String addUser(Model model, SysUser sysUser) {
        model.addAttribute(userService.getUserById(sysUser.getId()));
        return "user/user-edit";
    }

}
