package main.java.spread;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 括号生成
 *
 *  数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *  示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class GenerateParenthesis {
    List<String> list = new ArrayList<>();
    public List<String> generateParenthesis( int n) {
        generate(0, 0, n , "");
        return list;
    }

    private void generate(int left, int right, int n,  String s) {
        if (left == n && right == n) {
            list.add(s);
            return;
        }
        if (left < n) {
            generate(left + 1, right, n, s + "(");
        }
        if (left > right) {
            generate(left, right + 1, n, s + ")");
        }
    }


    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        List<String> strings = g.generateParenthesis(3);
        System.out.println(strings);
    }
}
