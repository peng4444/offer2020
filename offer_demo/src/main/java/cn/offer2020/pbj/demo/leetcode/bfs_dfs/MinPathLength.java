package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: MinPathLength
 * @Author: pbj
 * @Date: 2020/4/9 11:22
 * @Description: TODO
 */
public class MinPathLength {

    //题目描述：1 表示可以经过某个位置，求解从 (0, 0) 位置到 (tr, tc) 位置的最短路径长度。
    public int minPathLength(int[][] grids, int tr, int tc) {
        final int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        final int m = grids.length,n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                grids[cr][cc] = 0;
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grids[nr][nc] == 0) {
                        continue;
                    }
                    if (nr == tr && nc == tc) {
                        return pathLength;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }
}
