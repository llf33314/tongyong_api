package com.gt.api.bean.sign;

/**
 * 签名的JavaBean
 * @author psr
 * @date 2017/8/1
 * @version v1.0
 */
public class SignBean {

    public SignBean(){}

    /**
     * full构造器
     * @param sign 签名
     * @param timeStamp 时间戳
     * @param randNum 随机数
     */
    public SignBean(String sign, String timeStamp, String randNum){
        this.sign = sign;
        this.timeStamp = timeStamp;
        this.randNum = randNum;
    }

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 随机数
     */
    private String randNum;


    /**
     * 获取签名
     * @return
     */
    public String getSign() {
        return sign;
    }

    /**
     * 保存签名
     * @param sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取时间戳
     * @return
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 保存时间戳
     * @param timeStamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 获取随机数
     * @return
     */
    public String getRandNum() {
        return randNum;
    }

    /**
     * 保存随机数
     * @param randNum
     */
    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }
}
