package juc;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 利用信号量实现一个阻塞的HashSet
 * 构造方法传入容器大小，当容器内元素满时add会阻塞
 * @Author xuwei
 * @Date 2019/10/15 0015
 * @Version V1.0
 **/
public class BoundedHashSet<E> {
    private final Set<E> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = new HashSet<>();
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(E e) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try{
            wasAdded = set.add(e);
            return wasAdded;
        }finally {
            if (!wasAdded) {
                semaphore.release();
            }
        }
    }

    @Override
    public String toString() {
        return "BoundedHashSet{" +
                "set=" + set +
                '}';
    }

    public boolean remove(E e) {
        boolean wasRemoved = set.remove(e);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<Object> bs = new BoundedHashSet<>(3);
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bs.remove(1);
        }).start();
        bs.add(1);
        System.out.println(bs);
        bs.add(2);
        System.out.println(bs);
        bs.add(3);
        System.out.println(bs);
        bs.add(4);
        System.out.println(bs);
    }
}
