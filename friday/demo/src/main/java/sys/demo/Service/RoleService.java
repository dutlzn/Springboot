package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.model.SysRole;

public interface RoleService {
    Results<SysRole> getAllRoles();

    Results getAllRolesByPage(Integer offset, Integer limit);

    Results getRoleByFuzzyRoleNamePage(String roleName, Integer offset, Integer limit);
}
