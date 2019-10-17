package excutor;

import juc.FutureTaskDemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 单独使用FutureTask执行超时测试失败 TODO
 **/
public class FutureTimeoutDemo {
    public static void main(String[] args) {
       excutorTest();
    }

    public static void excutorTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer> future = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            return 10;
        });
        Integer number = 0;
        try {
            number = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw FutureTaskDemo.launderException(e);
        } catch (TimeoutException e) {
            number = 5;
            //表示任务线程在运行时可中断
            future.cancel(true);
        }
        System.out.println(number);
    }
}
