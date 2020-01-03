package communication;

import util.SleepUtil;

/**
 * @Author xuwei
 * @Date 2019/11/13 0013
 * @Version V1.0
 **/
public class ThreadLocalDemo {
//    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("一");
        System.gc();
        SleepUtil.sleep(2);
        String s = threadLocal.get();
        System.out.println(s);
    }

    private void set(ThreadLocal<String> threadLocal) {
        threadLocal.set("一");
    }

    private String get(ThreadLocal<String> threadLocal) {
        return threadLocal.get();
    }
}
