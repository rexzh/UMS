//generator==
package com.ums.management.core.service.impl;

import java.util.List;

import com.ums.management.core.dao.OrganizationMapper;
import com.ums.management.core.model.Organization;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
	@Autowired
	private OrganizationMapper _dao = null;

	@Override
	public Organization getOrganizationById(int id){
		return _dao.selectByPrimaryKey(id);
	}

	@Override
    public List<Organization> getOrganizations(){
		return _dao.selectOrganizations();
	}

	@Override
    public void deleteById(int id){
		_dao.deleteByPrimaryKey(id);
	}

	@Override
    public void create(Organization organization){
		_dao.insert(organization);
	}

	@Override
    public void update(Organization organization){
		_dao.updateByPrimaryKey(organization);
	}
}