package com.ums.management.core.service;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.view.model.RoleVO;
import com.ums.management.core.view.model.ServiceResult;

import java.util.List;

public interface IRoleService {
    RoleVO getRoleById(int id);
    List<Role> getAllRoles();

    ServiceResult<Integer> deleteById(int id);
    void create(RoleVO role);
    void update(RoleVO role);
}
