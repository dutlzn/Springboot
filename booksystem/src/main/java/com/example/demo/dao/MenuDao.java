package com.example.demo.dao;

import com.example.demo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuDao extends JpaRepository<Menu,Integer>, JpaSpecificationExecutor< Menu> {
	
	
	
}
