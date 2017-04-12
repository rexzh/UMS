//generator==
package com.ums.management.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.management.core.model.GlobalDict;
import com.ums.management.core.dao.GlobalDictMapper;
import com.ums.management.core.service.IGlobalDictService;

@Service
public class GlobalDictServiceImpl implements IGlobalDictService {
	@Autowired
	private GlobalDictMapper _dao = null;

	@Override
	public GlobalDict getGlobalDictById(int id){
		return _dao.selectByPrimaryKey(id);
	}

	@Override
    public List<GlobalDict> getGlobalDicts(){
		return _dao.selectGlobalDicts();
	}

	@Override
    public void deleteById(int id){
		_dao.deleteByPrimaryKey(id);
	}

	@Override
    public void create(GlobalDict globalDict){
		_dao.insert(globalDict);
	}

	@Override
    public void update(GlobalDict globalDict){
		_dao.updateByPrimaryKey(globalDict);
	}
}