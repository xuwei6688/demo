package main.java.utils;

import java.util.Random;

/**
 * @Author xuwei
 * @Date 2019-11-24
 * @Version V1.0
 **/
public class SortTestHelper {

    public static <T> void printArray(T [] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void makeArray(Integer[] array, int bound) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(bound);
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
