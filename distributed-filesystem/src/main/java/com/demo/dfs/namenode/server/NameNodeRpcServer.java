package com.demo.dfs.namenode.server;

/**
 * @author yanglei
 * @date 2021/11/12
 * NameNode的RPC服务的接口
 */
public class NameNodeRpcServer {

    private FSNameSystem nameSystem;
    public NameNodeRpcServer(FSNameSystem nameSystem){
        this.nameSystem = nameSystem;
    }


    public Boolean mkdir(String path){
        return this.nameSystem.mkdir(path);
    }

    /**
     * 启动这个rpc server
     */
    public void start(){
        // 开始监听指定的rpc server的端口号
    }

}
