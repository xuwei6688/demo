package main.java.spread;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ClimbStairs {
    Map<Integer, Integer> cache = new HashMap<>();
    /**
     * 数学归纳
     * n = 1：1
     * n = 2：1；2
     * n = 3：从第一级台阶爬两层到达第三层；或者从第二级台阶爬一层到达第三层
     * n = 4：从第二级台阶爬两层到达提四层；或者从第三季台阶爬一层到达第四层
     * f(n) = f(n-2) + f(n-1)
     */
    public  int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        Integer s1 = cache.get(n - 2);
        if (s1 == null) {
            s1 = climbStairs(n - 2);
            cache.put(n - 2, s1);
        }
        Integer s2 = cache.get(n - 1);
        if (s2 == null) {
            s2 = climbStairs(n - 1);
            cache.put(n - 1, s2);
        }

        return s1 + s2;
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int i = climbStairs.climbStairs(3);
        System.out.println(i);
    }
}
