package main.java.interview.chapter3;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MoveZeros {
    public static void move(int []array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
               continue;
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] == 0) {
                    continue;
                }
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                break;
            }
        }
    }

    public static void main(String[] args) {
        int [] array = {0, 1, 0, 3, 12};
        move(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
