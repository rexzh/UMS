package com.ums.management.web.controller;


import com.ums.management.core.model.Role;
import com.ums.management.core.service.IRoleService;
import com.ums.management.core.utility.CopyUtils;
import com.ums.management.core.view.model.ServiceResult;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.view.model.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


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

        ServiceResult result = this._svc.update(UserExtension.getCurrentUser(httpSession), roleVO);
        return ResponseVO.buildResponse(result);
    }

    @RequestMapping(value = "/role.json", method = RequestMethod.POST)
    public ResponseVO createRole(@RequestBody RoleVO roleVO) {
        this._svc.create(roleVO);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        return response;
    }

    @RequestMapping(value = "/role.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteRole(@PathVariable("id") Integer id) {
        ServiceResult result = this._svc.deleteById(id);
        return ResponseVO.buildResponse(result);
    }
}
