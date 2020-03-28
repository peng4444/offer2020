package cn.offer2020.pbj.demo.leetcode.a_recursion;

/**
 * @ClassName: Demo1219
 * @Author: pbj
 * @Date: 2020/3/19 15:00
 * @Description: TODO 黄金矿工
 */
public class Demo1219 {
    private int ans = 0;

    public int getMaximumGold(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        boolean[][] vis = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid,i,j,0,vis);
                }
            }
        }
        return ans;
    }

    public void dfs(int[][] grid, int x, int y,int gold, boolean[][] vis) {
        if((x < 0 || x >= grid.length) || (y < 0 || y >= grid[0].length) || grid[x][y] == 0 || vis[x][y]){
            ans = Math.max(ans,gold);
            return;
        }
        gold += grid[x][y];
        vis[x][y] = true;
        dfs(grid,x + 1,y,gold,vis);
        dfs(grid,x - 1,y,gold,vis);
        dfs(grid,x,y + 1,gold,vis);
        dfs(grid,x,y - 1,gold,vis);
        vis[x][y] = false;
    }
}
