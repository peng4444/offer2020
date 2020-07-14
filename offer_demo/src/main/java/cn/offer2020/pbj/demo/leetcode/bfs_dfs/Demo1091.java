package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @pClassName: Demo1091
 * @author: pengbingjiang
 * @create: 2020/7/14 19:25
 * @description: TODO 1091.二进制矩阵中的最短路径
 */
public class Demo1091 {

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1) return -1;
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if(curr[0]==n-1 && curr[1]==n-1) return step;
                for (int[] d : directions) {
                    int x = curr[0] + d[0];
                    int y = curr[1] + d[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 1) {
                        continue;
                    }
                    queue.add(new int[]{x, y});
                    grid[x][y] = 1;
                }
            }
            step++;
        }
        return -1;
    }
}
