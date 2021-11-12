package com.demo.dfs.namenode.server;

/**
 * @author yanglei
 * @date 2021/11/12
 */
public class NameNode {
    /**
     * NameNode 是否在运行
     */
    private volatile Boolean shouldRun;
    /**
     * 管理元数据
     */
    private FSNameSystem nameSystem;
    /**
     * NameNode对外提供rpc接口的server
     */
    private NameNodeRpcServer rpcServer;

    public NameNode(){
        this.shouldRun = true;
    }

    /**
     * 初始化NameNode
     */
    private void initialize(){
        this.nameSystem = new FSNameSystem();
        this.rpcServer =  new NameNodeRpcServer(this.nameSystem);
        this.rpcServer.start();
    }

    /**
     * 使NameNode运行
     */
    private void run(){

    }

    public static void main(String[] args) {
        NameNode nameNode = new NameNode();
        nameNode.initialize();
        nameNode.run();
    }








}
