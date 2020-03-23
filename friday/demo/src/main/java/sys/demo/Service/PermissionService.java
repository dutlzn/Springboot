package sys.demo.Service;

import com.alibaba.fastjson.JSONArray;
import sys.demo.base.result.Results;
import sys.demo.model.SysPermission;

public interface PermissionService {

    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);
}
