package com.ums.management.web.utility;

import com.ums.management.core.model.Role;
import com.ums.management.core.view.model.RoleVO;

/**
 * Created by zling on 2017-04-21.
 */
public class RoleExtension {
    private static final String CODE_ADMIN = "Admin";
    private static final String CODE_POWER_USER = "PowerUser";

    public static boolean isAdmin(Role role) {
        return role.getName().equals(CODE_ADMIN);
    }

    public static boolean isAdmin(RoleVO role) {
        return role.getName().equals(CODE_ADMIN);
    }

    public static boolean isPowerUser(Role role) {
        return role.getName().equals(CODE_POWER_USER);
    }

    public static boolean isPowerUser(RoleVO role) {
        return role.getName().equals(CODE_POWER_USER);
    }
}
