package boundedBuffer;

import java.util.concurrent.TimeUnit;

/**
 * 对SleepBoundedBuffer的优化
 * 利用wait、notify代替轮询 + 休眠的模式
 * 这样做的好处是：利用等待通知机制，避免了轮询对cpu的消耗，并且提升了响应性。
 * 如果队列满了或队列为空不能添加或取出元素时，
 * 先通过调用wait方法，让当前线程在当前BoundedBuffer对象上等待，并且会释放锁
 * 当完成操作后唤醒其他线程，这时其他之前等待的线程会重新检查状态，如果满足条件就进行操作，如果不满足重新等待
 **/
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public BoundedBuffer(int capacity) {
        super(capacity);
    }
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
