package com.curatorApi;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

public class CuratorEventDemo {
    /**
     * 三种watcher来做节点的监听
     * pathcache   监视一个路径下子节点的创建、删除、节点数据更新
     * NodeCache   监视一个节点的创建、更新、删除
     * TreeCache   pathcaceh+nodecache 的合体（监视路径下的创建、更新、删除事件），
     * 缓存路径下的所有子节点的数据
     */
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkUtil.create();
        //NodeCache
//        NodeCache nodeCache = new NodeCache(curatorFramework, "/nodeCache");
//        nodeCache.start(true);
//        nodeCache.getListenable().addListener(() -> System.out.println("节点变化：" + new String( nodeCache.getCurrentData().getData())));
//
//        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/nodeCache", "haha".getBytes());
//        TimeUnit.SECONDS.sleep(2);


        //PatchChildrenCache
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, "/pathcache", true);
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener(((client, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("子节点增加");
                    break;
                case CHILD_UPDATED:
                    System.out.println("子节点更新");
                    break;
                case CHILD_REMOVED:
                    System.out.println("子节点删除");
                    break;
                default:break;
            }
        }));

        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/pathcache");

        /**
         * ????为什么这里必须要sleep才能显示回调？
         */
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/pathcache/children", "create".getBytes());

        TimeUnit.SECONDS.sleep(1);
        curatorFramework.setData().forPath("/pathcache/children", "update".getBytes());

        TimeUnit.SECONDS.sleep(1);
        curatorFramework.delete().forPath("/pathcache/children");
        TimeUnit.SECONDS.sleep(20);
    }
}
