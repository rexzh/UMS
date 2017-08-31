package com.ums.management.web.utility;

import com.ums.management.core.model.Role;
import com.ums.management.web.controller.IndexController;
import com.ums.management.core.view.model.UserVO;

import javax.servlet.http.HttpSession;


public class SessionExtension {
    public static final String SESSION_USER = "user";
    public static final String SESSION_MENU = "menus";

    public static UserVO getCurrentUser(HttpSession session) {
        return (UserVO)session.getAttribute(SESSION_USER);
    }
}
