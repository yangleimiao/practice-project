package com.demo.register.client;

import java.util.UUID;

/**
 * @author yanglei
 * @date 2021/10/29
 */
public class RegisterClient {

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
    }

    public void start() {
        // 一旦启动了这个组件之后，他就负责在服务上干两个事情
        // 第一个事情，就是开启一个线程向register-server去发送请求，注册这个服务
        // 第二个事情，就是在注册成功之后，就会开启另外一个线程去发送心跳

        // 我们在register-client这块就开启一个线程
        // 这个线程刚启动的时候，第一个事情就是完成注册
        // 如果注册完成了之后，他就会进入一个while true死循环
        // 每隔30秒就发送一个请求去进行心跳

        new RegisterClientWorker(serviceInstanceId).start();
    }


   private class RegisterClientWorker extends Thread {

        public static final String SERVICE_NAME = "inventory-service";
        public static final String IP = "192.168.31.207";
        public static final String HOSTNAME = "inventory01";
        public static final int PORT = 9000;

        /**
         * http通信组件
         */
        private HttpSender httpSender;
        /**
         * 是否完成服务注册
         */
        private Boolean finishedRegister;
        /**
         * 服务实例id
         */
        private String serviceInstanceId;

        public RegisterClientWorker(String serviceInstanceId) {
            this.httpSender = new HttpSender();
            this.finishedRegister = false;
            this.serviceInstanceId = serviceInstanceId;
        }

        @Override
        public void run() {
            if(!finishedRegister) {
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

                // 如果说注册成功的话
                if(RegisterResponse.SUCCESS.equals(registerResponse.getStatus())) {
                    this.finishedRegister = true;
                } else {
                    return;
                }
            }

            // 如果说注册成功了，就进入while true死循环
            if(finishedRegister) {
                HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
                heartbeatRequest.setServiceInstanceId(serviceInstanceId);
                HeartbeatResponse heartbeatResponse = null;

                while(true) {
                    try {
                        heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                        System.out.println("心跳的结果为：" + heartbeatResponse.getStatus() + "......");
                        Thread.sleep(30 * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
