package interview.monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法 add、size，写两个线程：
 * 线程1，添加10个元素到容器中
 * 线程2，实时监控元素个数，当个数到达5个时，线程2给出提示并结束
 **/
public class MonitorDemo1 {
    private List<Integer> list = new ArrayList<>();

    public void add(Integer i) {
        list.add(i);
    }

    public Integer size() {
        return list.size();
    }

    public static void main(String[] args) {
        MonitorDemo1 demo1 = new MonitorDemo1();
        Object lock = new Object();
        new Thread(() -> {

            if (demo1.size() != 5) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("线程2执行结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }

        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    demo1.add(i);
                    System.out.println(i);
                    if (i == 5) {

                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1").start();
    }
}
