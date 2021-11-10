package com.demo.register.client;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author yanglei
 * @date 2021/10/29
 */
public class RegisterClient {

    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "172.30.44.10";
    public static final String HOSTNAME = "inventory01";
    public static final int PORT = 9000;
    private static final Long HEARTBEAT_INTERVAL = 30 * 1000L;
    /**
     * http通信组件
     */
    private HttpSender httpSender;
    /**
     * 服务实例id
     */
    private String serviceInstanceId;
    /**
     * 心跳线程
     */
    private HeartbeatWorker heartbeatWorker;

    /**
     * 服务实例是否在运行
     */
    private volatile Boolean isRunning;
    /**
     * 客户端缓存的注册表
     */
    private ClientCachedServiceRegistry registry;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        this.httpSender = new HttpSender();
        this.heartbeatWorker = new HeartbeatWorker();
        this.isRunning = true;
        this.registry = new ClientCachedServiceRegistry(this,httpSender);
    }

    public void start() {
        // 一旦启动了这个组件之后，他就负责在服务上干两个事情
        // 第一个事情，就是开启一个线程向register-server去发送请求，注册这个服务
        // 第二个事情，就是在注册成功之后，就会开启另外一个线程去发送心跳

        // 我们在register-client这块就开启一个线程
        // 这个线程刚启动的时候，第一个事情就是完成注册
        // 如果注册完成了之后，他就会进入一个while true死循环
        // 每隔30秒就发送一个请求去进行心跳
        try {
            RegisterClientWorker registerClientWorker = new RegisterClientWorker();
            registerClientWorker.start();
            registerClientWorker.join();
            // 启动发送心跳的程序
            heartbeatWorker.start();
            // 初始化客户端缓存的服务注册表组件
            this.registry.initialize();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 停止RegisterClient组件
     */
    public void shutdown(){
        this.isRunning = false;
        this.heartbeatWorker.interrupt();
        this.registry.destroy();
        // 服务下线
        this.httpSender.cancel(SERVICE_NAME,serviceInstanceId);
    }

    @Slf4j
   private class RegisterClientWorker extends Thread {
        @Override
        public void run() {
                // 应该是获取当前机器的信息
                // 包括当前机器的ip地址、hostname，以及你配置这个服务监听的端口号
                // 从配置文件里可以拿到
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setServiceName(SERVICE_NAME);
                registerRequest.setIp(IP);
                registerRequest.setHostname(HOSTNAME);
                registerRequest.setPort(PORT);
                registerRequest.setServiceInstanceId(serviceInstanceId);

                RegisterResponse registerResponse = httpSender.register(registerRequest);
                System.out.println("服务注册的结果是：" + registerResponse.getStatus() + "......");
                log.info("服务注册的结果是：" + registerResponse.getStatus() + "......");
        }

    }
    @Slf4j
    private class HeartbeatWorker extends Thread{
        @Override
        public void run() {
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setServiceName(SERVICE_NAME);
            heartbeatRequest.setServiceInstanceId(serviceInstanceId);
            HeartbeatResponse heartbeatResponse;
            while (isRunning){
                try {
                    heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                    log.info("心跳的结果为：" + heartbeatResponse.getStatus() + "......");
                    Thread.sleep(HEARTBEAT_INTERVAL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 查看RegisterClient是否还在运行
     * @return
     */
    public Boolean isRunning(){
        return isRunning;
    }

}
