package com.ums.management.core.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ums.management.core.dao.MenuMapper;
import com.ums.management.core.dao.RoleMenuMapper;
import com.ums.management.core.dao.SubmenuMapper;
import com.ums.management.core.model.Menu;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.model.Submenu;
import com.ums.management.core.service.IMenuService;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.core.view.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper _menuDao = null;
	@Autowired
	private SubmenuMapper _submenuDao = null;
	@Autowired
	private RoleMenuMapper _roleMenuDao = null;

	@Override
	public List<Menu> getAllMenusByRole(Role role) {
		if(role.getEnabled()) {
			List<Menu> menus = this.getMenus(role);
			this.removeEmptyMenus(menus);
			return menus;
		} else {
			return new ArrayList<>();
		}
	}
	
	private List<Menu> getMenus(Role role) {
		List<RoleMenu> roleMenus = _roleMenuDao.selectByRoleId(role.getId());
		List<Menu> menus = _menuDao.selectMenus();
		List<Submenu> submenus = _submenuDao.selectSubmenus();


		List<Submenu> filteredSubmenus = new ArrayList<>();
		for(Submenu s : submenus) {
			for(RoleMenu rm : roleMenus) {
				if(rm.getSubmenuId().equals(s.getId())) {
					filteredSubmenus.add(s);
				}
			}
		}

		for(Menu m : menus) {
			for(Submenu sub : filteredSubmenus) {
				if(sub.getParentId().equals(m.getId())) {
					m.addSubmenu(sub);
				}
			}
		}

		return menus;
	}

	private void removeEmptyMenus(List<Menu> menus) {
		Menu menu = null; 
		Iterator<Menu> itor = menus.iterator();
		while(itor.hasNext()) {
			menu = itor.next();
			if(menu.getSubmenus() == null || menu.getSubmenus().size() == 0) {
				itor.remove();
			}
		}
	}

	@Override
	public List<Submenu> getAllSubmenus(){
		return _submenuDao.selectSubmenus();
	}

	@Override
	public Submenu getSubmenuById(int id){
		return _submenuDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Menu> getAllMenus() {
		return _menuDao.selectMenus();
	}


	@Override
	public ServiceResult<Void> deleteSubmenuById(UserVO editor, int id) {
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;

		_submenuDao.deleteByPrimaryKey(id);
		return ServiceResult.SUCCESS;
	}

	@Override
	public ServiceResult<Void> updateSubmenu(UserVO editor, Submenu submenu) {
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;
		_submenuDao.updateByPrimaryKey(submenu);
		return ServiceResult.SUCCESS;
	}

	@Override
	public ServiceResult<Void> createSubmenu(UserVO editor, Submenu submenu) {
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;
		_submenuDao.insert(submenu);
		return ServiceResult.SUCCESS;
	}

	@Override
	public ServiceResult<Void> deleteMenuById(UserVO editor, int id){
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;
		_menuDao.deleteByPrimaryKey(id);
		return ServiceResult.SUCCESS;
	}

	@Override
	public ServiceResult<Void> createMenu(UserVO editor, Menu menu){
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;
		_menuDao.insert(menu);
		return ServiceResult.SUCCESS;
	}

	@Override
	public ServiceResult<Void> updateMenu(UserVO editor, Menu menu){
		if(!editor.getRole().isAdmin())
			return ServiceResult.NO_PERMISSION;
		_menuDao.updateByPrimaryKey(menu);
		return ServiceResult.SUCCESS;
	}

	@Override
	public Menu getMenuById(int id){
		return _menuDao.selectByPrimaryKey(id);
	}
}