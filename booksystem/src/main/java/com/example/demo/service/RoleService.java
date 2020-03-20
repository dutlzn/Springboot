package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

	public Integer update(Role role);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Role> list(Map<String,Object> map, Integer page, Integer pageSize);
	
	public Long getTotal(Map<String,Object> map);
	

	
	
}
