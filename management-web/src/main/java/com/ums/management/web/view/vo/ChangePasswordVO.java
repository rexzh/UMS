package com.ums.management.web.view.vo;

/**
 * Created by Rex on 2017/4/8.
 */
public class ChangePasswordVO {
    private long id;
    private String oldPassword;
    private String newPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
