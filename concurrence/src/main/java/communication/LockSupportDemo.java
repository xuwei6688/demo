package communication;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author xuwei
 * @Date 2020/5/24
 * @Version V1.0
 **/
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 =====");
            LockSupport.park();
            System.out.println("thread1 =====");
        });
        LockSupport.unpark(thread1);
        thread1.start();
//        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(thread1);
    }
}
