package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author xuwei
 * @Date 2019/10/30 0030
 * @Version V1.0
 **/
public class LockTest {
    public static void createBusyThread() {
        new Thread(() ->{
            while (true);
        },"testBusyThread").start();
    }

    public static void createLockTread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        createBusyThread();
        reader.readLine();
        Object o = new Object();
        createLockTread(o);
    }
}
