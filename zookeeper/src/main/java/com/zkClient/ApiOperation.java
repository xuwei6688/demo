package com.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author xuwei
 * @Date 2019/12/18 0018
 * @Version V1.0
 **/
public class ApiOperation {
    private static String CONNECTSTR = "192.168.101.97:2181,192.168.101.97:2182,192.168.101.97:2183";
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(CONNECTSTR, 5000);

        //获取子节点
        List<String> children = zkClient.getChildren("/");
        System.out.println(children);

        //创建持久节点
//        zkClient.createPersistent("/node2", "234234");

        //注册监听
        zkClient.subscribeChildChanges("/node2", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + " child changed:" + list);
            }
        });

        zkClient.createPersistent("/node2/child4");
        TimeUnit.SECONDS.sleep(1);

    }
}
