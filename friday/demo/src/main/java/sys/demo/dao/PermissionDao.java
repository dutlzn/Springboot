package sys.demo.dao;

import org.apache.ibatis.annotations.*;
import sys.demo.base.result.Results;
import sys.demo.model.SysPermission;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select * from sys_permission t")
    List<SysPermission> findAll();

    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<SysPermission> listByRoleId(Integer roleId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
    int save(SysPermission e);

    @Select("select * from sys_permission t where t.id = #{id}")
    SysPermission getSysPermissionById(Integer id);

    int update(SysPermission e);

    @Delete("delete from sys_permission where id = #{id}")
    int deleteById(Integer id);

    @Delete("delete from sys_permission where parentId = #{parentId}")
    int deleteByParentId(Integer parentId);

    @Select("SELECT DISTINCT t.parentId FROM sys_permission t")
    List<Integer> findAllPid();

    List<SysPermission> listByUserId(Long id);
}
