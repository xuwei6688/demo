package com.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *  构造方法是异步的，不一定已经建立了连接
 *  创建节点时父节点必须存在
 */
public class ApiOperation implements Watcher {
    private static String CONNECTSTR = "192.168.101.97:2181,192.168.101.97:2182,192.168.101.97:2183";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;
    private static Stat stat=new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        /*
         *  构造方法是异步的，它完成初始化立即返回，并不会等待连接建立。
         *  需要通过Watcher
         */
        zooKeeper = new ZooKeeper(CONNECTSTR, 5000, new ApiOperation());
        countDownLatch.await();

        String path = "/node";
        //创建节点
        zooKeeper.exists(path, true);
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //更新节点
        zooKeeper.exists(path, true);
        zooKeeper.setData(path, "343".getBytes(), -1);
        TimeUnit.SECONDS.sleep(1);

        //创建子节点
        //creates/delete  sets
        zooKeeper.exists(path + "/node-1", true);
        zooKeeper.create(path + "/node-1", "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        TimeUnit.SECONDS.sleep(1);


        //更新子节点
        zooKeeper.getChildren(path, true);
        zooKeeper.delete(path+ "/node-1",  -1);
        TimeUnit.SECONDS.sleep(1);



    }

    public void process(WatchedEvent event) {
        try {
            //如果连接建立
            if (event.getState() == Event.KeeperState.SyncConnected) {
                if (event.getType() == Event.EventType.None && event.getPath() == null) {
                    countDownLatch.countDown();
                    System.out.println(event.getState() + "-->" + event.getType() + event.getPath());
                } else if (event.getType() == Event.EventType.NodeDataChanged) {//节点更新触发
                    //getData watch true 表示sets  deletes操作会触发
                    System.out.println("数据变动路径：" + event.getPath() + "   变更后的数据：" + new String(zooKeeper.getData(event.getPath(), false, stat)));
                } else if (event.getType() == Event.EventType.NodeCreated) {//子节点创建触发
                    System.out.println("节点创建路径：" + event.getPath() + "创建的数据：" + new String(zooKeeper.getData(event.getPath(), false, stat)));
                } else if (event.getType() == Event.EventType.NodeChildrenChanged) {//子节点
                    System.out.println("子节点更新路径：" + event.getPath() + "变更后的子节点列表" + zooKeeper.getChildren(event.getPath(), false));
                } else if (event.getType() == Event.EventType.NodeDeleted) {//子节点删除触发
                    System.out.println("节点删除路径：" + event.getType() + "删除的数据：" );
                }
            }
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

    }
}
