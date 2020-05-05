package interview;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用 LinkedHashMap 实现 LRU，LinkedHashMap 默认的存储顺序是元素添加顺序。
 * 1.设置按照访问顺序存储，指定构造方法的 accessOrder = true , 存储顺序就按照访问顺序，
 * 最近被访问的元素就在前面，最久未被访问的元素就在后边。
 * 2.通过重写 removeEldestEntry 方法，指定移除最老元素的时机。
 **/
public class LRUCache1<K,V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public LRUCache1(int cacheSze) {
        super(cacheSze, 0.75f, true);
        MAX_CACHE_SIZE = cacheSze;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
