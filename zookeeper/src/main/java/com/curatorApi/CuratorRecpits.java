package com.curatorApi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Curator对典型应用场景的封装
 * @Author xuwei
 * @Date 2019/12/19 0019
 * @Version V1.0
 **/
public class CuratorRecpits {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkUtil.create();
        System.out.println("客户端已连接");

        //** **/** **/Master选举//** **/** **/
//        LeaderSelector selector = new LeaderSelector(curatorFramework, "/master_path", new LeaderSelectorListenerAdapter() {
//            @Override
//            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
//                System.out.println("成为Master角色");
//                TimeUnit.SECONDS.sleep(1000);
//            }
//        });
//        selector.autoRequeue();
//        selector.start();

        //分布式锁
//        final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/lock_path");
//        final DLock dLock = new DLock("/lock_path", curatorFramework);
//        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                try {
//                    countDownLatch.await();
//                    dLock.acquire();
//                } catch (Exception e) { }
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
//                String orderNo = sdf.format(new Date());
//                System.out.println("生成的订单号是：" + orderNo);
//                try {
//                    dLock.release();
//                } catch (Exception e) { }
//            }).start();
//        }
//        countDownLatch.countDown();

        final InterProcessReadWriteLock shardLock = new InterProcessReadWriteLock(curatorFramework, "/shared_lock");
        InterProcessMutex readLock = shardLock.readLock();
        readLock.acquire();
        readLock.acquire();
        TimeUnit.SECONDS.sleep(1000);
    }
}
