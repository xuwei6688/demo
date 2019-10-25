package deadLock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock是可中断的，意思是如果给Thread一个中断信号，那么使用Lock作为锁的线程可以响应中断。
 * 而以synchronized作为锁的线程，如果线程因为获取锁争用阻塞，那么实际上它是走不到代码的，也就没法对中断做出响应
 **/
public class LockInterruptiblyDemo {
    private static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put("hah", "===");

        Thread thread = new Thread(() -> {
            try {
                new LockInterruptiblyDemo().printHello();
            } catch (InterruptedException e) {
                System.out.println("printHello抛出中断异常");
            }

        });
        thread.start();
        thread.interrupt();

    }

    public void printHello() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try {
            System.out.println(map);
        } finally {
            lock.unlock();
        }
    }
}
