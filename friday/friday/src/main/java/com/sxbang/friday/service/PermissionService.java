package com.sxbang.friday.service;

import com.alibaba.fastjson.JSONArray;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.model.SysPermission;

public interface PermissionService {

	 Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);
}
