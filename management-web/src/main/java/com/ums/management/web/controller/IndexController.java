package com.ums.management.web.controller;

import javax.servlet.http.HttpSession;

import com.ums.management.core.service.IUserService;
import com.ums.management.web.view.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ums.management.core.service.IMenuService;
import com.ums.management.web.view.vo.ResponseVO;

/**
 * 
 * @author Giggs
 *
 */
@RestController
public class IndexController {

	public static final String USER_SESSION = "user";
	public static final String IS_LOGIN = "islogin";

	@Value("${app.env}")
	private String env = null;

	@Autowired
	private IMenuService _menuSvc = null;

	@Autowired
	private IUserService _userSvc = null;



    @RequestMapping(value = "/index.json")
    public ResponseVO index(HttpSession httpSession) {
    	/*
        Boolean isLogin = (Boolean) httpSession.getAttribute(IS_LOGIN);
        if(isLogin != null && isLogin) {
            ResponseVO response = ResponseVO.buildSuccessResponse();
            response.addData("doctor", httpSession.getAttribute("doctor"));
            response.addData("menus", httpSession.getAttribute("menus"));
			response.addData("env", env);
            return response;
        } else
            return ResponseVO.buildSuccessResponse();
        */
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("menus", _menuSvc.getAllMenusByRole(null));
		return response;
    }


	@RequestMapping(value = "/index.json", method = RequestMethod.POST)
	public ResponseVO index(HttpSession httpSession, @RequestBody LoginVO login) {
    	boolean loginSuccess = _userSvc.login(login.getUsername(), login.getPassword());


		//List<Menu> userMenus = null;

		/*
		ResultBean resultBean = doctorService.login(loginBean);
		if (resultBean.getStatus().equals("200")) {

			httpSession.setAttribute("menus", userMenus);
			httpSession.setAttribute(IS_LOGIN, true);
		} else {
			return ResponseVO.buildErrorResponse(resultBean.getMessage());
		}
		*/
		ResponseVO response = ResponseVO.buildSuccessResponse();
		//response.addData("doctor", doctor);
		response.addData("menus", _menuSvc.getAllMenus());
		//response.addData("env", env);

		return response;
	}

	@RequestMapping(value = "/logout.json", method = RequestMethod.GET)
	public ResponseVO logout(HttpSession httpSession) {
		httpSession.removeAttribute("doctor");
		httpSession.removeAttribute("menus");
		httpSession.removeAttribute(IS_LOGIN);
		return ResponseVO.buildSuccessResponse().addData("url", "/login");
	}
}
