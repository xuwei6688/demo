package juc;

import java.util.concurrent.CountDownLatch;

/**
 * java并发编程实战5.5.1例子
 * @Author xuwei
 * @Date 2019/10/15 0015
 * @Version V1.0
 **/
public class CountDownLatchDemo2 {

    /**
     * 统计任务并发运行时间
     * 运用了两个闭锁，一个startGate，控制所有的任务同时启动
     * 另一个endGate，确保所有的任务都执行完毕后计算完成时间
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        long startTime = System.nanoTime();
        startGate.countDown();

        endGate.await();
        return System.nanoTime() - startTime;
    }
}
