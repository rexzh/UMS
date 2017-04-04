package com.ums.management.web.view.vo;

import com.ums.management.core.model.RoleMenu;

import java.util.List;

/**
 * Created by Rex on 2016/9/15.
 */
public class RoleVO {
    private Integer id;

    private String name;

    private String description;

    private boolean enabled;

    private List<RoleMenu> roleMenus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
