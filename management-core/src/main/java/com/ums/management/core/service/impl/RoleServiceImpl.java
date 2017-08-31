package com.ums.management.core.service.impl;

import com.ums.management.core.dao.RoleMapper;
import com.ums.management.core.dao.RoleMenuMapper;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.core.utility.RoleMatrix;
import com.ums.management.core.view.model.RoleVO;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;
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
    public ServiceResult<Void> deleteById(int id) {
        Role r = _roleDao.selectByPrimaryKey(id);
        if(r.isAdmin() || r.isPowerUser()) {
            return new ServiceResult<>(400, "Built-in role can't be removed");
        } else {
            _roleMenuDao.deleteByRoleId(id);
            _roleDao.deleteByPrimaryKey(id);
            return new ServiceResult<>(null);
        }
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
    public ServiceResult<Void> update(UserVO editor, RoleVO roleVO) {
        Role roleToEdit = roleVO.toRole();

        if(!RoleMatrix.hasEnoughPower(editor.getRole(), roleToEdit)) {
            return new ServiceResult<>(403, "No permission");
        }

        Role oldRole = _roleDao.selectByPrimaryKey(roleToEdit.getId());
        if (oldRole.isAdmin() || oldRole.isPowerUser()) {
            if ((!oldRole.getName().equals(roleToEdit.getName())) || (!oldRole.getEnabled() == (roleToEdit.getEnabled()))) {
                return new ServiceResult<>(403, "Built-in role can't be changed");
            }
        }

        Role role = CopyUtils.copyBean(roleVO, Role.class);
        _roleDao.updateByPrimaryKey(role);
        _roleMenuDao.deleteByRoleId(role.getId());
        for (RoleMenu rm : roleVO.getRoleMenus()){
            _roleMenuDao.insert(rm);
        }
        return new ServiceResult<>(null);
    }
}
