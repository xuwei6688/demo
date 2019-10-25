package boundedBuffer;

import util.SleepUtil;

import java.util.concurrent.TimeUnit;

/**
 *  通过轮询和休眠来实现的阻塞队列
 *  java并发编程实战 14.1.2
 **/
public class SleepBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public SleepBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
