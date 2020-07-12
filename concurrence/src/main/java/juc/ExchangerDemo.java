package juc;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 用来给两个线程交换数据，当一个线程调用 exchange 方法时会阻塞，直到另一个线程也调用 exchange 方法，
 * 这时两个线程都从阻塞中继续恢复继续执行，返回值就是参数的交换。
 * @Author xuwei
 * @Date 2020/5/24
 * @Version V1.0
 **/
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            String s = "s1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + s);
        }, "t1").start();
        new Thread(()->{
            String s = "s2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + s);
        }, "t2").start();
    }
}
