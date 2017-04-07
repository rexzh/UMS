package com.ums.management.web.controller;

import javax.servlet.http.HttpSession;

import com.ums.management.core.model.Menu;
import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IUserService;
import com.ums.management.web.view.vo.LoginVO;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ums.management.core.service.IMenuService;
import com.ums.management.web.view.vo.ResponseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Giggs
 *
 */
@RestController
public class IndexController {

	public static final String SESSION_USER = "user";
	public static final String SESSION_MENU = "menus";
	public static final String SESSION_ENVIRONMENT = "env";

	@Value("${app.env}")
	private String env = null;

	@Autowired
	private IMenuService _menuSvc = null;

	@Autowired
	private IUserService _userSvc = null;



    @RequestMapping(value = "/index.json")
    public ResponseVO index(HttpSession httpSession) {

        UserVO user = (UserVO) httpSession.getAttribute(SESSION_USER);
		ResponseVO response = null;

        if(user != null){
            response = ResponseVO.buildSuccessResponse();
            response.addData(SESSION_USER, httpSession.getAttribute(SESSION_USER));
            response.addData(SESSION_MENU, httpSession.getAttribute(SESSION_MENU));
			response.addData(SESSION_ENVIRONMENT, env);
        } else {
            response = ResponseVO.buildErrorResponse("NotLogin");
        }

		return response;
    }


	@RequestMapping(value = "/index.json", method = RequestMethod.POST)
	public ResponseVO index(HttpSession httpSession, @RequestBody LoginVO login) {
		ResponseVO response = ResponseVO.buildSuccessResponse();
    	User loginUser = _userSvc.login(login.getUsername(), login.getPassword());
    	if(loginUser != null) {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(loginUser, userVO);
    		Role role = _userSvc.getRoleByUser(loginUser);
    		userVO.setRole(role);
    		List<Organization> orgs = _userSvc.getOrganizationsByUser(loginUser);
    		userVO.setOrganizations(orgs);
    		//TODO: clean disabled orgs.
    		httpSession.setAttribute(SESSION_USER, userVO);

    		if(role.getEnabled()) {
				httpSession.setAttribute(SESSION_MENU, _menuSvc.getAllMenusByRole(role));
			} else {
				httpSession.setAttribute(SESSION_MENU, new ArrayList<Menu>());
			}

			httpSession.setAttribute(SESSION_ENVIRONMENT, env);
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
}
