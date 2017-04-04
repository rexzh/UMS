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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper _menuDao;
	@Autowired
	private SubmenuMapper _submenuDao;
	@Autowired
	private RoleMenuMapper _roleMenuDao;

	@Override
	public List<Menu> getAllMenusByRole(Role role) {
		List<Menu> menus = this.getMenus(role);
		this.removeEmptyMenus(menus);
		return menus;
	}
	
	private List<Menu> getMenus(Role role) {
		//List<RoleMenu> roleMenus = _roleMenuDao.selectByRoleId(role.getId());
		List<Menu> menus = _menuDao.selectMenus();
		List<Submenu> submenus = _submenuDao.selectSubmenus();

		/*
		List<Submenu> filteredSubmenus = new ArrayList<>();
		for(Submenu s : submenus) {
			for(RoleMenu rm : roleMenus) {
				if(rm.getSubmenuId() == s.getId()) {
					filteredSubmenus.add(s);
				}
			}
		}
		*/

		for(Menu m : menus) {
			for(Submenu sub : submenus/*filtered*/) {
				if(sub.getParentId() == m.getId()) {
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
	public void deleteSubmenuById(int id) {
		_submenuDao.deleteByPrimaryKey(id);
	}

	@Override
	public void updateSubmenu(Submenu submenu) {
		_submenuDao.updateByPrimaryKey(submenu);
	}

	@Override
	public void createSubmenu(Submenu submenu) {
		_submenuDao.insert(submenu);
	}
}