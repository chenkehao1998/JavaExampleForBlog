package com.kehao.listener;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.RetryNTimes;



public class NodeCacheDemo {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new RetryNTimes(10,1000);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        client.start();
        NodeCache nodeCache = new NodeCache(client,"/b");
        nodeCache.getListenable().addListener(()->{
            ChildData data = nodeCache.getCurrentData();
            if(data == null){
                System.out.println("node has been deleted!");
            }else {
                System.out.println("data:"+new String(data.getData()));
            }
        });
        nodeCache.start(true);

        System.in.read();
        client.close();
    }
}
