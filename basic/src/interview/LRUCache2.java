package interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 使用 HashMap + LinkedList 实现 LRU，其中HashMap实际存放key，value，
 * LinkedList存放key，链表尾部的元素是最近访问元素，链表头部的元素是最久未访问的元素
 **/
public class LRUCache2<K, V> {
    private int capacity;
    private LinkedList<K> linkedList;
    private Map<K, V> map;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        this.linkedList = new LinkedList<>();
        this.map = new HashMap<>();
    }

    /**
     * 如果key在map中存在，就更新map中的value，并且将LinkedList中的key移动到末尾；
     * 如果key在map中不存在，分两种情况：
     * 1.cache未达到最大容量
     * 2.cache达到最大容量
     */
    public void put(K key, V value) {
        V v = map.get(key);
        if (v != null) {
            map.put(key, value);
            linkedList.remove(key);
            linkedList.addLast(key);
            return;
        }

        if (map.size() < capacity) {
            map.put(key, value);
            linkedList.add(key);
        }else {
            K oldestKey = linkedList.removeFirst();
            map.remove(oldestKey);
            map.put(key, value);
            linkedList.addLast(key);
        }
    }

    public V get(K key) {
        V v = map.get(key);
        if (v != null) {
            linkedList.remove(key);
            linkedList.addLast(key);
        }
        return v;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}

