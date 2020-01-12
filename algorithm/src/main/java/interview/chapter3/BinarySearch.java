package main.java.interview.chapter3;

import com.sun.tools.javac.util.Assert;

/**
 * @Author xuwei
 * @Date 2020-01-01
 * @Version V1.0
 **/
public class BinarySearch {
    /**
     *
     * @param array 有序数组
     * @param target  待查找元素target
     */
    public static int search(int[] array, int target) {
        //在[l,r]中查找元素 target
        int l = 0;
        int r = array.length;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(target == array[mid]){
                return mid;
            }else if(target < array[mid]){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int [] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(BinarySearch.search(array, 999));
        for (int i = 0; i < array.length; i++) {
            int index = BinarySearch.search(array, i);
            if (index != i) {
                throw new IllegalArgumentException();
            }
        }
    }
}
