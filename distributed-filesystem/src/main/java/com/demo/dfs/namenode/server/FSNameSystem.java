package com.demo.dfs.namenode.server;

/**
 * @author yanglei
 * @date 2021/11/12
 * 管理元数据
 */
public class FSNameSystem {
    private FSDirectory directory;

    private FSEditsLog editsLog;


    public FSNameSystem(){
        this.directory = new FSDirectory();
        this.editsLog = new FSEditsLog();
    }


    public Boolean mkdir(String path){
        this.directory.mkdir(path);
        this.editsLog.logEdit("make directory");
        return true;
    }
}
