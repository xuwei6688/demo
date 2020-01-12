package main.java.interview.chapter4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [9,4]
说明:

输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Intersection {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();//存放nums1中所有元素
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i])){
                set2.add(nums2[i]);
            }
        }
        int [] res = new int[set2.size()];
        int i =0;
        for (Integer j : set2) {
            res[i] = j;
            i++;
        }
        return res;
    }
    public static void main(String[] args) {
        int [] nums1 = {4,9,5};
        int [] nums2 = {9,4,9,8,4};
        int [] res = intersection(nums1, nums2);
        
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}