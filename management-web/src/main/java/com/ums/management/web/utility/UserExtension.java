package com.ums.management.web.utility;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.web.controller.IndexController;
import com.ums.management.web.view.vo.RoleVO;
import com.ums.management.web.view.vo.UserVO;

import javax.servlet.http.HttpSession;


public class UserExtension {
    public static boolean hasEnoughPower(HttpSession session, Role roleToEdit) {
        UserVO user = getCurrentUser(session);
        if((!RoleExtension.isAdmin(user.getRole())) && RoleExtension.isAdmin(roleToEdit))
            return false;
        else
            return true;
    }

    public static UserVO getCurrentUser(HttpSession session) {
        return (UserVO)session.getAttribute(IndexController.SESSION_USER);
    }
}
