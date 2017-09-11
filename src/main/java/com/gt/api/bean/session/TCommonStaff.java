package com.gt.api.bean.session;

import java.io.Serializable;

public class TCommonStaff implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;


    private String loginName;


    private String password;


    private String phone;


    private String email;


    private String jobNumber;


    private Integer gender;


    private Integer shopId;


    private Integer status;


    private String createTime;


    private Integer createPerson;


    private String recentIp;


    private String remark;


    private String name;


    private Integer isdelect;


    private Integer positionid;


    private Integer branid;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getLoginName() {
        return loginName;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getJobNumber() {
        return jobNumber;
    }


    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber == null ? null : jobNumber.trim();
    }


    public Integer getGender() {
        return gender;
    }


    public void setGender(Integer gender) {
        this.gender = gender;
    }


    public Integer getShopId() {
        return shopId;
    }


    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }


    public Integer getCreatePerson() {
        return createPerson;
    }


    public void setCreatePerson(Integer createPerson) {
        this.createPerson = createPerson;
    }


    public String getRecentIp() {
        return recentIp;
    }


    public void setRecentIp(String recentIp) {
        this.recentIp = recentIp == null ? null : recentIp.trim();
    }


    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Integer getIsdelect() {
        return isdelect;
    }


    public void setIsdelect(Integer isdelect) {
        this.isdelect = isdelect;
    }


    public Integer getPositionid() {
        return positionid;
    }


    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }


    public Integer getBranid() {
        return branid;
    }


    public void setBranid(Integer branid) {
        this.branid = branid;
    }
}
