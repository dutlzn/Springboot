package sys.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import sys.demo.model.SysRoleUser;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId, roleId) values(#{userId}, #{roleId})")
    void save(SysRoleUser sysRoleUser);
}
