package juc;

import java.util.concurrent.*;

/**
 * BlockingQueue有take、put方法，这两个方法都是阻塞方法，可以用来构建生产者消费者问题。
 */
public class BlockingQueuedDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        ExecutorService executorService = Executors.newCachedThreadPool();

        //生产者
        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {

                    blockingQueue.put("产品");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        //消费者
        executorService.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {

                    String product = blockingQueue.take();
                    System.out.println(product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
