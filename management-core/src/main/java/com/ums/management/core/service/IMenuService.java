package com.ums.management.core.service;

import com.ums.management.core.model.Menu;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.Submenu;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;

import java.util.List;

public interface IMenuService {
	List<Menu> getAllMenusByRole(Role role);

	List<Submenu> getAllSubmenus();

	Submenu getSubmenuById(int id);

	List<Menu> getAllMenus();

	ServiceResult<Void> createSubmenu(UserVO editor, Submenu menu);
	ServiceResult<Void> updateSubmenu(UserVO editor, Submenu menu);
	ServiceResult<Void> deleteSubmenuById(UserVO editor, int id);

	Menu getMenuById(int id);

	ServiceResult<Void> deleteMenuById(UserVO editor, int id);
	ServiceResult<Void> createMenu(UserVO editor, Menu menu);
	ServiceResult<Void> updateMenu(UserVO editor, Menu menu);
}
