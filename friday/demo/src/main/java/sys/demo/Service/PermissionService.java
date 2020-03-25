package sys.demo.Service;

import com.alibaba.fastjson.JSONArray;
import sys.demo.base.result.Results;
import sys.demo.model.SysPermission;

public interface PermissionService {

    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);

    Results<SysPermission> getMenuAll();

    Results<SysPermission> save(SysPermission permission);

    SysPermission getSysPermissionById(Integer id);

    Results updateSysPermission(SysPermission permission);

    Results delete(Integer id);

    /**
     * 删除id=pid不存在的节点
     */


}
