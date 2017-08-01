package com.gt.api.bean.sign;

/**
 * 签名工具枚举类
 * @author psr
 * @date 2017/8/1
 * @version v1.0
 */
public enum SignEnum {

    // 00开头是代表成功
    SUCCESS("001","检查成功"),
    // 10开头是验证失败
    TIME_OUT("101","请求超时"),
    SIGN_ERROR("102","签名错误"),
    // 20开头是系统错误
    SERVER_ERROR("201","系统错误")
    ;

    SignEnum(String code, String value){};

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
