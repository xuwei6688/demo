package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore可以控制对共享资源并发访问的线程数量
 * 构造方法传递并发访问数量
 * 线程通过acquire()方法尝试访问资源，通过release()方法释放资源。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();

                    System.out.println("线程" + Thread.currentThread().getId() + "访问共享资源");
                    TimeUnit.SECONDS.sleep(1);

                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
