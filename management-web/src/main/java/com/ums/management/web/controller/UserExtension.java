package com.ums.management.web.controller;

import com.ums.management.core.model.Role;
import com.ums.management.core.model.User;
import com.ums.management.web.view.vo.UserVO;

import javax.servlet.http.HttpSession;

/**
 * Created by zling on 2017-04-07.
 */
public class UserExtension {
    public static boolean hasEnoughPower(HttpSession session, Role roleToEdit) {
        UserVO user = (UserVO)session.getAttribute(IndexController.SESSION_USER);
        if((!user.getRole().getName().equals(Role.ADMIN)) && roleToEdit.getName().equals(Role.ADMIN))
            return false;
        else
            return true;
    }

    public static UserVO getCurrentUser(HttpSession session) {
        return (UserVO)session.getAttribute(IndexController.SESSION_USER);
    }
}
