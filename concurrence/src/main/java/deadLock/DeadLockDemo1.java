package deadLock;

import java.util.concurrent.TimeUnit;


public class DeadLockDemo1 {

    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();

        new Thread(() ->{
            synchronized (a) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("============");
                }
            }
        }).start();

        new Thread(() ->{
            synchronized (b) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println("************");
                }
            }
        }).start();
    }
}
