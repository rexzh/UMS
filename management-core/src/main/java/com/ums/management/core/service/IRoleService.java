package com.ums.management.core.service;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;

import java.util.List;

/**
 * Created by Rex on 2016/9/4.
 */
public interface IRoleService {
    Role getRoleById(int id);
    List<Role> getAllRoles();

    void deleteById(int id);
    void create(Role role, List<RoleMenu> roleMenus);
    void update(Role role, List<RoleMenu> roleMenus);

    List<RoleMenu> getRoleMenuByRole(Role role);
}
