package main.java.sort;

import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import main.java.utils.SortTestHelper;

import java.util.Arrays;

/**
 * @Author xuwei
 * @Date 2019-11-24
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        int n = 100000;
        Integer[] array = new Integer[n];
        SortTestHelper.makeArray(array, n);
        Integer[] array2 = Arrays.copyOf(array, array.length);



        //选择排序
        long startTime = System.currentTimeMillis();
        SelectionSort.sort(array);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime);
        System.out.println(SortTestHelper.isSorted(array));


        //插入排序
//        long startTime1 = System.currentTimeMillis();
//        InsertionSort.sort(array2);
//        long totalTime1 = System.currentTimeMillis() - startTime1;
//        System.out.println(totalTime1);
//
//        System.out.println(SortTestHelper.isSorted(array2));

        //归并排序
//        long startTime = System.currentTimeMillis();
//        MergeSort.sort(array, Integer.class);
//        long totalTime = System.currentTimeMillis() - startTime;
//        System.out.println(totalTime);
//        System.out.println(SortTestHelper.isSorted(array));

//        long startTime = System.currentTimeMillis();
//        QuickSort.sort(array);
//        long totalTime = System.currentTimeMillis() - startTime;
//        System.out.println(totalTime);
//        System.out.println(SortTestHelper.isSorted(array));
    }
}
