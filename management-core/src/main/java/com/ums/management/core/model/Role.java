package com.ums.management.core.model;

public class Role {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.ID
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.Name
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.Description
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.ID
     *
     * @return the value of role.ID
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.ID
     *
     * @param id the value for role.ID
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.Name
     *
     * @return the value of role.Name
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.Name
     *
     * @param name the value for role.Name
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.Description
     *
     * @return the value of role.Description
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.Description
     *
     * @param description the value for role.Description
     *
     * @mbggenerated Sat Apr 01 17:56:22 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}