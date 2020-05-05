package interview;

/**
 * @Author xuwei
 * @Date 2020/5/5
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
//        LRUCache1<String, String> cache = new LRUCache1<>(3);
//        LRUCache2<String, String> cache = new LRUCache2<>(3);
        LRUCache3<String, String> cache = new LRUCache3<>(3);
        cache.put("一", "1");
        cache.put("二", "2");
        cache.put("三", "3");
        cache.get("一");
        cache.get("三");
        cache.put("四", "4");

        System.out.println(cache);
    }
}
