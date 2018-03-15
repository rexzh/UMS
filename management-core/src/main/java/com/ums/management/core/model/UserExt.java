package com.ums.management.core.model;

public class UserExt {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.user_id
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.family_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private String familyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.given_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private String givenName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.gender
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private Integer gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.birth
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private Long birth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_ext.pic
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    private String pic;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.user_id
     *
     * @return the value of user_ext.user_id
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.user_id
     *
     * @param userId the value for user_ext.user_id
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.family_name
     *
     * @return the value of user_ext.family_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.family_name
     *
     * @param familyName the value for user_ext.family_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName == null ? null : familyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.given_name
     *
     * @return the value of user_ext.given_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.given_name
     *
     * @param givenName the value for user_ext.given_name
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName == null ? null : givenName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.gender
     *
     * @return the value of user_ext.gender
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.gender
     *
     * @param gender the value for user_ext.gender
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.birth
     *
     * @return the value of user_ext.birth
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public Long getBirth() {
        return birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.birth
     *
     * @param birth the value for user_ext.birth
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setBirth(Long birth) {
        this.birth = birth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_ext.pic
     *
     * @return the value of user_ext.pic
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_ext.pic
     *
     * @param pic the value for user_ext.pic
     *
     * @mbggenerated Thu Mar 15 22:50:33 CST 2018
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}