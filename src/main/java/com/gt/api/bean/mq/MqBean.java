package com.gt.api.bean.mq;

/**
 * rabbitmq中的实体类
 */
public class MqBean {
    //mq中的ip
    private String mqIp;
    //mq中的password
    private Integer port;

    public String getMqIp() {
        return mqIp;
    }

    public void setMqIp(String mqIp) {
        this.mqIp = mqIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
