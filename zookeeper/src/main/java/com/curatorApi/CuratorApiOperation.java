package com.curatorApi;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CuratorApiOperation {
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkUtil.create();
        System.out.println("客户端已连接");
//        String path = "/curatorNode2/234/passion";
//        //增加操作
//        //创建一个持久节点，并递归创建父节点
        String result = curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/seq", "job".getBytes());
        System.out.println("创建节点：" + result);
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
//       //guaranteed()如果删除失败，只要客户端会话有效，会在后台一直尝试删除
//        curatorFramework.delete().guaranteed().forPath("/node2/child2");

        //异步接口，操作结果的返回信息异步返回
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
        curatorFramework.create().withMode(CreateMode.PERSISTENT);
//                .inBackground(new BackgroundCallback() {
//                    @Override
//                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
//                        System.out.println(Thread.currentThread().getName()+"->resultCode:"+event.getResultCode()+"->" + event.getType());
//
//                        countDownLatch.countDown();
//                    }
//                }, service).forPath("/node2");
//        countDownLatch.await();

        //** **/** **/** **/** **/** **/事件监听//** **/** **/** **/** **/** **/** **/
        //1.NodeCache 能够监听节点数据变化，该节点被创建，但是不能监听该节点被删除
//        final NodeCache nodeCache = new NodeCache(curatorFramework, "/node");
//        //start的参数默认是false，如果是true第一次启动时就会拉取数据节点的内容
//        nodeCache.start(true);
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("Node data update,new data：" + new String(nodeCache.getCurrentData().getData()));
//            }
//        });

        //2.对子节点的变化监听，无法监听孙子节点的变化
//        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, "/node", true);
//        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
//        cache.getListenable().addListener(new PathChildrenCacheListener() {
//            @Override
//            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
//                switch (event.getType()) {
//                    case CHILD_ADDED:
//                        System.out.println("新增节点：" + event.getData().getPath());
//                        break;
//                    case CHILD_UPDATED:
//                        System.out.println("更新节点：" + event.getData().getPath());
//                        break;
//                    case CHILD_REMOVED:
//                        System.out.println("删除节点：" + event.getData().getPath());
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//        List<String> strings = curatorFramework.getChildren().forPath("/");
//        strings.forEach(e ->{
//            try {
//                if (!e.equalsIgnoreCase("zookeeper")){
//                    String path = "/" + e;
//                    curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });


//        TimeUnit.SECONDS.sleep(100);
        //事物操作 curator独有
//        curatorFramework.inTransaction().create().forPath("/nodeA", "xixha".getBytes())
//                .and().create().forPath("/nodeB", "不存在".getBytes()).and().commit();
    }
}
