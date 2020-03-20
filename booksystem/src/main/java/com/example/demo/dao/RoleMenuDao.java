package com.example.demo.dao;

import com.example.demo.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleMenuDao  extends JpaRepository<RoleMenu,Integer>, JpaSpecificationExecutor< RoleMenu> {

}
