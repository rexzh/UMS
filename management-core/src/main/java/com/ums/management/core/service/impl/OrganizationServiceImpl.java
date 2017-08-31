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
import com.ums.management.core.view.model.UserVO;
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
    public List<Organization> getOrganizations(UserVO editor, String name, Boolean enabled, Integer start, Integer rows) {
        Map<String, Object> queryMap = new HashMap<>();
        if (editor.getRole().isAdmin()) {
            queryMap.put("name", name);
            queryMap.put("enabled", enabled);

            queryMap.put("start", start);
            queryMap.put("rows", rows);
            return _dao.selectOrganizations(queryMap);
        } else {
            queryMap.put("userId", editor.getId());
            queryMap.put("name", name);
            queryMap.put("enabled", enabled);

            queryMap.put("start", start);
            queryMap.put("rows", rows);
            return _dao.selectOrganizationsByUserId(queryMap);
        }
    }

    @Override
    public int countOrganizations(UserVO editor, String name, Boolean enabled) {
        Map<String, Object> queryMap = new HashMap<>();
        if (editor.getRole().isAdmin()) {
            queryMap.put("name", name);
            queryMap.put("enabled", enabled);

            return _dao.countOrganizations(queryMap);
        } else {
            queryMap.put("userId", editor.getId());
            queryMap.put("name", name);
            queryMap.put("enabled", enabled);

            return _dao.countOrganizationsByUserId(queryMap);
        }
    }

    @Override
    public void deleteById(int id) {
        _dao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void create(UserVO editor, Organization organization) {
        _dao.insert(organization);
        if (!editor.getRole().isAdmin()) {
            UserOrg uo = new UserOrg();
            uo.setUserId(editor.getId());
            uo.setOrgId(organization.getId());
            _uoDao.insert(uo);
        }
    }

    @Override
    public void update(Organization organization) {
        _dao.updateByPrimaryKey(organization);
    }
}