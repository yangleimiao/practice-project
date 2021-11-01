package com.demo.register.server;

/**
 * @author yanglei
 * @date 2021/11/1
 * 负责处理register-client 发送的请求
 */
public class RegisterServerController {
    private Registry registry = Registry.getInstance();
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
            heartbeatResponse.setStatus(HeartbeatResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            heartbeatResponse.setStatus(HeartbeatResponse.FAILURE);
        }
        return heartbeatResponse;
    }
}
