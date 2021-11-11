package com.demo.register.server;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanglei
 * @date 2021/11/1
 * 注册表
 */
@Slf4j
public class ServiceRegistry {
    private static ServiceRegistry instance = new ServiceRegistry();
    private ServiceRegistry(){}

    private Map<String,Map<String,ServiceInstance>> registry = new HashMap<>();
    public static ServiceRegistry getInstance(){
        return instance;
    }

    /**
     * 服务注册
     * @param serviceInstance 实例名称
     */
    public synchronized void register(ServiceInstance serviceInstance){
        Map<String,ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServiceName());
        if (serviceInstanceMap == null){
            serviceInstanceMap = new HashMap<>();
            registry.put(serviceInstance.getServiceName(),serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),serviceInstance);
        log.info("服务实例 {} 完成注册",serviceInstance);
    }

    /**
     * 获取服务实例
     * @param serviceInstanceName 实例名称
     * @param serviceInstanceId 实例Id
     * @return 服务实例
     */
    public synchronized ServiceInstance getServiceInstance(String serviceInstanceName,String serviceInstanceId){
        return registry.get(serviceInstanceName).get(serviceInstanceId);
    }

    /**
     * 获取注册表
     * @return
     */
    public synchronized Map<String,Map<String,ServiceInstance>> getRegistry(){
        return registry;
    }

    /**
     * 注册表中删除服务实例
     * @param serviceInstanceName
     * @param serviceInstanceId
     */
    public synchronized void remove(String serviceInstanceName,String serviceInstanceId){
        log.info("删除服务实例 {}",serviceInstanceId);
        registry.get(serviceInstanceName).remove(serviceInstanceId);
    }




}
