package sys.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sys.demo.model.SysRole;

import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();
}
