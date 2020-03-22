package sys.demo.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.demo.Service.RoleUserService;
import sys.demo.base.result.Results;
import sys.demo.dao.RoleDao;
import sys.demo.dao.RoleUserDao;
import sys.demo.model.SysRole;
import sys.demo.model.SysRoleUser;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;


    @Override
    public Results getSysRoleUserByUserId(Integer userId) {
        SysRoleUser sysRoleUser = roleUserDao.getSysRoleUserByUserId(userId);
        if(sysRoleUser != null){
            return Results.success(sysRoleUser);
        }else{
            return Results.success();
        }
    }
}
