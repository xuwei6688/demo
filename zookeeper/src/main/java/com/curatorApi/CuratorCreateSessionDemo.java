package com.curatorApi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorCreateSessionDemo {
    private static String CONNECTSTR = "192.168.0.104:2181,192.168.0.105:2181,192.168.0.106:2181,192.168.0.107:2181";
    public static void main(String[] args) throws Exception {
        //两种方式创建会话
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECTSTR, 5000, 5000,
                new ExponentialBackoffRetry(10000, 3));
        /*
         * ExponentialBackoffRetry重试策略
         * sleepMs = (long)(this.baseSleepTimeMs * Math.max(1, this.random.nextInt(1 << retryCount + 1)));
         */


        curatorFramework.start();
        System.out.println("success");
        //fluent风格
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().connectString(CONNECTSTR)
                .sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(10000, 3)).build();
        curatorFramework1.start();

        System.out.println("success");


    }
}
