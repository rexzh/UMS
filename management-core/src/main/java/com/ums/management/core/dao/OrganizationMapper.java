package com.ums.management.core.dao;

import com.ums.management.core.model.Organization;

public interface OrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    int insert(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    int insertSelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    Organization selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization
     *
     * @mbggenerated Sat Apr 01 17:58:02 CST 2017
     */
    int updateByPrimaryKey(Organization record);
}