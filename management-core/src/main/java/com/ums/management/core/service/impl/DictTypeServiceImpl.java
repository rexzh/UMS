//generator==
package com.ums.management.core.service.impl;

import java.util.List;

import com.ums.management.core.dao.DictTypeMapper;
import com.ums.management.core.model.DictType;
import com.ums.management.core.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictTypeServiceImpl implements IDictTypeService {
	@Autowired
	private DictTypeMapper _dao = null;

	@Override
	public DictType getDictTypeById(int id){
		return _dao.selectByPrimaryKey(id);
	}

	@Override
    public List<DictType> getDictTypes(){
		return _dao.selectDictTypes();
	}

	@Override
    public void deleteById(int id){
		_dao.deleteByPrimaryKey(id);
	}

	@Override
    public void create(DictType dictType){
		_dao.insert(dictType);
	}

	@Override
    public void update(DictType dictType){
		_dao.updateByPrimaryKey(dictType);
	}
}