package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.model.SysRole;

public interface RoleService {
    Results<SysRole> getAllRoles();
}
