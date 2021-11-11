package com.demo.register.server;

import java.util.Map;

/**
 * @author yanglei
 * @date 2021/11/1
 * 负责处理register-client 发送的请求
 */
public class RegisterServerController {
    private ServiceRegistry registry = ServiceRegistry.getInstance();
    /**
     * 服务注册
     * @param request 注册请求
     * @return 注册响应
     */
    public RegisterResponse register(RegisterRequest request){
        RegisterResponse registerResponse = new RegisterResponse();
        try {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setHostname(request.getHostname());
            serviceInstance.setIp(request.getIp());
            serviceInstance.setPort(request.getPort());
            serviceInstance.setServiceInstanceId(request.getServiceInstanceId());
            serviceInstance.setServiceName(request.getServiceName());
            registry.register(serviceInstance);
            // 更新自我保护机制的阈值
            synchronized (SelfProtectionPolicy.class){
                SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                // +2 是因为30秒发送一次心跳，一分钟就是2次
                selfProtectionPolicy.setExpectHeartbeatRate(selfProtectionPolicy.getExpectHeartbeatRate()+2);
                selfProtectionPolicy.setExpectHeartbeatThreshold((long) (selfProtectionPolicy.getExpectHeartbeatRate()*0.85));
            }
            registerResponse.setStatus(RegisterResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            registerResponse.setStatus(RegisterResponse.FAILURE);
        }
        return registerResponse;
    }

    /**
     * 发送心跳
     * @param request 心跳请求
     * @return 心跳响应
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        try {
            ServiceInstance serviceInstance = registry.getServiceInstance(
                    request.getServiceName(),request.getServiceInstanceId());
            serviceInstance.renew();
            // 记录每分钟的心跳的次数
            HeartbeatMeasuredRate heartbeatMeasuredRate = HeartbeatMeasuredRate.getInstance();
            heartbeatMeasuredRate.increment();

            heartbeatResponse.setStatus(HeartbeatResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            heartbeatResponse.setStatus(HeartbeatResponse.FAILURE);
        }
        return heartbeatResponse;
    }

    /**
     * 拉取服务注册表
     * @return
     */
    public Map<String,Map<String,ServiceInstance>> fetchServiceRegistry(){
        return registry.getRegistry();
    }

    /**
     * 服务下线
     * @param serviceName
     * @param serviceInstanceId
     */
    public void cancel(String serviceName,String serviceInstanceId){
        registry.remove(serviceName,serviceInstanceId);
    }

}
