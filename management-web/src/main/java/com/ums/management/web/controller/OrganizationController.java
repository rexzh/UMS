package com.ums.management.web.controller;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.service.IOrganizationService;
import com.ums.management.web.utility.PageExtension;
import com.ums.management.web.utility.RoleExtension;
import com.ums.management.web.utility.UserExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.RoleVO;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class OrganizationController {
	@Autowired
	private IOrganizationService _svc = null;

    @RequestMapping("/organization.json/byUser")
    public ResponseVO getOrganizationsByUser(HttpSession httpSession) {

        UserVO user = UserExtension.getCurrentUser(httpSession);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        if(RoleExtension.isAdmin(user.getRole()))
            response.addData("organizations", _svc.getOrganizations(null, null, null, null));
        else
            response.addData("organizations", _svc.getOrganizationsByUserId(user.getId(), null, null, null, null));
        return response;
    }

	@RequestMapping("/organization.json")
	public ResponseVO getOrganizations(HttpSession httpSession,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "enabled", required = false) Boolean enabled,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "rows", required = false) Integer rows) {
        Integer start = PageExtension.calcStart(page, rows);

        UserVO user = UserExtension.getCurrentUser(httpSession);

		ResponseVO response = ResponseVO.buildSuccessResponse();
        if(RoleExtension.isAdmin(user.getRole())) {
            response.addData("organizations", _svc.getOrganizations(name, enabled, start, rows));
            response.addData("count", _svc.countOrganizations(name, enabled));
        } else {
            response.addData("organizations", _svc.getOrganizationsByUserId(user.getId(), name, enabled, start, rows));
            response.addData("count", _svc.countOrganizationsByUserId(user.getId(), name, enabled));
        }

        return response;
	}

    @RequestMapping("/organization.json/{id}")
    public ResponseVO getOrganizationById(@PathVariable("id") Integer organizationId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("organization", _svc.getOrganizationById(organizationId));
        return response;
    }

	@RequestMapping(value = "/organization.json", method = RequestMethod.PUT)
    public ResponseVO updateOrganization(@RequestBody Organization organization) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.update(organization);
        return response;
    }

    @RequestMapping(value = "/organization.json", method = RequestMethod.POST)
    public ResponseVO createOrganization(HttpSession httpSession, @RequestBody Organization organization) {

        UserVO user = UserExtension.getCurrentUser(httpSession);
        ResponseVO response = ResponseVO.buildSuccessResponse();
        if(RoleExtension.isAdmin(user.getRole()))
            _svc.create(organization, null);
        else
            _svc.create(organization, user.getId());
        return response;
    }

    @RequestMapping(value = "/organization.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteOrganization(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteById(id);
        return response;
    }
}