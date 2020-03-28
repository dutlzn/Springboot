package com.lzn.friday;

import com.lzn.friday.dao.UserDao;
import com.lzn.friday.model.SysUser;
import com.lzn.friday.service.UserService;
import com.lzn.friday.util.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FridayApplicationTests {

    @Resource
    private UserDao userDao;

    @Test
    public void contextLoads() {
        userDao.changePassword(new Long(1), new BCryptPasswordEncoder().encode("65874092"));
        userDao.changePassword(new Long(2), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(3), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(18), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(26), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(27), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(28), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(29), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(30), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(32), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(41), new BCryptPasswordEncoder().encode("123456"));
        userDao.changePassword(new Long(43), new BCryptPasswordEncoder().encode("123456"));
//        userDao.changePassword(new Long(1), new MD5.crypt("ADMIN"));
//        SysUser sysUser = userDao.getUser("admin");
//        System.out.println(sysUser);
//        sysUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
//        System.out.println(sysUser);
    }

}
