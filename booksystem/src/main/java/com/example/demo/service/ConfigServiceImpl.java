package com.example.demo.service;

import com.example.demo.dao.ConfigDao;
import com.example.demo.entity.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource
	private ConfigDao configDao;
	
	@Override
	public Config findById(Integer id) {
		return configDao.findId(id);
	}

}
