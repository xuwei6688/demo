package main.java.interview.chapter3;

/**
 * 209.长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MinSubArrayLen {
//    public int minSubArrayLen(int s, int[] nums) {
//        int minLength = nums.length + 1;//最小的子数组长度
//        int currentLength = 0;
//        int sum = 0;//子数组元素之和
//        int i = 0;
//        int j = 0;
//        for (; i < nums.length; ) {
//            if (sum < s) {
//                sum += nums[j];
//                currentLength = ++currentLength;
//                if (j < nums.length - 1) {
//                    j = j +1;
//                }
//            } else if (sum >= s) {
//                if (currentLength < minLength) {
//                    minLength = currentLength;
//                }
//                sum = sum - nums[i];
//                currentLength = currentLength - 1;
//                i = i + 1;
//                if (sum >= s && currentLength < minLength) {
//                    minLength = currentLength;
//                }
//            }
//        }
//        return minLength == nums.length ? 0 : minLength;
//    }

    public int minSubArrayLen(int s, int[] nums) {
        int res = nums.length + 1;
        int i =0, j = -1; //[i,j]的滑动窗口
        int sum = 0;
        while (i < nums.length) {
            if (j + 1 < nums.length && sum < s) {
                sum = sum + nums[++j];
            }else {
                sum = sum - nums[i++];
            }
            if (sum >= s) {
                res = Math.min(res, j - i + 1);
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }

    public static void main(String[] args) {
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int[] nums = {};
        int i = minSubArrayLen.minSubArrayLen(7, nums);
        System.out.println(i);
    }
}
