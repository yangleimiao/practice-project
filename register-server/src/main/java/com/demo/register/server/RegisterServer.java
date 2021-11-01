package com.demo.register.server;


import java.util.UUID;

/**
 * @author yanglei
 * @date 2021/11/1
 */

public class RegisterServer {

    /**
     * 模拟
     */
    public static void main(String[] args) throws InterruptedException {
        RegisterServerController controller = new RegisterServerController();
        String serviceInstanceId = UUID.randomUUID().toString().replace("-","");
        // 模拟发起服务注册的请求
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setHostname("");
        registerRequest.setIp("");
        registerRequest.setPort(9000);
        registerRequest.setServiceInstanceId(serviceInstanceId);
        registerRequest.setServiceName("");

        controller.register(registerRequest);

        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);
        heartbeatRequest.setServiceName("");
        controller.heartbeat(heartbeatRequest);

        // 开启一个后台线程，检查微服务的存活
        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();
        while (true){
            Thread.sleep(30*1000);
        }
    }

}
