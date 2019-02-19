package singleton;

/**
 * 要点：
 * 私有构造方法
 * volatile修饰，防止指令重排
 * 双重校验锁
 */
public class LazySingleton {
    private volatile static LazySingleton instance;

    private LazySingleton() {
        new Thread().interrupt();
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
