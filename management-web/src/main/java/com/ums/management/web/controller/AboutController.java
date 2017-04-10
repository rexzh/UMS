package com.ums.management.web.controller;

import com.ums.management.core.model.Menu;
import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.core.service.IMenuService;
import com.ums.management.core.service.IUserService;
import com.ums.management.web.view.vo.LoginVO;
import com.ums.management.web.view.vo.ResponseVO;
import com.ums.management.web.view.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AboutController {
	@Value("${app.env}")
	private String env = null;



    @RequestMapping(value = "/about.json")
    public ResponseVO about() {
		ResponseVO response = ResponseVO.buildSuccessResponse();
		response.addData("env", env);
		response.addData("version", "v0.0.1-SNAPSHOT");

		return response;
    }
}
