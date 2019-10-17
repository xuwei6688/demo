package memorizer;

import juc.FutureTaskDemo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Memorizer2 在取缓存和设置缓存不是原子操作，高并发情况下仍有可能对同一个入参多个线程同时创建FutureTask
 *
 **/
public class Memorizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        FutureTask<V> ft = new FutureTask<>(() -> computable.compute(arg));
        Future<V> f = cache.putIfAbsent(arg, ft);
        if (f == null) {
            //只在当前没有其他线程计算时启动
            ft.run();
            f = ft;
        }
        try {
           return  f.get();
        } catch (ExecutionException e) {
            throw FutureTaskDemo.launderException(e);
        }
    }
}
