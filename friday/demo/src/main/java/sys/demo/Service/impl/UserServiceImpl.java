package sys.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sys.demo.Service.UserService;
import sys.demo.base.result.Results;
import sys.demo.dao.RoleUserDao;
import sys.demo.dao.UserDao;
import sys.demo.dto.UserDto;
import sys.demo.model.SysRoleUser;
import sys.demo.model.SysUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleUserDao roleUserDao;
    @Override
    public SysUser getUser(String username){
        return userDao.getUser(username);
    }

    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
        //count user-list
        return Results.success(userDao.countAllUsers().intValue(), userDao.getAllUsersByPage(offset, limit));
    }

    @Override
    public Results save(SysUser user, Integer roleId) {

        if(roleId != null){
            //user
            userDao.save(user);
            //roleUser
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(user.getId().intValue());
            roleUserDao.save(sysRoleUser);
            return Results.success();
        }
        return Results.failure();
    }

    @Override
    public SysUser getUserByPhone(String telephone) {
        return userDao.getUserByPhone(telephone);
    }

    @Override
    public SysUser getUserByName(String username) {
        return userDao.getUserByUsername(username);
    }

}
