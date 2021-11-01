package com.demo.register.server;

/**
 * @author yanglei
 * @date 2021/11/1
 */
public class HeartbeatRequest {
    /**
     * 服务实例Id
     */
    private String serviceInstanceId;
    /**
     * 服务名称
     */
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }
    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }
    @Override
    public String toString() {
        return "HeartbeatRequest [serviceName=" + serviceName + ", serviceInstanceId=" + serviceInstanceId + "]";
    }
}
