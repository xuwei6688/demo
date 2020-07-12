import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xuwei
 * @Date 2019/12/20 0020
 * @Version V1.0
 **/
public class Test {
    private List<String> list = new ArrayList<>();

    public void add(String str) {
        list.add(str);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(134217728 / 1024 / 1024);
    }

}
