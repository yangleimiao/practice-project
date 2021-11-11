package com.demo.register.server;

/**
 * @author yanglei
 * @date 2021/11/9
 * 心跳测量计数器
 */
public class HeartbeatMeasuredRate {

    private static HeartbeatMeasuredRate instance = new HeartbeatMeasuredRate();
    private HeartbeatMeasuredRate(){}

    /**
     * 最近一分钟的心跳次数
     */
    private long latestMinuteHeartbeatRate = 0L;
    /**
     * 最近一分钟的时间戳
     */
    private long latestMinuteTimestamp = System.currentTimeMillis();

    /**
     * 获取实例
     * @return
     */
    public static HeartbeatMeasuredRate getInstance(){
        return instance;
    }

    /**
     * 增加一次最近一分钟的心跳次数
     */
    public synchronized void increment(){
        long currentTime = System.currentTimeMillis();
        if (currentTime - latestMinuteTimestamp > 60* 1000){
            // 进入新的一分钟，归零
            latestMinuteHeartbeatRate = 0L;
            this.latestMinuteTimestamp = System.currentTimeMillis();
        }
        latestMinuteHeartbeatRate++;
    }
    /**
     * 获取最近一分钟的心跳次数
     * @return
     */
    public synchronized long getLatestMinuteHeartbeatRate() {
        return latestMinuteHeartbeatRate;
    }

}
