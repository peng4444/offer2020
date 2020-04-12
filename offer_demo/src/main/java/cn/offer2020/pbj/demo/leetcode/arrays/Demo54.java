package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo54
 * @Author: pbj
 * @Date: 2020/1/5 11:22
 * @Description: TODO 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 */
public class Demo54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) {
            return ans;
        }
        int len_x = matrix.length;
        int len_y= matrix[0].length;
        int len = len_x * len_y;
        int start_x = 0, start_y = 0;
        while (ans.size() < len) {
            int x = start_x, y = start_y;
            for (; y < len_y && ans.size() < len; y++) {
                ans.add(matrix[x][y]);
            }
            y = y -1;
            for (x = x + 1; x < len_x && ans.size() < len; x++) {
                ans.add(matrix[x][y]);
            }
            x = x -1;
            for (y = y - 1; y >= start_y && ans.size() < len; y--) {
                ans.add(matrix[x][y]);
            }
            y = y+1;
            for (x = x - 1; x > start_x && ans.size() < len; x--) {
                ans.add(matrix[x][y]);
            }
            len_x--;
            len_y--;
            start_x++;
            start_y++;
        }
        return ans;
    }

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List ans = new ArrayList();
            if (matrix.length == 0) return ans;
            int R = matrix.length, C = matrix[0].length;
            boolean[][] seen = new boolean[R][C];
            int[] dr = {0, 1, 0, -1};
            int[] dc = {1, 0, -1, 0};
            int r = 0, c = 0, di = 0;
            for (int i = 0; i < R * C; i++) {
                ans.add(matrix[r][c]);
                seen[r][c] = true;
                int cr = r + dr[di];
                int cc = c + dc[di];
                if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]){
                    r = cr;
                    c = cc;
                } else {
                    di = (di + 1) % 4;
                    r += dr[di];
                    c += dc[di];
                }
            }
            return ans;
        }
    }
}
