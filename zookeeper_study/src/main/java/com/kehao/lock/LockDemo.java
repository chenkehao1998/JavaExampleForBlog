package com.kehao.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        //构造超时重试策略，每1s重试一次，重试10次
        RetryPolicy retryPolicy = new RetryNTimes(10,1000);
        //构造客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181",retryPolicy);
        // 启动客户端
        client.start();

        //构造锁
        InterProcessLock lock = new InterProcessMutex(client,"/user/1/update");

        //构造userDao
        UserDao userDao = new UserDao();

        //并发修改100遍
        for (int i = 0; i < 100; i++) {
            new Thread((new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.acquire();
                        // 查询当前积分
                        int score = userDao.getScoreFromDb();
                        // 积分加1
                        score++;
                        //更新数据库
                        userDao.updateScore(score);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            })).start();
        }
        //随眠5s等待任务执行完成
        Thread.sleep(5000L);
        //输出最终的结果
        System.out.println("完成，结果："+userDao.getScoreFromDb());

        //关闭client
        client.close();
    }
}
