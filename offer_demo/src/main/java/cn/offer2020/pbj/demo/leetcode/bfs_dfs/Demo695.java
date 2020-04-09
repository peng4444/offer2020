package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName: Demo695
 * @Author: pbj
 * @Date: 2020/4/9 14:20
 * @Description: TODO 695. 岛屿的最大面积
 */
public class Demo695 {
    //广度优先遍历(队列实现)
    public int maxAreaOfIsland2(int[][] grid) {
        Deque<int[]> queue = new LinkedList<>();

        int[][] moveIndexArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                queue.offer(new int[]{i, j});
                //计算最大面积
                int currMaxArea = 0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] poll = queue.poll();
                        int currI = poll[0];
                        int currJ = poll[1];
                        if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[0].length || grid[currI][currJ] == 0) {
                            continue;
                        }
                        currMaxArea++;
                        grid[currI][currJ] = 0;
                        for (int[] moveIndex : moveIndexArray) {
                            queue.offer(new int[]{currI + moveIndex[0], currJ + moveIndex[1]});
                        }
                    }
                }
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }

    //深度优先遍历(栈实现)
    public int maxAreaOfIsland1(int[][] grid) {
        Deque<int[]> stack = new LinkedList<>();

        int[][] moveIndexArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                stack.push(new int[]{i, j});
                //计算最大面积
                int currMaxArea = 0;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int currI = pop[0];
                    int currJ = pop[1];
                    if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[0].length || grid[currI][currJ] == 0) {
                        continue;
                    }
                    currMaxArea++;
                    grid[currI][currJ] = 0;
                    for (int[] moveIndex : moveIndexArray) {
                        stack.push(new int[]{currI + moveIndex[0], currJ + moveIndex[1]});
                    }
                }
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }


    //递归实现深度优先遍历
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //计算最大面积
                int currMaxArea = getMaxArea(i, j, grid);
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }

        return maxArea;
    }

    private int getMaxArea(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        //通过将经过的岛屿设置为0来确保下次不会重复访问
        grid[i][j] = 0;

        int upMaxArea = getMaxArea(i - 1, j, grid);

        int downMaxArea = getMaxArea(i + 1, j, grid);

        int leftMaxArea = getMaxArea(i, j - 1, grid);

        int rightMaxArea = getMaxArea(i, j + 1, grid);

        return upMaxArea + downMaxArea + leftMaxArea + rightMaxArea + 1;
    }
}
