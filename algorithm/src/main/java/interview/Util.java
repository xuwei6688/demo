package main.java.interview;

/**
 * @Author xuwei
 * @Date 2020-01-01
 * @Version V1.0
 **/
public class Util {

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
    }
}
