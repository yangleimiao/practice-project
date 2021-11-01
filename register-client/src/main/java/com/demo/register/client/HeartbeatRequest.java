package com.demo.register.client;

/**
 * @author yanglei
 * @date 2021/11/1
 */
public class HeartbeatRequest {
    private String serviceInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }
    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }
}
