package com.ums.management.core.utility;

import com.ums.management.core.model.Role;

public class RoleMatrix {
    public static boolean hasEnoughPower(Role editor, Role roleToEdit) {
        if((!editor.isAdmin()) && roleToEdit.isAdmin()) {
            return false;
        } else if((!editor.isAdmin()) && (!editor.isPowerUser())) {
            return false;
        } else
            return true;
    }

    public static boolean hasEnoughPower(Role editor) {
        return editor.isAdmin() || editor.isPowerUser();
    }
}
