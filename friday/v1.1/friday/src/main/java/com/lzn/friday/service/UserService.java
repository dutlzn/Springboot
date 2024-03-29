package com.lzn.friday.service;
import com.lzn.friday.base.result.Results;
import com.lzn.friday.dto.UserDto;
import com.lzn.friday.model.SysUser;

public interface UserService {

	SysUser getUser(String username);

	Results<SysUser> getAllUsersByPage(Integer startPosition, Integer limit);

	SysUser getUserByPhone(String phone);

	SysUser getUserByEmail(String email);

    Results save(SysUser user, Integer roleId);

    SysUser getUserById(Long id);

    Results updateUser(UserDto userDto, Integer roleId);

    int deleteUser(Long id);

    Results<SysUser> getUserByFuzzyUserNamePage(String username, Integer startPosition, Integer limit);

    Results<SysUser> changePassword(String username, String oldPassword, String newPassword);
}
