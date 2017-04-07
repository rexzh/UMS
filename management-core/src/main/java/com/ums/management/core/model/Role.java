package com.ums.management.core.model;

public class Role {
    public static final String ADMIN = "Admin";

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.ID
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.Name
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.Description
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.Enabled
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    private Boolean enabled;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.ID
     *
     * @return the value of role.ID
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
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
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
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
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
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
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
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
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
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
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.Enabled
     *
     * @return the value of role.Enabled
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.Enabled
     *
     * @param enabled the value for role.Enabled
     *
     * @mbggenerated Tue Apr 04 11:11:07 CST 2017
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}