package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo51
 * @Author: pbj
 * @Date: 2019/12/25 21:41
 * @Description: TODO 52.N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击.
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 */
public class Demo52 {
    public int totalNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();//存储最终返回结果
        int[] arr = new int[n];//存储不能被放置的位置
        hepler(0,n,arr,ans);
        return ans.size();
    }

    public void hepler(int m,int n,int[] arr,List<List<String>> ans) {
        if (m == n) {
            List<String> list = new ArrayList<>();
            char[][] chars = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    chars[i][j] = '.';
                }
                chars[i][arr[i]] = 'Q';
                list.add(new String(chars[i]));
            }
            ans.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            arr[m] = i;
            if(isConflit(arr,m)){
                hepler(m+1,n,arr,ans);
            }
        }
    }

    public boolean isConflit(int[] arr, int m) {
        for (int i = 0; i < m; i++) {
            if (arr[i] == arr[m] || Math.abs(m - i) == Math.abs(arr[m] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /* *
     * 功能描述: 使用 bitmap 回溯
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/25 22:09
     */
    class Solution {
        public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
            /**
             row: 当前放置皇后的行号
             hills: 主对角线占据情况 [1 = 被占据，0 = 未被占据]
             next_row: 下一行被占据的情况 [1 = 被占据，0 = 未被占据]
             dales: 次对角线占据情况 [1 = 被占据，0 = 未被占据]
             count: 所有可行解的个数
             */

            // 棋盘所有的列都可放置，
            // 即，按位表示为 n 个 '1'
            // bin(cols) = 0b1111 (n = 4), bin(cols) = 0b111 (n = 3)
            // [1 = 可放置]
            int columns = (1 << n) - 1;

            if (row == n)   // 如果已经放置了 n 个皇后
                count++;  // 累加可行解
            else {
                // 当前行可用的列
                // ! 表示 0 和 1 的含义对于变量 hills, next_row and dales的含义是相反的
                // [1 = 未被占据，0 = 被占据]
                int free_columns = columns & ~(hills | next_row | dales);

                // 找到可以放置下一个皇后的列
                while (free_columns != 0) {
                    // free_columns 的第一个为 '1' 的位
                    // 在该列我们放置当前皇后
                    int curr_column = - free_columns & free_columns;

                    // 放置皇后
                    // 并且排除对应的列
                    free_columns ^= curr_column;

                    count = backtrack(row + 1,
                            (hills | curr_column) << 1,
                            next_row | curr_column,
                            (dales | curr_column) >> 1,
                            count, n);
                }
            }

            return count;
        }
        public int totalNQueens(int n) {
            return backtrack(0, 0, 0, 0, 0, n);
        }
    }
}
