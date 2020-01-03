import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xuwei
 * @Date 2019/12/20 0020
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                1L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(()->{
//                System.out.println("hello");
//            });
//
//        }
//        executorService.allowCoreThreadTimeOut(true);
//        executorService.shutdown();
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

    }
}
