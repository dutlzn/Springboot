package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class HelloController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @RequestMapping("/testMysql")
    public String home() {
        userRepository.deleteAll();;
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);

        User user = new User();
        user.setName("user");
        user.setCreatedTime(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        user.setRoles(roles);
        userRepository.save(user);
        return "hello";
    }


    @GetMapping("/testPage")
    public void findPage() {
        log.info("user"+4314);
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        for(User user:page.getContent()){
            log.info("====user====user name:("
                    +user.getName()
                    +"),department name:("
                    +user.getDepartment()
                    +"),role name:("
                    +user.getRoles().get(0).getName()+")");
        }
    }
}
