package com.ums.management.core.service;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.view.model.RoleVO;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;

import java.util.List;

public interface IRoleService {
    RoleVO getRoleById(int id);
    List<Role> getAllRoles();

    ServiceResult<Void> deleteById(UserVO editor, int id);
    ServiceResult<Void> create(UserVO editor, RoleVO role);
    ServiceResult<Void> update(UserVO editor, RoleVO role);
}
