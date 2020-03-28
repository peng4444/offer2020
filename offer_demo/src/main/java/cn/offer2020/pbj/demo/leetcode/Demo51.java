package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo51
 * @Author: pbj
 * @Date: 2019/12/25 21:41
 * @Description: TODO N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击.
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 */
public class Demo51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();//存储最终返回结果
        int[] arr = new int[n];//存储不能被放置的位置
        hepler(0,n,arr,ans);
        return ans;
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
}
