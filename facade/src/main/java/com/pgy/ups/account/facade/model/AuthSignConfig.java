package com.pgy.ups.account.facade.model;

import java.io.Serializable;
import java.util.Date;

public class AuthSignConfig implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.id
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.tpp_nid
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private String tppNid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.tpp_mer_no
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private String tppMerNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.auth_sign_type
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private String authSignType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.sms_flag
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private Integer smsFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.gmt_create
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_sign_config.gmt_modified
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table auth_sign_config
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.id
     *
     * @return the value of auth_sign_config.id
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.id
     *
     * @param id the value for auth_sign_config.id
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.tpp_nid
     *
     * @return the value of auth_sign_config.tpp_nid
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public String getTppNid() {
        return tppNid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.tpp_nid
     *
     * @param tppNid the value for auth_sign_config.tpp_nid
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setTppNid(String tppNid) {
        this.tppNid = tppNid == null ? null : tppNid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.tpp_mer_no
     *
     * @return the value of auth_sign_config.tpp_mer_no
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public String getTppMerNo() {
        return tppMerNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.tpp_mer_no
     *
     * @param tppMerNo the value for auth_sign_config.tpp_mer_no
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setTppMerNo(String tppMerNo) {
        this.tppMerNo = tppMerNo == null ? null : tppMerNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.auth_sign_type
     *
     * @return the value of auth_sign_config.auth_sign_type
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public String getAuthSignType() {
        return authSignType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.auth_sign_type
     *
     * @param authSignType the value for auth_sign_config.auth_sign_type
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setAuthSignType(String authSignType) {
        this.authSignType = authSignType == null ? null : authSignType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.sms_flag
     *
     * @return the value of auth_sign_config.sms_flag
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public Integer getSmsFlag() {
        return smsFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.sms_flag
     *
     * @param smsFlag the value for auth_sign_config.sms_flag
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setSmsFlag(Integer smsFlag) {
        this.smsFlag = smsFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.gmt_create
     *
     * @return the value of auth_sign_config.gmt_create
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.gmt_create
     *
     * @param gmtCreate the value for auth_sign_config.gmt_create
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_sign_config.gmt_modified
     *
     * @return the value of auth_sign_config.gmt_modified
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_sign_config.gmt_modified
     *
     * @param gmtModified the value for auth_sign_config.gmt_modified
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_sign_config
     *
     * @mbggenerated Wed Oct 10 19:48:27 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tppNid=").append(tppNid);
        sb.append(", tppMerNo=").append(tppMerNo);
        sb.append(", authSignType=").append(authSignType);
        sb.append(", smsFlag=").append(smsFlag);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}