package sys.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sys.demo.Service.UserService;
import sys.demo.base.result.Results;
import sys.demo.dao.UserDao;
import sys.demo.model.SysUser;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public SysUser getUser(String username){
        return userDao.getUser(username);
    }

    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
        //count user-list
        return Results.success(userDao.countAllUsers().intValue(), userDao.getAllUsersByPage(offset, limit));
    }
}
