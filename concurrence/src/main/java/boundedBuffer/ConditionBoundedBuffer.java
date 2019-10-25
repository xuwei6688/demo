package boundedBuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java并发编程实战14.3
 * Condition和和内置条件队列相似
 * 一个Condition和一个Lock关联在一起，就像一个条件队列和一个内置锁关联一样。
 * 与条件队列不同的式，对于每个Lock可以有多个Condition对象
 *
 **/
public class ConditionBoundedBuffer<V> {
    private final Lock lock = new ReentrantLock();
    //条件谓词 notFull(count < items.length)
    private final Condition notFull = lock.newCondition();
    //条件谓词 notEmpty(count > 0)
    private final Condition notEmpty = lock.newCondition();

    private V[] items;
    private int head,tail,count;

    public ConditionBoundedBuffer(int capacity) {
        items = (V[])new Object[capacity];
    }

    //阻塞并直到 notFull
    public void put(V v) throws InterruptedException {
        lock.lock();
        try{
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = v;
            if (++tail == items.length) {
                tail = 0;
            }
            count++;
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //阻塞直到 notEmpty
    public V take() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0) {
                notEmpty.await();
            }
            V v = items[head];
            items[head] = null;
            if (++head == items.length) {
                head = 0;
            }
            count--;
            notFull.signalAll();
            return v;
        }finally {
            lock.unlock();
        }
    }
}
