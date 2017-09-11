package com.gt.api.bean.session;

import java.io.Serializable;
import java.util.Date;
public class BusUser implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;


    private String password;


    private String email;


    private String phone;


    private String gender;


    private String registerIp;


    private String recentIp;


    private Date ctime;


    private Date mtime;


    private String status;


    private Integer level;


    private Integer city_id;


    private Date startTime;


    private Date endTime;


    private Integer days;

    private String levelDesc;

    private Integer years;


    private Double fansCurrency;

    private long flow;

    private Integer industryid;


    private Integer pid;

    private Integer smsCount;

    private Integer istechnique;

    private Integer advert;

    private String busmoney_level;
    private String regionids;
    private Integer isagent;
    private Integer agentid;

    private String realname;

    private Integer login_source;

    private Boolean is_binding;

    private Date unbundling_time;


    private String fixed_phone;

    private String customer_id;

    private String merchant_name;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getGender() {
        return gender;
    }


    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }


    public String getRegisterIp() {
        return registerIp;
    }


    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }


    public String getRecentIp() {
        return recentIp;
    }


    public void setRecentIp(String recentIp) {
        this.recentIp = recentIp == null ? null : recentIp.trim();
    }


    public Date getCtime() {
        return ctime;
    }


    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }


    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getFansCurrency() {
        return fansCurrency;
    }

    public void setFansCurrency(Double fansCurrency) {
        this.fansCurrency = fansCurrency;
    }

    public Integer getIndustryid() {
        return industryid;
    }

    public void setIndustryid(Integer industryid) {
        this.industryid = industryid;
    }

    public long getFlow() {
        return flow;
    }

    public void setFlow(long flow) {
        this.flow = flow;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Integer getIstechnique() {
        return istechnique;
    }

    public void setIstechnique(Integer istechnique) {
        this.istechnique = istechnique;
    }

    public Integer getAdvert() {
        return advert;
    }

    public void setAdvert(Integer advert) {
        this.advert = advert;
    }

    public String getBusmoney_level() {
        return busmoney_level;
    }

    public void setBusmoney_level(String busmoney_level) {
        this.busmoney_level = busmoney_level;
    }

    public String getRegionids() {
        return regionids;
    }

    public void setRegionids(String regionids) {
        this.regionids = regionids;
    }

    public Integer getIsagent() {
        return isagent;
    }

    public void setIsagent(Integer isagent) {
        this.isagent = isagent;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getLogin_source() {
        return login_source;
    }

    public void setLogin_source(Integer login_source) {
        this.login_source = login_source;
    }

    public Boolean getIs_binding() {
        return is_binding;
    }

    public void setIs_binding(Boolean is_binding) {
        this.is_binding = is_binding;
    }

    public Date getUnbundling_time() {
        return unbundling_time;
    }

    public void setUnbundling_time(Date unbundling_time) {
        this.unbundling_time = unbundling_time;
    }

    public String getFixed_phone() {
        return fixed_phone;
    }

    public void setFixed_phone(String fixed_phone) {
        this.fixed_phone = fixed_phone;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }


}
