package com.gt.api.bean.session;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class WxPublicUsers implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;


    private String wxUserToken;


    private String authRefreshToken;


    private Integer busUserId;


    private String authorizerInfo;


    private String headImg;


    private Integer serviceTypeInfo;


    private Integer verifyTypeInfo;


    private String userName;


    private String alias;


    private String qrcodeUrl;


    private String appid;


    private String appsecret;


    private String funcInfo;


    private String accessToken;


    private Date tokenLastTime;


    private Integer tokenDuration;


    private String apiticket;


    private Integer apiTicketDuration;


    private Date apiTicketLastTime;


    private Date createTime;


    private Date modifyTime;


    private Integer totalVoiceCount;


    private Integer totalVideoCount;


    private Integer totalImageCount;


    private Integer totalNewsCount;


    private Integer useNewsCount;


    private Integer useVoiceCount;


    private Integer useVideoCount;


    private Integer useImageCount;


    private Integer isFriendReplyOpen;


    private Integer isAutoreplyOpen;


    private Integer isTemplateOpen;


    private Integer totalBulk;


    private Integer useBulk;


    private String mchId;


    private String apiKey;


    private Integer isPower;



    private String businessInfo;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getWxUserToken() {
        return wxUserToken;
    }


    public void setWxUserToken(String wxUserToken) {
        this.wxUserToken = wxUserToken == null ? null : wxUserToken.trim();
    }


    public String getAuthRefreshToken() {
        return authRefreshToken;
    }


    public void setAuthRefreshToken(String authRefreshToken) {
        this.authRefreshToken = authRefreshToken == null ? null : authRefreshToken.trim();
    }


    public Integer getBusUserId() {
        return busUserId;
    }


    public void setBusUserId(Integer busUserId) {
        this.busUserId = busUserId;
    }


    public String getAuthorizerInfo() {
        return authorizerInfo;
    }


    public void setAuthorizerInfo(String authorizerInfo) {
        this.authorizerInfo = authorizerInfo == null ? null : authorizerInfo.trim();
    }


    public String getHeadImg() {
        return headImg;
    }


    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }


    public Integer getServiceTypeInfo() {
        return serviceTypeInfo;
    }


    public void setServiceTypeInfo(Integer serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
    }


    public Integer getVerifyTypeInfo() {
        return verifyTypeInfo;
    }


    public void setVerifyTypeInfo(Integer verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getAlias() {
        return alias;
    }


    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }


    public String getQrcodeUrl() {
        return qrcodeUrl;
    }


    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl == null ? null : qrcodeUrl.trim();
    }


    public String getAppid() {
        return appid;
    }


    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }


    public String getAppsecret() {
        return appsecret;
    }


    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }


    public String getFuncInfo() {
        return funcInfo;
    }


    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo == null ? null : funcInfo.trim();
    }


    public String getAccessToken() {
        return accessToken;
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }


    public Date getTokenLastTime() {
        return tokenLastTime;
    }


    public void setTokenLastTime(Date tokenLastTime) {
        this.tokenLastTime = tokenLastTime;
    }


    public Integer getTokenDuration() {
        return tokenDuration;
    }


    public void setTokenDuration(Integer tokenDuration) {
        this.tokenDuration = tokenDuration;
    }


    public String getApiticket() {
        return apiticket;
    }


    public void setApiticket(String apiticket) {
        this.apiticket = apiticket == null ? null : apiticket.trim();
    }


    public Integer getApiTicketDuration() {
        return apiTicketDuration;
    }


    public void setApiTicketDuration(Integer apiTicketDuration) {
        this.apiTicketDuration = apiTicketDuration;
    }


    public Date getApiTicketLastTime() {
        return apiTicketLastTime;
    }


    public void setApiTicketLastTime(Date apiTicketLastTime) {
        this.apiTicketLastTime = apiTicketLastTime;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getModifyTime() {
        return modifyTime;
    }


    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }


    public Integer getTotalVoiceCount() {
        return totalVoiceCount;
    }


    public void setTotalVoiceCount(Integer totalVoiceCount) {
        this.totalVoiceCount = totalVoiceCount;
    }


    public Integer getTotalVideoCount() {
        return totalVideoCount;
    }


    public void setTotalVideoCount(Integer totalVideoCount) {
        this.totalVideoCount = totalVideoCount;
    }


    public Integer getTotalImageCount() {
        return totalImageCount;
    }


    public void setTotalImageCount(Integer totalImageCount) {
        this.totalImageCount = totalImageCount;
    }


    public Integer getTotalNewsCount() {
        return totalNewsCount;
    }


    public void setTotalNewsCount(Integer totalNewsCount) {
        this.totalNewsCount = totalNewsCount;
    }


    public Integer getUseNewsCount() {
        return useNewsCount;
    }


    public void setUseNewsCount(Integer useNewsCount) {
        this.useNewsCount = useNewsCount;
    }



    public Integer getUseVoiceCount() {
        return useVoiceCount;
    }


    public void setUseVoiceCount(Integer useVoiceCount) {
        this.useVoiceCount = useVoiceCount;
    }


    public Integer getUseVideoCount() {
        return useVideoCount;
    }


    public void setUseVideoCount(Integer useVideoCount) {
        this.useVideoCount = useVideoCount;
    }


    public Integer getUseImageCount() {
        return useImageCount;
    }


    public void setUseImageCount(Integer useImageCount) {
        this.useImageCount = useImageCount;
    }


    public Integer getIsFriendReplyOpen() {
        return isFriendReplyOpen;
    }


    public void setIsFriendReplyOpen(Integer isFriendReplyOpen) {
        this.isFriendReplyOpen = isFriendReplyOpen;
    }


    public Integer getIsAutoreplyOpen() {
        return isAutoreplyOpen;
    }


    public void setIsAutoreplyOpen(Integer isAutoreplyOpen) {
        this.isAutoreplyOpen = isAutoreplyOpen;
    }


    public Integer getIsTemplateOpen() {
        return isTemplateOpen;
    }


    public void setIsTemplateOpen(Integer isTemplateOpen) {
        this.isTemplateOpen = isTemplateOpen;
    }


    public Integer getTotalBulk() {
        return totalBulk;
    }


    public void setTotalBulk(Integer totalBulk) {
        this.totalBulk = totalBulk;
    }


    public Integer getUseBulk() {
        return useBulk;
    }


    public void setUseBulk(Integer useBulk) {
        this.useBulk = useBulk;
    }


    public String getMchId() {
        return mchId;
    }


    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }


    public String getApiKey() {
        return apiKey;
    }


    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }


    public Integer getIsPower() {
        return isPower;
    }


    public void setIsPower(Integer isPower) {
        this.isPower = isPower;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
}
