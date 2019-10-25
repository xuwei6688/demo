package juc;

import util.SleepUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author xuwei
 * @Date 2019/10/25 0025
 * @Version V1.0
 **/
public class FutureTaskDemo2 {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() ->{
            SleepUtil.sleep(2);
            return  "result";
        });
        new Thread(futureTask).start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    futureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }, "========" + i).start();
        }

    }
}
