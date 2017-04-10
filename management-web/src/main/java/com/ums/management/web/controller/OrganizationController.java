package com.ums.management.web.controller;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.service.IOrganizationService;
import com.ums.management.web.view.vo.ResponseVO;
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
        if(user.getRole().getName().equals(Role.ADMIN))
            response.addData("organizations", _svc.getOrganizations());
        else
            response.addData("organizations", user.getOrganizations());
        return response;
    }

	@RequestMapping("/organization.json")
	public ResponseVO getOrganizations() {
		ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("organizations", _svc.getOrganizations());
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
    public ResponseVO createOrganization(@RequestBody Organization organization) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.create(organization);
        return response;
    }

    @RequestMapping(value = "/organization.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteOrganization(@PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteById(id);
        return response;
    }
}