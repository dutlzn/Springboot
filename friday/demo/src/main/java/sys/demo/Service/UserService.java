package sys.demo.Service;

import sys.demo.base.result.Results;
import sys.demo.model.SysUser;

public interface UserService {
    SysUser getUser(String username);

    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);
}
