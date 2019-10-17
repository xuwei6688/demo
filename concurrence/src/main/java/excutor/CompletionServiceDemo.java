package excutor;

import java.util.Random;
import java.util.concurrent.*;

/**
 *  CompletionService将Executor和BlockingQueue的功能融合在一起。可以将Callable任务提交给它执行，
 *  然后使用类似队列的take和poll来获取已完成的结果。而这个结果会被封装成Future。
 *  ExecutorCompletionService实现了CompletionService
 **/
public class CompletionServiceDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);


        //1.添加任务
        for (int i = 0; i < 10; i++) {
            completionService.submit(() ->{
                Random random = new Random();
                int sleepTime = random.nextInt(5);
                TimeUnit.SECONDS.sleep(sleepTime);
                return sleepTime;
            });
        }

        //2.查看任务执行结果
        for (int i = 0; i < 10; i++) {
            try {
                Future<Integer> future = completionService.take();
                Integer integer = future.get();
                System.out.println(integer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("haha");
    }
}
