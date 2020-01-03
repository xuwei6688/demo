package main.java.sort;

/**
 * 插入排序，时间复杂度O(n^2)
 * 每次把为排序数组的第一个元素，插入已排序数组中，插入的过程是从已排序数组的后边向前依次比较，到可插入位置停止
 * 相对选择排序，插入排序里层for循环是可以提前终止的，所以比选择排序更高效一些 PS:实际测试效果不如选择排序，应该是因为排序是每个元素都需要向后移动的关系
 **/
public class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            //记录下未排序数组的第一个元素
            T t = array[i];
            int j = i;
            //从未排序数组的第一个元素起向前查找，如果元素小于
            for (; j > 0 && t.compareTo(array[j - 1]) < 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = t;
        }
    }
}
