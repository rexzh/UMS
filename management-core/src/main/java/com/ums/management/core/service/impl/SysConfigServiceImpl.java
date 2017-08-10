//generator==
package com.ums.management.core.service.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.management.core.model.SysConfig;
import com.ums.management.core.dao.SysConfigMapper;
import com.ums.management.core.service.ISysConfigService;

@Service
public class SysConfigServiceImpl implements ISysConfigService {
	@Autowired
	private SysConfigMapper _dao = null;

	@Override
	public SysConfig getSysConfigById(int id){
		return _dao.selectByPrimaryKey(id);
	}

	@Override
    public List<SysConfig> getSysConfigs(){
		return _dao.selectSysConfigs();
	}

	@Override
    public void deleteById(int id){
		_dao.deleteByPrimaryKey(id);
	}

	@Override
    public void create(SysConfig sysConfig){
		_dao.insert(sysConfig);
	}

	@Override
    public void update(SysConfig sysConfig){
		_dao.updateByPrimaryKey(sysConfig);
	}
}