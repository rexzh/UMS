package com.ums.management.web.view.vo;

import com.ums.management.core.model.Organization;
import com.ums.management.core.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rex on 2017/4/3.
 */
public class UserVO {

    private long id;


    private String code;


    private String name;


    private String salt;


    private String password;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getSalt() {
        return salt;
    }


    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    private Role role;

    public Role getRole(){ return role; }

    private boolean enabled;

    public void setRole(Role role){
        this.role = role;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private List<Organization> organizations = new ArrayList<>();

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
