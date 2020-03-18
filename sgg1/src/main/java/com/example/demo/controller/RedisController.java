package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class RedisController {
    @Autowired
    private UserRedis userRedis;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/testRedis")
    public void testRedis(){
        Department deparment = new Department();
        deparment.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreatedTime(new Date());
        user.setDepartment(deparment);

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        userRedis.delete(this.getClass().getName()+":userByname:"+user.getName());
        userRedis.add(this.getClass().getName()+":userByname:"+user.getName(), 10L, user);

        user = userRedis.get(this.getClass().getName() + ":userByname:user");
        log.info("====user====user name:("
                +user.getName()
                +"),department name:("
                +user.getDepartment()
                +"),role name:("
                +user.getRoles().get(0).getName()+")");

    }

    @GetMapping("/testRedisList")
    public void testRedisList() {

    }
}
