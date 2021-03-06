package com.ums.management.core.dao;

import com.ums.management.core.model.UserOrg;

public interface UserOrgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    int insert(UserOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    int insertSelective(UserOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    UserOrg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    int updateByPrimaryKeySelective(UserOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_org
     *
     * @mbggenerated Wed Apr 05 13:20:15 CST 2017
     */
    int updateByPrimaryKey(UserOrg record);

    int deleteByUserId(long userId);
}