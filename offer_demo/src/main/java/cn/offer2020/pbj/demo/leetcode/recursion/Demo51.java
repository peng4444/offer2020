package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.*;

/**
 * @ClassName: Demo51
 * @Author: pbj
 * @Date: 2019/12/25 21:41
 * @Description: TODO 51.N皇后
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

    //DFS+位移
    class Solution {
        private List<List<String>> ans = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            solve(new ArrayList<String>(), n, 0, 0, 0);
            return ans;
        }

        private void solve(List<String> res, int n, int shu, int pia, int na) {
            if (res.size() == n) {
                ans.add(new ArrayList<>(res));
                return;
            }
            int cur = ((1 << n) - 1) & ~(shu | pia | na);
            while (cur != 0) {
                int p = cur & (-cur);
                char[] chars = new char[n];
                Arrays.fill(chars, '.');
                chars[Integer.toBinaryString(p).length() - 1] = 'Q';

                res.add(new String(chars));
                solve(res, n, shu | p, (pia | p) << 1, (na | p) >> 1);
                res.remove(res.size() - 1);

                cur = cur & (cur - 1);
            }
        }
    }

    public List<List<String>> solveNQueens2(int n) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> z_diagonal = new HashSet<>();
        Set<Integer> f_diagonal = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        backtrack(0, n, res, new ArrayList<String>(), col, z_diagonal, f_diagonal);
        return res;
    }

    private void backtrack(int i, int n, List<List<String>> res, ArrayList<String> tmp, Set<Integer> col, Set<Integer> z_diagonal, Set<Integer> f_diagonal) {
        if (i == n) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col.contains(j) && !z_diagonal.contains(i + j) && !f_diagonal.contains(i - j)) {
                col.add(j);
                z_diagonal.add(i + j);
                f_diagonal.add(i - j);
                char[] s = new char[n];
                Arrays.fill(s, '.');
                s[j] = 'Q';
                tmp.add(new String(s));
                backtrack(i+1,n,res,tmp,col,z_diagonal,f_diagonal);
                tmp.remove(tmp.size() - 1);
                col.remove(j);
                z_diagonal.remove(i + j);
                f_diagonal.remove(i - j);
            }
        }
    }
}
