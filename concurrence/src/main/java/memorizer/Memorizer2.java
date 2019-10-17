package memorizer;

import juc.FutureTaskDemo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Memorizer1 可能有这样一种情况：
 * 线程A    cache.get(5) = null   compute(5)                cache.put
 * 线程B                               cache.get(5) = null           compute(5)           cache.put
 * 如上所示，对于同一个入参，线程A都要计算完成了，这是线程B也来了，线程B没有获取到缓存，于是也开始计算
 * 发生这种问题的原因是线程B不知道线程A正在计算，把cache的value替换为Future，
 **/
public class Memorizer2<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            FutureTask<V> ft = new FutureTask<>(() -> computable.compute(arg));
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }

        // 取缓存的值
        try {
            return  f.get();
        } catch (ExecutionException e) {
            throw FutureTaskDemo.launderException(e);
        }
    }
}
