package com.curatorApi;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CuratorApiOperation {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkUtil.create();
//        String path = "/curatorNode2/234/passion";
//        //增加操作
//
//        String result = curatorFramework.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT).forPath(path, "create".getBytes());
//        System.out.println("创建节点：" + result);
//
//        //更新操作
//        Stat update = curatorFramework.setData().forPath(path, "update".getBytes());
//        System.out.println("更新节点" + update);
//
//        //查询操作
//        byte[] bytes = curatorFramework.getData().forPath(path);
//        System.out.println(new String(bytes));
//
//        //删除操作
//        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curatorNode");
//
        //异步操作
//        System.out.println(Thread.currentThread().getName());
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        curatorFramework.create().withMode(CreateMode.PERSISTENT)
//                .inBackground(new BackgroundCallback() {
//                    @Override
//                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//                        System.out.println(Thread.currentThread().getName()+"->resultCode:"+event.getResultCode()+"->" + event.getType());
//
//                        countDownLatch.countDown();
//                    }
//                }, service).forPath("/curatorS");
//        countDownLatch.await();

        //事物操作 curator独有
        curatorFramework.inTransaction().create().forPath("/nodeA", "xixha".getBytes())
                .and().create().forPath("/nodeB", "不存在".getBytes()).and().commit();
    }
}
