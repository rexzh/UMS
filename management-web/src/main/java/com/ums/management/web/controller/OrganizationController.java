package com.ums.management.web.controller;

import com.ums.management.core.model.Organization;
import com.ums.management.core.service.IOrganizationService;
import com.ums.management.web.utility.PageExtension;
import com.ums.management.web.utility.SessionExtension;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.core.view.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class OrganizationController {
    @Autowired
    private IOrganizationService _svc = null;

    @RequestMapping("/organization.json/byUser")
    public ResponseVO getOrganizationsByUser(HttpSession httpSession) {

        UserVO user = SessionExtension.getCurrentUser(httpSession);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("organizations", _svc.getOrganizations(user, null, null, null, null));
        return response;
    }

    @RequestMapping("/organization.json")
    public ResponseVO getOrganizations(HttpSession httpSession,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "enabled", required = false) Boolean enabled,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "rows", required = false) Integer rows) {
        Integer start = PageExtension.calcStart(page, rows);

        UserVO user = SessionExtension.getCurrentUser(httpSession);

        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("organizations", _svc.getOrganizations(user, name, enabled, start, rows));
        response.addData("count", _svc.countOrganizations(user, name, enabled));
        return response;
    }

    @RequestMapping("/organization.json/{id}")
    public ResponseVO getOrganizationById(@PathVariable("id") Integer organizationId) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        response.addData("organization", _svc.getOrganizationById(organizationId));
        return response;
    }

    @RequestMapping(value = "/organization.json", method = RequestMethod.PUT)
    public ResponseVO updateOrganization(HttpSession session, @RequestBody Organization organization) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.update(SessionExtension.getCurrentUser(session), organization);
        return response;
    }

    @RequestMapping(value = "/organization.json", method = RequestMethod.POST)
    public ResponseVO createOrganization(HttpSession httpSession, @RequestBody Organization organization) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.create(SessionExtension.getCurrentUser(httpSession), organization);
        return response;
    }

    @RequestMapping(value = "/organization.json/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteOrganization(HttpSession session, @PathVariable("id") Integer id) {

        ResponseVO response = ResponseVO.buildSuccessResponse();
        _svc.deleteById(SessionExtension.getCurrentUser(session), id);
        return response;
    }
}