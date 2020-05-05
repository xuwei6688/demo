package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xuwei
 * @Date 2020/5/5
 * @Version V1.0
 **/
class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    class Node{
        Integer key;
        Integer value;
        Node pre;
        Node next;
        Node(Integer key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    public Node removeHead(){
        if(head == null){
            return null;
        }
        Node node = head;
        if(head == tail){
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.pre = null;
        }
        return node;
    }

    public void addTail(Node newNode){
        if(newNode == null){
            return;
        }
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
    }

    public void moveToTail(Node node){
        if(node == null){
            return;
        }
        if (head == tail) {
            return;
        }
        if(head == node){
            head = head.next;
            head.pre = null;
        } else if (tail == node) {
            return;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        tail.next = node;
        node.pre = tail;
        node.next = null;
        tail = node;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null){
            return -1;
        }
        moveToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            moveToTail(node);
            node.value = value;
            return;
        }
        node = new Node(key, value);
        if(map.size() < capacity){
            map.put(key, node);
            addTail(node);
        }else{
            Node oldestNde = removeHead();
            map.remove(oldestNde.key);
            map.put(key, node);
            addTail(node);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
