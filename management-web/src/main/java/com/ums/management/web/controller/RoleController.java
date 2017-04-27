package com.ums.management.web.controller;


import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import com.ums.management.web.utility.RoleExtension;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class RoleController {
    @Autowired
    private IRoleService _svc = null;

    @RequestMapping("/role.json")
    public ResponseVO getAllRoles() {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("roles", this._svc.getAllRoles());
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
    public ResponseVO updateRole(HttpSession httpSession, @RequestBody RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);

        Role oldRole = _svc.getRoleById(role.getId());
        if (RoleExtension.isAdmin(oldRole) || RoleExtension.isPowerUser(oldRole)) {
            boolean b1 = !oldRole.getName().equals(role.getName());
            boolean b2 = oldRole.getEnabled() != role.getEnabled();
            if ((!oldRole.getName().equals(role.getName())) || (!oldRole.getEnabled().equals(role.getEnabled()))) {
                return ResponseVO.buildErrorResponse("Built-in role can't be changed");
            }
        }

        if (UserExtension.hasEnoughPower(httpSession, role)) {
            this._svc.update(role, roleVO.getRoleMenus());
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("No permission");
        }
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
        Role role = this._svc.getRoleById(id);
        if (RoleExtension.isAdmin(role) || RoleExtension.isPowerUser(role)) {
            return ResponseVO.buildErrorResponse("Built-in role can't be removed");
        }

        this._svc.deleteById(id);
        return ResponseVO.buildSuccessResponse();
    }
}
