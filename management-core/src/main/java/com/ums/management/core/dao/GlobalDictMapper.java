package com.ums.management.core.dao;

import com.ums.management.core.model.GlobalDict;

import java.util.List;
import java.util.Map;

public interface GlobalDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    int insert(GlobalDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    int insertSelective(GlobalDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    GlobalDict selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    int updateByPrimaryKeySelective(GlobalDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table global_dict
     *
     * @mbggenerated Wed Apr 12 13:27:32 CST 2017
     */
    int updateByPrimaryKey(GlobalDict record);

    List<GlobalDict> selectGlobalDicts(Map<String, Object> queryMap);
}