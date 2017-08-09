package com.ums.management.core.service.impl;

import com.ums.management.core.dao.RoleMapper;
import com.ums.management.core.dao.RoleMenuMapper;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.core.view.model.RoleVO;
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
    public RoleVO getRoleById(int id) {
        Role role = _roleDao.selectByPrimaryKey(id);
        List<RoleMenu> roleMenus = _roleMenuDao.selectByRoleId(role.getId());
        RoleVO roleVO = CopyUtils.copyBean(role, RoleVO.class);
        roleVO.setRoleMenus(roleMenus);
        return roleVO;
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
    public void create(RoleVO roleVO) {
        Role role = CopyUtils.copyBean(roleVO, Role.class);
        _roleDao.insert(role);

        for (RoleMenu rm : roleVO.getRoleMenus()){
            rm.setRoleId(role.getId());
            _roleMenuDao.insert(rm);
        }
    }

    @Override
    @Transactional
    public void update(RoleVO roleVO) {
        Role role = CopyUtils.copyBean(roleVO, Role.class);
        _roleDao.updateByPrimaryKey(role);
        _roleMenuDao.deleteByRoleId(role.getId());
        for (RoleMenu rm : roleVO.getRoleMenus()){
            _roleMenuDao.insert(rm);
        }
    }
}
