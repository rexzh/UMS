package com.ums.management.core.model;

public class LocalDict {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.type_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private Integer typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.org_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private Integer orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.value
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private Integer value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.name
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column local_dict.comment
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    private String comment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.id
     *
     * @return the value of local_dict.id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.id
     *
     * @param id the value for local_dict.id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.type_id
     *
     * @return the value of local_dict.type_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.type_id
     *
     * @param typeId the value for local_dict.type_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.org_id
     *
     * @return the value of local_dict.org_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.org_id
     *
     * @param orgId the value for local_dict.org_id
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.value
     *
     * @return the value of local_dict.value
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public Integer getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.value
     *
     * @param value the value for local_dict.value
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.name
     *
     * @return the value of local_dict.name
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.name
     *
     * @param name the value for local_dict.name
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column local_dict.comment
     *
     * @return the value of local_dict.comment
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column local_dict.comment
     *
     * @param comment the value for local_dict.comment
     *
     * @mbggenerated Wed Apr 12 16:03:26 CST 2017
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}