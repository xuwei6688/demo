package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java并发编程实战14.4.4
 * 通过锁实现信号量
 **/
public class SemphareOnLock {
    private final Lock lock = new ReentrantLock();

    //条件谓词：permitsAvailable（permits > 0）
    private final Condition permitsAvailable = lock.newCondition();
    private int permits;

    public SemphareOnLock(int permits) {
        //TODO 这里为什么需要加锁？
        lock.lock();
        try{
            this.permits = permits;
        }finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try{
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        }finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try{
            ++permits;
            permitsAvailable.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
