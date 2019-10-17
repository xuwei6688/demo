package memorizer;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 因为计算的过程比较耗时间，所以对结果进行缓存
 * @Author xuwei
 * @Date 2019/10/15 0015
 * @Version V1.0
 **/
public class Memorizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private Computable<A, V> computable;

    public Memorizer1(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
