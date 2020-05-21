package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo885
 * @Author: pbj
 * @Date: 2020/5/21 10:56
 * @Description: TODO 885. 螺旋矩阵 III
 */
public class Demo885 {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] ans = new int[R*C][2];
        int index = 0;
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        ans[index++] = new int[]{r0, c0};
        int cnt = 2;
        int count = cnt /2;
        while (index != R * C) {
            int dirindex = (cnt+2) % 4;
            r0 += dr[dirindex];
            c0 += dc[dirindex];
            if (0 <= r0 && r0 < R && 0 <= c0 && c0 < C) {
                ans[index][0] = r0;
                ans[index][1] = c0;
                index++;
            }
            count--;
            if(count==0) count = (++cnt)/2;
        }
        return ans;
    }
}
