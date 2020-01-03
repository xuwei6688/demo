package com.curatorApi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

/**
 * @Author xuwei
 * @Date 2019/12/19 0019
 * @Version V1.0
 **/
public class DLock {
    private String path;
    private CuratorFramework curatorFramework;

    public DLock(String path, CuratorFramework curatorFramework) {
        this.path = path;
        this.curatorFramework = curatorFramework;
    }

    public void acquire() {
        boolean done = true;
        while (done) {
            try {
                curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(path, "234".getBytes());
                done = false;
            } catch (KeeperException.NodeExistsException e) {
//                System.out.println(e.getMessage());
            } catch (Exception e) {

            }
        }

    }
    public void release() throws Exception {
        curatorFramework.delete().guaranteed().forPath(path);
    }
}
