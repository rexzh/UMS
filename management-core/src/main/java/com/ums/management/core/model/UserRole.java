package com.ums.management.core.model;

public class UserRole {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.user_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_role.role_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    private Integer roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.user_id
     *
     * @return the value of user_role.user_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.user_id
     *
     * @param userId the value for user_role.user_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_role.role_id
     *
     * @return the value of user_role.role_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_role.role_id
     *
     * @param roleId the value for user_role.role_id
     *
     * @mbggenerated Sat Apr 01 17:58:45 CST 2017
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}