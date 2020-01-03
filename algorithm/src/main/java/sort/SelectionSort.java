package main.java.sort;


/**
 * 选择排序 O(n^2)
 **/
public class SelectionSort {
   public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            //找到未排序数组的最小元素
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            //将未排序数组的第一个元素和最小元素交换
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
