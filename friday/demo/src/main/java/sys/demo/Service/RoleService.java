package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.dto.RoleDto;
import sys.demo.model.SysRole;


public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

    Results<SysRole> save(RoleDto roleDto);

    SysRole getRoleById(Integer id);

    Results update(RoleDto roleDto);

    Results delete(Integer id);

    Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer offset, Integer limit);
}
