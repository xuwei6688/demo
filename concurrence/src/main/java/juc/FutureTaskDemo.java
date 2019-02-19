package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask实现了RunnableFuture，RunnableFuture继承了Runnable和Future。
 * 所以FutureTask既可以当做一个Runnable去执行，同时也可以异步获取执行结果。
 * 主线成可以把费时间同时又不影响主线程执行的操作放到FutureTask中执行，等主线程执行完成后调用FutureTask的get()方法获取返回。get是个阻塞方法。
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "hello";
            }
        });

        new Thread(futureTask).start();


        System.out.println("=============");
        System.out.println(futureTask.get());

    }
}
