package com.lzn.friday.controller;

import com.lzn.friday.base.result.PageTableRequest;
import com.lzn.friday.base.result.ResponseCode;
import com.lzn.friday.base.result.Results;
import com.lzn.friday.dto.UserDto;
import com.lzn.friday.model.SysUser;
import com.lzn.friday.service.UserService;
import com.lzn.friday.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{username}")
    @ResponseBody
	public SysUser user(@PathVariable String username) {
	    log.info("UserController.user(): param ( username = " + username +" )");
		return userService.getUser(username);
	}

	@GetMapping("/list")
	@ResponseBody
	public Results<SysUser> getUsers(PageTableRequest request) {
		request.countOffset();
		return userService.getAllUsersByPage(request.getOffset(),request.getLimit());
	}

	@GetMapping(value = "/add")
	public String addUser(Model model) {
		model.addAttribute("sysUser",new SysUser());
		return "user/user-add";
	}

	@PostMapping(value = "/add")
	@ResponseBody
	public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
		SysUser sysUser = null;
		sysUser = userService.getUser(userDto.getUsername());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
		}
		sysUser = userService.getUserByPhone(userDto.getTelephone());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
		}
		sysUser = userService.getUserByEmail(userDto.getEmail());
		if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
			return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
		}

		userDto.setStatus(1);
		userDto.setPassword(MD5.crypt(userDto.getPassword()));
		return userService.save(userDto,roleId);
	}

	String pattern = "yyyy-MM-dd";

	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
	}

    @GetMapping(value = "/edit")
    public String editUser(Model model, SysUser user) {
        model.addAttribute("sysUser",userService.getUserById(user.getId()));
        return "user/user-edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public Results<SysUser> updateUser( UserDto userDto,Integer roleId) {
        SysUser sysUser = null;
        sysUser = userService.getUser(userDto.getUsername());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.USERNAME_REPEAT.getCode(),ResponseCode.USERNAME_REPEAT.getMessage());
        }
        sysUser = userService.getUserByPhone(userDto.getTelephone());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        sysUser = userService.getUserByEmail(userDto.getEmail());
        if(sysUser != null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.EMAIL_REPEAT.getCode(),ResponseCode.EMAIL_REPEAT.getMessage());
        }
        return userService.updateUser(userDto,roleId);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public Results<SysUser> deleteUser( UserDto userDto) {
        userService.deleteUser(userDto.getId());
        return Results.success();
    }

    @GetMapping("/findUserByFuzzyUserName")
	@ResponseBody
	public Results<SysUser> getUserByFuzzyUserName(PageTableRequest requests, String username) {
		requests.countOffset();
		return userService.getUserByFuzzyUserNamePage(username,requests.getOffset(),requests.getLimit());
	}


}
