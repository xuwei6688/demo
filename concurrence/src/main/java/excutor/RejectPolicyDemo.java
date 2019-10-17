package excutor;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AbortPolicy执行结果
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@6d03e736 rejected from java.util.concurrent.ThreadPoolExecutor@568db2f2[Running, pool size = 1, active threads = 1, queued tasks = 5, completed tasks = 0]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:112)
 * 	at excutor.RejectPolicyDemo.main(RejectPolicyDemo.java:29)
 * 这是第0个提交的线程
 * 这是第1个提交的线程
 * 这是第2个提交的线程
 * 这是第3个提交的线程
 * 这是第4个提交的线程
 * 这是第5个提交的线程
 * 原因：主线程挂了，工作线程没挂。因为主线程挂了，所以在主线程调用shutdown是永远也不会执行的。
 *
 * DiscardPolicy 执行结果
 * 这是第0个提交的线程
 * 这是第1个提交的线程
 * 这是第2个提交的线程
 * 这是第3个提交的线程
 * 这是第4个提交的线程
 * 这是第5个提交的线程
 * 原因：一个工作线程，5个在任务队列中，其他的都被拒绝了
 *
 * DiscardPolicy
 * 这是第0个提交的线程
 * 这是第5个提交的线程
 * 这是第6个提交的线程
 * 这是第7个提交的线程
 * 这是第8个提交的线程
 * 这是第9个提交的线程
 **/
public class RejectPolicyDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5);
//        ThreadPoolExecutor.AbortPolicy policy = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor.DiscardPolicy policy = new ThreadPoolExecutor.DiscardPolicy();
//        ThreadPoolExecutor.CallerRunsPolicy policy = new ThreadPoolExecutor.CallerRunsPolicy();
        ExecutorService executorService = new TimingThreadPool(1, 1, 0,
                TimeUnit.SECONDS,blockingQueue, policy);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(i));
        }
        System.out.println("主线程：" + Thread.currentThread().getId());
        executorService.shutdown();
    }
}

class Task implements Runnable{
    private int number;
    public Task(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        //模拟线程执行消耗时间
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这是第" + number + "个提交的线程,在线程" + Thread.currentThread().getId() + "中执行的");
    }
}
