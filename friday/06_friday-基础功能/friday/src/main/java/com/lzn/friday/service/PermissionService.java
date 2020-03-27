package com.lzn.friday.service;

import com.alibaba.fastjson.JSONArray;
import com.lzn.friday.base.result.Results;
import com.lzn.friday.model.SysPermission;

public interface PermissionService {

	 Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);

    Results<SysPermission> getMenuAll();

    Results<SysPermission> save(SysPermission sysPermission);

    SysPermission getSysPermissionById(Integer id);

    Results  updateSysPermission(SysPermission sysPermission);

    Results delete(Integer id);

    Results getMenu(Long userId);
}
