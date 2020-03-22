package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.dto.UserDto;
import sys.demo.model.SysUser;

public interface UserService {
    SysUser getUser(String username);

    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

    Results<SysUser> save(SysUser userDto, Integer roleId);

    SysUser getUserByPhone(String telephone);

    SysUser getUserByName(String username);

    Object getUserById(Long id);

    Results<SysUser> updateUser(UserDto userDto, Integer roleId);

    int deleteUser(Long id);

    Results<SysUser> getUserByFuzzyUsername(String username, Integer offset, Integer limit);
}