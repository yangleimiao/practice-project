package com.demo.register.client;


import lombok.extern.slf4j.Slf4j;

/**
 * @author yanglei
 * @date 2021/11/1
 * 服务实例：包括基础信息和契约信息
 */
@Slf4j
public class ServiceInstance {
    private String serviceName;

    private String ip;

    private String hostname;

    private int port;

    private String serviceInstanceId;

    private Lease lease;

    private static final Long NOT_ALIVE_PERIOD = 90*1000L;

    public ServiceInstance(){
        this.lease = new Lease();
    }

    /**
     * 服务续约
     */
    public void renew(){
        this.lease.renew();
    }

    /**
     * 服务实例是否存活
     * @return
     */
    public Boolean isAlive(){
        return this.lease.isAlive();
    }
    private class Lease{
        /**
         * 最新一次心跳的时间
         */
        private Long latestHeartBeatTime = System.currentTimeMillis();

        /**
         * 续约
         */
        public void renew(){
            this.latestHeartBeatTime = System.currentTimeMillis();
            log.info("服务实例续约:{}",serviceInstanceId);
        }

        /**
         * 判断服务实例是否存活
         * @return
         */
        public Boolean isAlive(){
            Long currentTime = System.currentTimeMillis();
            if (currentTime - latestHeartBeatTime > NOT_ALIVE_PERIOD){
                log.info("服务实例 {} 未存活",serviceInstanceId);
                return false;
            }
            log.info("服务实例 {} 保持存活",serviceInstanceId);
            return true;
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }


    @Override
    public String toString() {
        return "ServiceInstance [serviceName=" + serviceName + ", ip=" + ip + ", hostname=" + hostname + ", port="
                + port + ", serviceInstanceId=" + serviceInstanceId + ", lease=" + lease + "]";
    }
}
