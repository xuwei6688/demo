package main.java.sort;

import java.lang.reflect.Array;

/**
 * 归并排序
 *
 */
public class MergeSort {
    public static <T extends Comparable<T>> void sort(T[] array, Class<T> tClass) {
        mergeSort(array, 0, array.length -1, tClass);
    }

    //对数组的[l,r]部分归并排序
    private static <T extends Comparable<T>> void mergeSort(T[] array, int l, int r, Class<T> tClass) {
        if (r <= l) {
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(array, l, mid, tClass);
        mergeSort(array, mid + 1, r, tClass);

        //合并操作
        merge(array, l, mid, r, tClass);
    }

    //对数组 [l,mid] 和[mid+1,r]合并
    private static <T extends Comparable<T>> void merge(T[] array, int l, int mid, int r, Class<T> tClass) {
        //创建一个辅助数组存放那两个排好序的数组
        T[] aux = (T[]) Array.newInstance(tClass, r -l +1);
        for (int i = l; i <= r; i++) {
            aux[i - l] = array[i];
        }

        int i = l, j = mid + 1;
        for (int k = l; k <=r ; k++) {
            //左边的数组已经没有元素了
            if (i > mid) {
                array[k] = aux[j - l];
                j++;
            }
            //右边的数组已经没有元素了
            else if (j > r) {
                array[k] = aux[i - l];
                i++;
            }
            //左右两倍安的数组都有元素，选择它们两个中较小的元素
            else if (aux[i - l].compareTo(aux[j - l]) < 0) {
                array[k] = aux[i - l];
                i++;
            }else {
                array[k] = aux[j - l];
                j++;
            }
        }

    }
}
