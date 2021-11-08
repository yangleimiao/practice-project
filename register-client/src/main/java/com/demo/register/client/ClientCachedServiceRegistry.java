package com.demo.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanglei
 * @date 2021/11/8
 * 客户端缓存的服务注册表
 */
public class ClientCachedServiceRegistry {
    /**
     * 服务注册表拉去间隔时间
     */
    private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 30 * 1000L;
    /**
     * 客户端缓存的服务注册表
     */
    private Map<String, Map<String,ServiceInstance>> registry = new HashMap<>();
    // 后台线程
    private Daemon daemon;

    private RegisterClient registerClient;

    private HttpSender httpSender;

    public ClientCachedServiceRegistry(RegisterClient registerClient,HttpSender httpSender){
        this.daemon = new Daemon();
        this.registerClient = registerClient;
        this.httpSender = httpSender;
    }
    /**
     * 初始化
     */
    public void initialize(){
        this.daemon.start();
    }

    /**
     * 销毁
     */
    public void destroy(){
        this.daemon.interrupt();
    }

    /**
     * 负责拉取注册表到本地
     */
    private class Daemon extends Thread{
        @Override
        public void run() {
            while (registerClient.isRunning()){
                try {
                    registry = httpSender.fetchServiceRegistry();
                    Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取服务注册表
     * @return
     */
    public Map<String, Map<String,ServiceInstance>> getRegistry(){
        return registry;
    }
}
