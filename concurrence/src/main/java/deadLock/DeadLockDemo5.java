package deadLock;

import util.SleepUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用tryLock避免死锁
 * tryLock会尝试获取锁，如果获取到返回true，获取不到返回false
 * 它不会重试，但至少不会阻塞了
 *
 * 偶尔输出是这样的 原因：上一个线程还没该状态，下一个线程就开始运行了
 * ==============
 * *************
 * *************
 **/
public class DeadLockDemo5 {

    public static void main(String[] args) throws InterruptedException {
        Lock a = new ReentrantLock();
        Lock b = new ReentrantLock();
        a.lockInterruptibly();

        RetryFlag f1 = new RetryFlag();
        while (f1.isRetry()) {
            new Thread(() ->{
                if (a.tryLock()) {
                    try{
                        SleepUtil.sleep(1);
                        if (b.tryLock()) {
                            try{
                                f1.setRetry(false);
                                System.out.println("==============");
                            }finally {
                                b.unlock();
                            }
                        }
                    }finally {
                        a.unlock();
                    }
                }
            }).start();
        }

        RetryFlag f2 = new RetryFlag();
        while (f2.isRetry()) {
            new Thread(() ->{
                if (b.tryLock()) {
                    try{
                        SleepUtil.sleep(1);
                        if (a.tryLock()) {
                            try{
                                f2.setRetry(false);
                                System.out.println("*************");
                            }finally {
                                a.unlock();
                            }
                        }
                    }finally {
                        b.unlock();
                    }
                }
            }).start();
        }
    }

}
class RetryFlag{
    boolean retry = true;

    public boolean isRetry() {
        return retry;
    }

    public void setRetry(boolean retry) {
        this.retry = retry;
    }
}
