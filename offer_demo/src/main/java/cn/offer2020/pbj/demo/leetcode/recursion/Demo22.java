package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo22
 * @Author: pbj
 * @Date: 2019/12/22 19:37
 * @Description: TODO  22.括号生成
 */
public class Demo22 {
    /* *
     * 功能描述: 回溯法
     * 只有在我们知道序列仍然保持有效时才添加 '(' or ')'，而不是像 方法一 那样每次添加。
     * 我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     * 如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号。
     * 时间复杂度O(4^n /)
     * @param: [n]
     * @return: java.util.List<java.lang.String>
     * @auther: pbj
     * @date: 2019/12/22 19:53
     */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        generateOneByOne("", result, n, n);
        return result;
    }

    public void generateOneByOne(String subList, List<String> result, int left, int right) {
        if (left == 0 && right == 0) {// 左右括号都不剩余了，递归终止
            result.add(subList);
            return;
        }
        if (left > 0) {// 如果左括号还剩余的话，可以拼接左括号
            generateOneByOne(subList + "(", result, left - 1, right);
        }
        if (right > left) {// 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            generateOneByOne(subList + ")", result, left, right - 1);
        }
    }

    // 把结果集保存在动态规划的数组里
    public List<String> generateParenthesis3(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // 这里 dp 数组我们把它变成列表的样子，方便调用而已
        List<List<String>> dp = new ArrayList<>(n);
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);
        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        // 枚举右括号的位置
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }

    /* *
     * 功能描述: 闭合函数
     * 对于任何一个括号组合（1对以上），可以表达为这么一种组合 ( left ) right 的形式。
     * 其中，若left 和 right 两部分的括号总数为 n-1对，那么新的组合 ( left ) right 则有n对，
     * 这样将n对括号求解，转化为n-1对的求解，以此类推直到零对括号可以直接给出空字符串的解。
     * 以3对为例，可拆解为 “( left=0对）right=2对”“( left=1对）right=1对”“( left=2对）right=0对”。
     * 对于上述题解给出的方案，如果缓存中间组合，可以减少搜索次数。
     * @param: [n]
     * @return: java.util.List<java.lang.String>
     * @auther: pbj
     * @date: 2019/12/22 19:52
     */
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis1(c))
                    for (String right : generateParenthesis1(n - 1 - c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }


    /* *
     * 功能描述: 暴力法 生成所有的字符序列，再判断有效性
     * 时间复杂度O(2^2n N)
     * @param: [n]
     * @return: java.util.List<java.lang.String>
     * @auther: pbj
     * @date: 2019/12/22 19:49
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    //生成所有的可能序列
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    //判断生成括号是否有效
    public boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}
