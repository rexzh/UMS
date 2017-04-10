package com.ums.management.core.service.impl;

import com.ums.management.core.dao.RoleMapper;
import com.ums.management.core.dao.RoleMenuMapper;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper _roleDao = null;

    @Autowired
    private RoleMenuMapper _roleMenuDao = null;

    @Override
    public Role getRoleById(int id) {
        return _roleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<RoleMenu> getRoleMenuByRole(Role role) {
        return _roleMenuDao.selectByRoleId(role.getId());
    }

    @Override
    public List<Role> getAllRoles() {
        return _roleDao.selectAllRoles();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        _roleMenuDao.deleteByRoleId(id);
        _roleDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void create(Role role, List<RoleMenu> roleMenus) {
        _roleDao.insert(role);
        for (RoleMenu rm : roleMenus){
            rm.setRoleId(role.getId());
            _roleMenuDao.insert(rm);
        }
    }

    @Override
    @Transactional
    public void update(Role role, List<RoleMenu> roleMenus) {
        _roleDao.updateByPrimaryKey(role);
        _roleMenuDao.deleteByRoleId(role.getId());
        for (RoleMenu rm : roleMenus){
            _roleMenuDao.insert(rm);
        }
    }
}
