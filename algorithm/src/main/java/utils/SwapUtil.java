package main.java.utils;

/**
 * @Author xuwei
 * @Date 2019-11-24
 * @Version V1.0
 **/
public class SwapUtil {
    public static <T> void swap(T[] array, int i, int j) {
        T t = array[j];
        array[j] = array[i];
        array[i] = t;
    }
}
