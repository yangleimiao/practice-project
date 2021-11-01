package com.demo.register.server;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanglei
 * @date 2021/11/1
 * 注册表
 */
public class Registry {
    private static Registry instance = new Registry();
    private Registry(){}

    private Map<String,Map<String,ServiceInstance>> registry = new HashMap<>();
    public static Registry getInstance(){
        return instance;
    }

    /**
     * 服务注册
     * @param serviceInstance 实例名称
     */
    public void register(ServiceInstance serviceInstance){
        Map<String,ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServiceInstanceName());
        if (serviceInstanceMap == null){
            serviceInstanceMap = new HashMap<>();
            registry.put(serviceInstance.getServiceInstanceName(),serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),serviceInstance);
    }

    /**
     * 获取服务实例
     * @param serviceInstanceName 实例名称
     * @param serviceInstanceId 实例Id
     * @return 服务实例
     */
    public ServiceInstance getServiceInstance(String serviceInstanceName,String serviceInstanceId){
        return registry.get(serviceInstanceName).get(serviceInstanceId);
    }

    /**
     * 获取注册表
     * @return
     */
    public Map<String,Map<String,ServiceInstance>> getRegistry(){
        return registry;
    }

    /**
     * 注册表中删除服务实例
     * @param serviceInstanceName
     * @param serviceInstanceId
     */
    public void remove(String serviceInstanceName,String serviceInstanceId){
        registry.get(serviceInstanceName).remove(serviceInstanceId);
    }




}
