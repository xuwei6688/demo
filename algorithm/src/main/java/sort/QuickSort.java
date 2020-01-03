package main.java.sort;

import main.java.utils.SwapUtil;

/**
 * 快速排序
 **/
public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static <T extends Comparable<T>> void quickSort(T[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(array, l, r);
        quickSort(array, l, p - 1);
        quickSort(array, p + 1, r);
    }

    //返回p，使得array[l,p-1] <= array[p] 并且 array[p+1] >= array[p]
    private static <T extends Comparable<T>> int partition(T[] array, int l, int r) {
        T v = array[l];

        //array[l+1,p-1] <= v   并且   array[p+1,i) >= v
        int p = l;
        //利用定义，p的初始值等于l，这样初始时比v小的数组就为空；i用开区间，并且初始值时p+1，这样初始时比v大的数组也为空
        for (int i = p + 1; i <= r; i++) {
            if (array[i].compareTo(v) < 0) {
                SwapUtil.swap(array, i, p + 1);
                p++;
            }
        }
        //最后交换v和array[j]
        SwapUtil.swap(array, l, p);
        return p;
    }
}
