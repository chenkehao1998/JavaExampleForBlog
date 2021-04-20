package com.kehao.crud;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.List;

public class CRUDDemo {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new RetryNTimes(10,1000);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        client.start();
        delete(client);
//        System.in.read();
        client.close();
    }

    public static void getChildren(CuratorFramework client) throws Exception {
        List<String> stringList = client.getChildren().forPath("/");
        System.out.println(stringList);
    }

    public static void getDataOfNode(CuratorFramework client) throws Exception {
        byte[] bytes = client.getData().forPath("/b");
        System.out.println(new String(bytes));
    }



    public static void add(CuratorFramework client) throws Exception{
        client.create().forPath("/a");
        client.create().forPath("/b","b节点".getBytes());
        client.create().creatingParentsIfNeeded().forPath("/c/d");
    }

    public static void addNodeByMode(CuratorFramework client)throws Exception{
        client.create().withMode(CreateMode.PERSISTENT).forPath("/e");//普通
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/f");//临时
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/g");//序列
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/g");//序列
        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/g");//序列
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/h");//临时序列
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/h");//临时序列
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/h");//临时序列
    }

    public static void delete(CuratorFramework client){
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/g");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
