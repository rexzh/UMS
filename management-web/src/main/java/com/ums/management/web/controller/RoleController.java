package com.ums.management.web.controller;


import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rex on 2016/9/2.
 */
@RestController
public class RoleController {
    @Autowired
    private IRoleService _svc;


    @RequestMapping("/role.json")
    public ResponseVO getAllRoles(){
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("roles", this._svc.getRoles());
        return response;
    }


    @RequestMapping("/role.json/{id}")
    public ResponseVO getRoleById(@PathVariable("id") Integer roleId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        Role role = this._svc.getRoleById(roleId);
        List<RoleMenu> roleMenus = this._svc.getRoleMenuByRole(role);

        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role, roleVO);
        roleVO.setRoleMenus(roleMenus);

        response.addData("role", roleVO);
        return response;
    }

    @RequestMapping(value = "/role.json", method = RequestMethod.PUT)
    public ResponseVO updateRole(@RequestBody RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        this._svc.update(role, roleVO.getRoleMenus());
        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }

    @RequestMapping(value = "/role.json", method = RequestMethod.POST)
    public ResponseVO createRole(@RequestBody RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        this._svc.create(role, roleVO.getRoleMenus());
        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }


    @RequestMapping(value = "/role.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteRole(@PathVariable("id") Integer id) {
        this._svc.deleteById(id);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }
}
