package com.ums.management.web.controller;


import com.ums.management.core.model.Role;
import com.ums.management.core.model.RoleMenu;
import com.ums.management.core.service.IRoleService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.web.utility.RoleExtension;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.view.model.RoleVO;
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
        RoleVO role = this._svc.getRoleById(roleId);

        response.addData("role", role);
        return response;
    }

    @RequestMapping(value = "/role.json", method = RequestMethod.PUT)
    public ResponseVO updateRole(HttpSession httpSession, @RequestBody RoleVO roleVO) {
        Role role = CopyUtils.copyBean(roleVO, Role.class);

        RoleVO oldRole = _svc.getRoleById(role.getId());
        if (RoleExtension.isAdmin(oldRole) || RoleExtension.isPowerUser(oldRole)) {
            boolean b1 = !oldRole.getName().equals(role.getName());
            boolean b2 = oldRole.getEnabled() != role.getEnabled();
            if ((!oldRole.getName().equals(role.getName())) || (!oldRole.getEnabled() == (role.getEnabled()))) {
                return ResponseVO.buildErrorResponse("Built-in role can't be changed");
            }
        }

        if (UserExtension.hasEnoughPower(httpSession, role)) {
            this._svc.update(roleVO);
            return ResponseVO.buildSuccessResponse();
        } else {
            return ResponseVO.buildErrorResponse("No permission");
        }
    }

    @RequestMapping(value = "/role.json", method = RequestMethod.POST)
    public ResponseVO createRole(@RequestBody RoleVO roleVO) {
        this._svc.create(roleVO);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }

    @RequestMapping(value = "/role.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteRole(@PathVariable("id") Integer id) {
        RoleVO role = this._svc.getRoleById(id);
        if (RoleExtension.isAdmin(role) || RoleExtension.isPowerUser(role)) {
            return ResponseVO.buildErrorResponse("Built-in role can't be removed");
        }

        this._svc.deleteById(id);
        return ResponseVO.buildSuccessResponse();
    }
}
