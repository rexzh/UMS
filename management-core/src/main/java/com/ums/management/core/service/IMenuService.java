package com.ums.management.core.service;

import com.ums.management.core.model.Menu;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.Submenu;

import java.util.List;

public interface IMenuService {
	List<Menu> getAllMenusByRole(Role role);

	List<Submenu> getAllSubmenus();

	Submenu getSubmenuById(int id);

	List<Menu> getAllMenus();

	void createSubmenu(Submenu menu);

	void updateSubmenu(Submenu menu);

	void deleteSubmenuById(int id);
}
