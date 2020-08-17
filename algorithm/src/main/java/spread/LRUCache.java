package main.java.spread;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/8/3
 * @Version V1.0
 **/
public class LRUCache {
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;
    }
    private int capacity;
    private int size;
    private Map<Integer, DLinkedNode> cache;
    private DLinkedNode head;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();

        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.next = tail;
        tail.pre = head;
    }

    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    private void moveHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        DLinkedNode pre = tail.pre;
        removeNode(pre);
        return pre;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            node = new DLinkedNode();
            node.key = key;
            node.value = value;

            addNode(node);
            size++;
            cache.put(key, node);

            if (size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
            }
        }else{
            node.value = value;
            moveHead(node);
        }
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        moveHead(node);
        return node.value;

    }
}
