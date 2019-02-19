package communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.只能在同步代码中使用wait否则会在执行时抛IllegalMonitorStateException
 * 2.调用wait()当前线程会进入WAITING状态，并释放锁，直到其它线程执行notify或notify唤醒当前线程
 * 3.wait和notify、notifyAll都属于Object的方法
 */
public class WaitDemo {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitDemo waitDemo = new WaitDemo();
        executorService.execute(() -> waitDemo.after());
        executorService.execute(() -> waitDemo.before());
    }
}
