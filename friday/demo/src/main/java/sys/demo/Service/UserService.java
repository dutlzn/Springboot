package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.dto.UserDto;
import sys.demo.model.SysUser;

public interface UserService {
    SysUser getUser(String username);

    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

    Results<SysUser> save(SysUser userDto, Integer roleId);
}
