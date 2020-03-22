package sys.demo.Service.impl;

import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.demo.Service.RoleService;
import sys.demo.base.result.Results;
import sys.demo.dao.RoleDao;
import sys.demo.model.SysRole;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Results<SysRole> getAllRoles() {
        // 50是数量，因为不可能多于50就写死了
        return Results.success(50, roleDao.getAllRoles());
    }
}