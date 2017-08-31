package com.ums.management.web.controller;

import javax.servlet.http.HttpSession;

import com.ums.management.core.model.Organization;
import com.ums.management.core.service.IUserService;
import com.ums.management.web.utility.SessionExtension;
import com.ums.management.core.view.model.LoginVO;
import com.ums.management.core.view.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ums.management.core.service.IMenuService;
import com.ums.management.web.view.vo.ResponseVO;

import static com.ums.management.web.utility.SessionExtension.SESSION_MENU;
import static com.ums.management.web.utility.SessionExtension.SESSION_USER;

@RestController
public class IndexController {
    @Autowired
    private IMenuService _menuSvc = null;

    @Autowired
    private IUserService _userSvc = null;

    @RequestMapping(value = "/index.json")
    public ResponseVO index(HttpSession httpSession) {

        UserVO user = SessionExtension.getCurrentUser(httpSession);
        ResponseVO response = null;

        if (user != null) {
            response = ResponseVO.buildSuccessResponse();
            response.addData(SESSION_USER, httpSession.getAttribute(SESSION_USER));
            response.addData(SESSION_MENU, httpSession.getAttribute(SESSION_MENU));
        } else {
            response = ResponseVO.buildErrorResponse("NotLogin").addData("code", 401);
        }

        return response;
    }

    @RequestMapping(value = "/index.json", method = RequestMethod.POST)
    public ResponseVO index(HttpSession httpSession, @RequestBody LoginVO login) {
        ResponseVO response = ResponseVO.buildSuccessResponse();
        UserVO loginUser = _userSvc.login(login);
        if (loginUser != null) {
            httpSession.setAttribute(SESSION_USER, loginUser);
            httpSession.setAttribute(SESSION_MENU, _menuSvc.getAllMenusByRole(loginUser.getRole()));

            response.addData("login", true);
        } else {
            response.addData("login", false);
        }

        return response;
    }

    @RequestMapping(value = "/logout.json", method = RequestMethod.GET)
    public ResponseVO logout(HttpSession httpSession) {
        httpSession.removeAttribute(SESSION_USER);
        httpSession.removeAttribute(SESSION_MENU);
        return ResponseVO.buildSuccessResponse();
    }

    @RequestMapping(value = "/currentOrg.json", method = RequestMethod.POST)
    public ResponseVO currentOrg(HttpSession httpSession, @RequestBody Organization org) {
        UserVO user = SessionExtension.getCurrentUser(httpSession);
        user.setCurrentOrganization(org);
        return ResponseVO.buildSuccessResponse();
    }
}
