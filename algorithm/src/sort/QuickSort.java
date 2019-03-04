package sort;

import java.util.Random;

public class QuickSort {
    private static int number[];

    public static void quickSort(int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int j = right;
        //存放基数
        int temp = number[i];

        while (i != j) {
            //左边第一个比基数大的数的下标
            if(number[i] <= temp && i < j){
                i++;
            }

            //找到右边第一个比基数小的数的下标
            if (number[j] >= temp && i < j){
                j--;
            }

            //两数交换
            int t = number[i];
            number[i] = number[j];
            number[j] = t;
        }
        //这时数组左边的数字都比基数小，右边的数字都比基数大，但是基数还在最左边，把基数和i位置元素交换位置
        number[left] = number[i];
        number[i] = temp;

        //把数组拆分两段，重复上边的操作
        quickSort(left, j -1);
        quickSort(j + 1, right);
    }

    public static void main(String[] args) {
        Random random = new Random();
        number = new int[10_0000];
        for(int i = 0; i < number.length; i++){
            number[i] = random.nextInt(10000);
        }
        long startTime = System.currentTimeMillis();
        quickSort(0, number.length - 1);

        System.out.println("结束时间:" + (System.currentTimeMillis() - startTime));
        for(int i = 1; i < 100; i++){
            System.out.println(number[i * 1000]);
        }
    }

}
