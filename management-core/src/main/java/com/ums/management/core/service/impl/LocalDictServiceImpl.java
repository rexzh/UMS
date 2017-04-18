//generator==
package com.ums.management.core.service.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.management.core.model.LocalDict;
import com.ums.management.core.dao.LocalDictMapper;
import com.ums.management.core.service.ILocalDictService;

@Service
public class LocalDictServiceImpl implements ILocalDictService {
	@Autowired
	private LocalDictMapper _dao = null;

	@Override
	public LocalDict getLocalDictById(int id){
		return _dao.selectByPrimaryKey(id);
	}

	@Override
    public List<LocalDict> getLocalDicts(Integer typeId, int orgId){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("typeId", typeId);
		queryMap.put("orgId", orgId);

		return _dao.selectLocalDicts(queryMap);
	}

	@Override
	public List<LocalDict> getLocalDictByCodeAndOrgId(String code, int orgId){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("code", code);
		queryMap.put("orgId", orgId);

		return _dao.selectLocalDicts(queryMap);
	}

	@Override
    public void deleteById(int id){
		_dao.deleteByPrimaryKey(id);
	}

	@Override
    public void create(LocalDict localDict){
		_dao.insert(localDict);
	}

	@Override
    public void update(LocalDict localDict){
		_dao.updateByPrimaryKey(localDict);
	}
}