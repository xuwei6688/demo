package juc;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue是一个没有边界BlockingQueue实现，加入其中的元素必需实现Delayed接口。
 * 当生产者线程调用put之类的方法加入元素时，会触发Delayed接口中的compareTo方法进行排序，
 * 也就是说队列中元素的顺序是按到期时间排序的，而非它们进入队列的顺序。排在队列头部的元素是最早到期的，越往后到期时间赿晚。
 *
 * 消费者线程查看队列头部的元素，注意是查看不是取出。然后调用元素的getDelay方法，
 * 如果此方法返回的值小０或者等于０，则消费者线程会从队列中取出此元素，并进行处理。
 * 如果getDelay方法返回的值大于0，则消费者线程wait返回的时间值后，再从队列头部取出元素，此时元素应该已经到期。
 **/
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayTask task3 = new DelayTask(15, "task3");
        DelayTask task1 = new DelayTask(5, "task1");
        DelayTask task2 = new DelayTask(10, "task2");

        DelayQueue<DelayTask> queue = new DelayQueue<>();
        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        for (int i = 0; i < 3; i++) {
            DelayTask task = queue.take();
            System.out.println("name:" + task.getName() + "time:" + LocalDateTime.now());
        }
    }
}

class DelayTask implements Delayed {
    /** 任务的开始时间 **/
    private long startTime;
    /** 任务名称 **/
    private String name;

    public DelayTask(long startTime, String name) {
        this.startTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(startTime);
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return startTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.startTime - ((DelayTask)o).startTime);
    }

    public long getStartTime() {
        return startTime;
    }

    public String getName() {
        return name;
    }
}
