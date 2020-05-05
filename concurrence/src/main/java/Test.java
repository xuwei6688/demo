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
        Lock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.MICROSECONDS.sleep(1000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{

            //                lock.tryLock(5, TimeUnit.SECONDS);
            lock.lock();
            System.out.println("=======");
        }).start();

    }

}
