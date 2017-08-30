package com.ums.management.web.utility;

import com.ums.management.core.model.Role;
import com.ums.management.web.controller.IndexController;
import com.ums.management.core.view.model.UserVO;

import javax.servlet.http.HttpSession;


public class UserExtension {

    

    public static UserVO getCurrentUser(HttpSession session) {
        return (UserVO)session.getAttribute(IndexController.SESSION_USER);
    }
}
