package com.curatorApi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorFrameworkUtil {
    private static String CONNECTSTR = "192.168.101.97:2181,192.168.101.97:2182,192.168.101.97:2183";
    public static CuratorFramework create() {
        CuratorFramework curatorFramework1 = CuratorFrameworkFactory.builder().connectString(CONNECTSTR)
                .sessionTimeoutMs(5000).connectionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(10000, 3)).build();
        curatorFramework1.start();
        return curatorFramework1;
    }
}
