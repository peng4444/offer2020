package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo338
 * @Author: pbj
 * @Date: 2019/12/28 21:20
 * @Description: TODO 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 */
public class Demo338 {
    public int[] countBits(int num) {
        int[] ans = new int[num+1];
        for(int i = 0;i<=num;i++){
            ans[i] = helper(i);
        }
        return ans;
    }
    int helper(int n){
        int ans  = 0;
        while (n != 0) {
            ans++;
            n &= (n - 1);
        }
        return ans;
    }

    /* *
     * 功能描述: 动态规划 + 最高有效位 【通过】
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/28 21:20
     */
    public class Solution {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            int i = 0, b = 1;
            // [0, b) is calculated
            while (b <= num) {
                // generate [b, 2b) or [b, num) from [0, b)
                while(i < b && i + b <= num){
                    ans[i + b] = ans[i] + 1;
                    ++i;
                }
                i = 0;   // reset i
                b <<= 1; // b = 2b
            }
            return ans;
        }
    }

    /* *
     * 功能描述:动态规划 + 最低有效位 【通过】
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/28 21:21
     */
    public class Solution2 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
            return ans;
        }
    }

    /* *
     * 功能描述: 动态规划 + 最后设置位【通过】
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/28 21:22
     */
    public class Solution3 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i & (i - 1)] + 1;
            return ans;
        }
    }
}
