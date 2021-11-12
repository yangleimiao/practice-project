package com.demo.dfs.namenode.server;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yanglei
 * @date 2021/11/12
 * 文件目录树组件
 */
public class FSDirectory {

    /**
     * 文件中的目录树
     */
    private INodeDirectory dirTree;
    public FSDirectory(){
        this.dirTree = new INodeDirectory("/");
    }

    /**
     * 创建目录
     * @param path
     */
    public void mkdir(String path){
        synchronized (dirTree){
            String[] pathes = path.split("/");
            INodeDirectory parent = null;
            for (String splitedPath: pathes){
                if (splitedPath.trim().equals("")){

                }
            }
        }

    }


    /**
     * 文件目录树中的一个节点
     */
    private interface INode {}

    /**
     * 文件目录树中的目录
     */
    public static class INodeDirectory implements INode{
        private String path;
        private List<INode> children;

        public INodeDirectory(String path){
            this.path = path;
            this.children = new LinkedList<>();
        }

        public void addChild(INode iNode){
            this.children.add(iNode);
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<INode> getChildren() {
            return children;
        }

        public void setChildren(List<INode> children) {
            this.children = children;
        }
    }

    /**
     * 文件目录树中的一个文件
     */
    public static class INodeFile implements INode{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
