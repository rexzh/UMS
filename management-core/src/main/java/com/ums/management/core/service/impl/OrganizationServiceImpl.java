package com.ums.management.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ums.management.core.dao.OrganizationMapper;
import com.ums.management.core.dao.UserOrgMapper;
import com.ums.management.core.model.Organization;
import com.ums.management.core.model.User;
import com.ums.management.core.model.UserOrg;
import com.ums.management.core.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private OrganizationMapper _dao = null;

    @Autowired
    private UserOrgMapper _uoDao = null;

    @Override
    public Organization getOrganizationById(int id) {
        return _dao.selectByPrimaryKey(id);
    }

    @Override
    public List<Organization> getOrganizations(String name, Boolean enabled, Integer start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);

        queryMap.put("start", start);
        queryMap.put("rows", rows);
        return _dao.selectOrganizations(queryMap);
    }

    @Override
    public int countOrganizations(String name, Boolean enabled) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);
        return _dao.countOrganizations(queryMap);
    }

    @Override
    public List<Organization> getOrganizationsByUserId(long userId, String name, Boolean enabled, Integer start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);

        queryMap.put("start", start);
        queryMap.put("rows", rows);
        return _dao.selectOrganizationsByUserId(queryMap);
    }

    @Override
    public int countOrganizationsByUserId(long userId, String name, Boolean enabled) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("userId", userId);
        queryMap.put("name", name);
        queryMap.put("enabled", enabled);
        return _dao.countOrganizationsByUserId(queryMap);
    }

    @Override
    public void deleteById(int id) {
        _dao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void create(Organization organization, Long userId) {
        _dao.insert(organization);
        if (userId != null) {
            UserOrg uo = new UserOrg();
            uo.setUserId(userId);
            uo.setOrgId(organization.getId());
            _uoDao.insert(uo);
        }
    }

    @Override
    public void update(Organization organization) {
        _dao.updateByPrimaryKey(organization);
    }
}