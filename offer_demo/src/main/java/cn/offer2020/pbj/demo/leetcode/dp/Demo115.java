package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @pClassName: Demo115
 * @author: pengbingjiang
 * @create: 2020/7/18 11:00
 * @description: TODO 115.不同的子序列
 * 给定一个字符串S和一个字符串T，计算在S的子序列中T出现的个数。
 */
public class Demo115 {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1];
        for(int i = 0;i<s.length()+1;i++){
            dp[0][i] = 1;
        }
        for(int i = 1;i<t.length()+1;i++){
            for(int j = i;j<s.length()+1;j++){
                if(t.charAt(i-1)==s.charAt(j-1)){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    /**
     * 二维换一维 严格按照二维的流程 参见上面矩阵 这个是15ms
     * @param s
     * @param t
     * @return
     */
    public int numDistinct2(String s, String t) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, 1);
        int pre = 1;
        //每行算一次
        for(int i = 0; i < t.length(); i++){
            //0-n算n+1次
            for(int j = 0; j <= s.length(); j++){
                //先保存dp[j]下次用
                int temp = dp[j];
                if(j == 0){
                    dp[j] = 0;
                }else {
                    if(t.charAt(i) == s.charAt(j-1)){
                        dp[j] = dp[j-1] + pre;
                    }else {
                        dp[j] = dp[j-1];
                    }
                }
                pre = temp;
            }
        }
        return dp[s.length()];
    }

    /**
     * 列主序 倒序计算 就不用保存临时值pre了
     * 可以按上图二维矩阵的顺序模仿一下 这个是11ms
     * @param s
     * @param t
     * @return
     */
    public int numDistinct3(String s, String t) {
        // dp[0]表示空串
        int[] dp = new int[t.length() + 1];
        // dp[0]永远是1，因为不管S多长，都只能找到一个空串，与T相等
        dp[0] = 1;

        for (int i = 0; i < s.length(); i++){
            for (int j = t.length() - 1; j >= 0; j--) {
                if (t.charAt(j) == s.charAt(i)) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[t.length()];
    }

    /**
     * 列主序 先构造字典 就不用遍历t了
     * 这样就优化成了答案上的2ms的了
     * @param s
     * @param t
     * @return
     */
    public int numDistinct4(String s, String t) {
        // dp[0]表示空串
        int[] dp = new int[t.length() + 1];
        // dp[0]永远是1，因为不管S多长，都只能找到一个空串，与T相等
        dp[0] = 1;

        //t的字典
        int[] map = new int[128];
        Arrays.fill(map, -1);

        //从尾部遍历的时候可以遍历 next类似链表 无重复值时为-1，
        //有重复时例如从rabbit的b开始索引在map[b] = 2 next[2] 指向下一个b的索引为3
        // for (int j = t.length() - 1; j >= 0; j--) {
        //     if (t.charAt(j) == s.charAt(i)) {
        //        dp[j + 1] += dp[j];
        //     }
        // }
        //这段代码的寻址就可以从map[s.charAt(i)] 找到索引j 在用next[j] 一直找和 s.charAt(i)相等的字符 其他的就可以跳过了
        //所以这个代码的优化 关键要理解 上面的一维倒算
        int[] nexts = new int[t.length()];
        for(int i = 0 ; i < t.length(); i++){
            int c = t.charAt(i);
            nexts[i] = map[c];
            map[c] = i;
        }

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            for(int j = map[c]; j >= 0; j = nexts[j]){
                dp[j + 1] += dp[j];
            }
        }
        return dp[t.length()];
    }
}
