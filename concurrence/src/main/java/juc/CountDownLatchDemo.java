package juc;

import util.SleepUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch内部维护了一个计数器，初始化时通过构造方法为计数器赋值。
 * 主线程调用await让主线程等待。其他线程调用countdown()让技术器减一，当计数器为0时主线程继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(() ->{
                countDownLatch.countDown();
                Thread.currentThread().interrupt();
                SleepUtil.sleep(2);
                System.out.println(Thread.currentThread().getId());
            });
        }

        countDownLatch.await();
        System.out.println("end");

    }
}
