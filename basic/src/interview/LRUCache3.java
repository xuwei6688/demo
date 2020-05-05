package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/5/5
 * @Version V1.0
 **/
public class LRUCache3<K,V> {
    private Map<K, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    class Node{
        K key;
        V value;
        Node pre;
        Node next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    LRUCache3(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    /**
     * key在map中存在，更新value，将key对应的node移动到链表尾部
     * key在map中不存在：
     * 1.map元素个数小于容量，直接放入map，将key对应的node移动到链表尾部
     * 2.map元素等于容量，删除链表头，并且从map中删除对应的key，value；最后将本次的key，value存入map，将key添加到链表尾部
     */
    public void put(K key, V value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            removeToTail(node);
            return;
        }
        node = new Node(key, value);
        if (map.size() < capacity) {
            map.put(key, node);
            addTail(node);
        }else{
            Node headNode = removeHead();
            map.remove(headNode.key);
            map.put(key, node);
            addTail(node);
        }
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        removeToTail(node);
        return node.value;
    }


    public void addTail(Node newNode) {
        if (newNode == null) {
            return;
        }

        if (head == null) {
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
    }

    public Node removeHead() {
        if (head == null) {
            return null;
        }
        Node node = head;
        if (head == tail) {
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.pre = null;
        }
        return node;
    }

    public void removeToTail(Node node) {
        if (node == null) {
            return;
        }
        if (head == node) {
            head = head.next;
            head.pre = null;
        }else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        tail.next = node;
        node.pre = tail;
        node.next = null;
        tail = node;
    }

    @Override
    public String toString() {
        return  map.toString();
    }
}
