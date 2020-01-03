package juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author xuwei
 * @Date 2019/11/6 0006
 * @Version V1.0
 **/
public class AtomicLongDemo {
    private static  AtomicLong atomicLong = new AtomicLong();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService excutor = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> taskList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            taskList.add(()->{
                atomicLong.addAndGet(1);
               return null;
            });
        }
        excutor.invokeAll(taskList);
        System.out.println(atomicLong);
        excutor.shutdown();
    }
}
