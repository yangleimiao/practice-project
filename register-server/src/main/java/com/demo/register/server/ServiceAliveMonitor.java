package com.demo.register.server;

import java.util.Map;

/**
 * @author yanglei
 * @date 2021/11/1
 * 存活状态监控组件
 */
public class ServiceAliveMonitor {
    /**
     * 检查服务实例是否存活的间隔
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60* 1000L;
    /**
     * 监控服务存活的后台线程
     */
    private Daemon daemon;

    public ServiceAliveMonitor(){
        this.daemon = new Daemon();
        // 设置为daemon线程
        // 如果工作线程（非daemon线程）都结束了，daemon线程不会阻止jvm进程退出
        daemon.setDaemon(true);
        daemon.setName("ServiceAliveMonitor");
    }

    /**
     * 启动后台线程
     */
    public void start(){
        daemon.start();
    }

    /**
     * 监控服务存活的后台线程
     */
    private class Daemon extends Thread{
        private ServiceRegistry registry = ServiceRegistry.getInstance();

        @Override
        public void run() {
            Map<String,Map<String,ServiceInstance>> registerMap;
            while (true){
                try {
                    // 判断是否已开启保护机制
                    SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                    if (selfProtectionPolicy.isEnable()){
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                        continue;
                    }
                    registerMap = registry.getRegistry();
                    for (String serviceName : registerMap.keySet()){
                        for (ServiceInstance serviceInstance: registerMap.get(serviceName).values()){
                            // 说明服务实例没有存活，在注册表中去掉它
                            if (!serviceInstance.isAlive()){
                                registry.remove(serviceName,serviceInstance.getServiceInstanceId());
                                // 更新自我保护机制的阈值
                                synchronized (SelfProtectionPolicy.class){

                                    selfProtectionPolicy.setExpectHeartbeatRate(selfProtectionPolicy.getExpectHeartbeatRate() -2);
                                    selfProtectionPolicy.setExpectHeartbeatThreshold((long) (selfProtectionPolicy.getExpectHeartbeatRate()*0.85));
                                }
                            }
                        }
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
