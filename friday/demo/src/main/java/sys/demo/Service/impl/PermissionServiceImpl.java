package sys.demo.Service.impl;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sys.demo.Service.PermissionService;
import sys.demo.base.result.Results;
import sys.demo.dao.PermissionDao;
import sys.demo.model.SysPermission;
import sys.demo.util.TreeUtils;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Results<JSONArray> listAllPermission() {
//        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listByRoleId(Integer roleId) {
        List<SysPermission> datas = permissionDao.listByRoleId(roleId);
        return Results.success(0, datas);
    }


    @Override
    public Results<SysPermission> getMenuAll() {
        return Results.success(0, permissionDao.findAll());
    }

    @Override
    public Results save(SysPermission sysPermission) {
        return (permissionDao.save(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return permissionDao.getSysPermissionById(id);
    }

    @Override
    public Results updateSysPermission(SysPermission sysPermission) {
        return (permissionDao.update(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(Integer id) {
        permissionDao.deleteById(id);
        permissionDao.deleteByParentId(id);
        return Results.success();
    }

}
