package excutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 通过继承ThreadPoolExecutor并实现beforeExecute、afterExecute、terminated
 * 可以对线程池的运行情况做一个统计
 **/
public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong nums = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();


    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.getCompletedTaskCount();
        totalTime.getAndAdd(System.nanoTime() - startTime.get());
        nums.incrementAndGet();
    }

    @Override
    protected void terminated() {
        System.out.println("总任务数" + nums.get());
        System.out.println("任务执行总时间" + totalTime.get());
    }
}
