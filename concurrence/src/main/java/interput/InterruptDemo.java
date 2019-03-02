package interput;

import java.util.concurrent.TimeUnit;

/**
 * thread.interrupt()
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().interrupt();
        System.out.println("stop 1??" + Thread.interrupted());
        System.out.println("stop 2??" + Thread.interrupted());


    }
}
