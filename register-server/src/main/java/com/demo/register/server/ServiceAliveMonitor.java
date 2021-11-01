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
    private Daemon daemon = new Daemon();
    public void start(){
        daemon.start();
    }

    /**
     * 监控服务存活的后台线程
     */
    private class Daemon extends Thread{
        private Registry registry = Registry.getInstance();

        @Override
        public void run() {
            Map<String,Map<String,ServiceInstance>> registerMap = null;
            while (true){
                try {
                    registerMap = registry.getRegistry();
                    for (String serviceName : registerMap.keySet()){
                        for (ServiceInstance serviceInstance: registerMap.get(serviceName).values()){
                            // 说明服务实例没有存活，在注册表中去掉它
                            if (!serviceInstance.isAlive()){
                                registry.remove(serviceName,serviceInstance.getServiceInstanceId());
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
