package com.demo.register.server;

/**
 * @author yanglei
 * @date 2021/11/10
 * 自我保护机制
 */
public class SelfProtectionPolicy {
    private static SelfProtectionPolicy instance = new SelfProtectionPolicy();
    private SelfProtectionPolicy(){}

    /**
     * 期望的心跳次数
     */
    private long expectHeartbeatRate = 0L;
    /**
     * 期望的心跳次数的阈值
     */
    private long expectHeartbeatThreshold = 0L;

    /**
     * 获取实例
     * @return
     */
    public static SelfProtectionPolicy getInstance(){
        return instance;
    }

    /**
     * 判断是否需要开启自我保护机制
     * @return
     */
    public Boolean isEnable(){
        HeartbeatMeasuredRate heartbeatMeasuredRate = HeartbeatMeasuredRate.getInstance();
        long latestMinuteHeartbeatRate = heartbeatMeasuredRate.getLatestMinuteHeartbeatRate();
        if (latestMinuteHeartbeatRate<expectHeartbeatThreshold){
            System.out.println("【自我保护机制开启】最近一分钟心跳数："+ latestMinuteHeartbeatRate);
            return true;
        }
        System.out.println("【自我保护机制未开启】");
        return false;

    }


    public long getExpectHeartbeatRate() {
        return expectHeartbeatRate;
    }

    public void setExpectHeartbeatRate(long expectHeartbeatRate) {
        this.expectHeartbeatRate = expectHeartbeatRate;
    }

    public long getExpectHeartbeatThreshold() {
        return expectHeartbeatThreshold;
    }

    public void setExpectHeartbeatThreshold(long expectHeartbeatThreshold) {
        this.expectHeartbeatThreshold = expectHeartbeatThreshold;
    }
}
