package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 要点：
 * 私有构造方法
 * volatile修饰，防止指令重排
 * 双重校验锁
 */
public class LazySingleton {
    private volatile static LazySingleton instance;

    private LazySingleton() {
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

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?>[] constructors = LazySingleton.class.getDeclaredConstructors();
        Constructor<?> c = constructors[0];
        c.setAccessible(true);
        Object o = c.newInstance();
        Object o1 = c.newInstance();
        System.out.println(o == o1);


    }
}
