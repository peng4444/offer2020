package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @pClassName: Demo329
 * @author: pengbingjiang
 * @create: 2020/7/26 11:16
 * @description: TODO 329.矩阵中的最长递增路径
 */
public class Demo329 {
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][] cache;
    int m;
    int n;
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        if(m==0) return 0;
        n = matrix[0].length;
        cache = new int[m][n];
        int res = 1;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                res = Math.max(res,dfs(matrix,i,j));
            }
        }
        return res;
    }

    public int dfs(int[][] matrix,int x,int y){
        if(cache[x][y]!=0){
            return cache[x][y];
        }
        if(x>=m||x<0||y>=n||y<0){
            return 0;
        }
        int res = 1;
        for(int[] dir:dirs){
            int new_x = x + dir[0];
            int new_y = y + dir[1];
            if(new_x<m&&new_x>=0&&new_y>=0&&new_y<n&&matrix[new_x][new_y]>matrix[x][y]){
                res = Math.max(res,1+dfs(matrix,new_x,new_y));
            }
        }
        cache[x][y] = res;
        return res;
    }

    class Solution {
        private int longest; // 在遍历过程中记录每个点递增能到达的最长路径的最大值，可以省去最后对max数组遍历取最大；
        private final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 方向数组，路径问题必备

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return 0;
            int m = matrix.length, n = matrix[0].length, count = 0;
            int[] max = new int[m * n];// 用二维数组也可以，没太大区别，占用空间都是m * n
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (max[i * n + j] == 0) { // 对于已经访问并记录了最大递增路径的节点，直接跳过；
                        _longestIncreasingPath(matrix, max, i, j, m, n);
                    }
                }
            }
            return longest;
        }

        // 找到（x, y）节点能访问到的最大路径，期间记录到max中；
        private int _longestIncreasingPath(int[][] matrix, int[] max, int x, int y, int m, int n) {
            //terminator 遇到有记录过的最大路径 直接返回路径
            int index = x * n + y;
            if (max[index] != 0) return max[index];
            max[index] = 1; // 未记录过的位置，能访问到的路径至少为1
            for (int[] dir : directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                // 关键：只找递增的路径，进行下探（因为只找的递增，所以保证了不会往回跑）
                if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && matrix[x][y] < matrix[nextX][nextY]) {
                    max[index] = Math.max(max[index], _longestIncreasingPath(matrix, max, nextX, nextY, m, n) + 1) ;
                }
            }
            longest = Math.max(longest, max[index]);
            return max[index];
        }

    }

    class Solution1 {
        int N=21000,M=4*N,idx=0;
        int[] h=new int[N],ne=new int[M],e=new int[M];
        void add(int a,int b){
            e[idx]=b;
            ne[idx]=h[a];
            h[a]=idx++;
        }
        int[] dx=new int[]{-1,0,1,0};
        int[] dy=new int[]{0,1,0,-1};
        int topsort(int[] din,int n,int m){
            int[] dist=new int[n*m];
            Arrays.fill(dist,0);
            Queue<Integer> q=new LinkedList<>();
            for(int i=0;i<n*m;i++)
                if(din[i]==0){
                    q.add(i);
                    dist[i]=1;
                }
            while(!q.isEmpty()){
                int t=q.poll();
                for(int i=h[t];i!=-1;i=ne[i]){
                    int j=e[i];
                    if(dist[j]<dist[t]+1) dist[j]=dist[t]+1;
                    din[j]--;
                    if(din[j]==0) q.add(j);
                }
            }
            int res=0;
            for(int i=0;i<n*m;i++) res=Math.max(res,dist[i]);
            return res;
        }
        public int longestIncreasingPath(int[][] matrix) {
            if(matrix==null||matrix.length<1) return 0;
            idx=0;
            int n=matrix.length;
            int m=matrix[0].length;
            int[] din=new int[N];
            Arrays.fill(h,-1);
            for(int i=0;i<matrix.length;i++)
                for(int j=0;j<matrix[0].length;j++)
                    for(int k=0;k<4;k++){
                        int a=i+dx[k];
                        int b=j+dy[k];
                        if(a<0||a>=n||b<0||b>=m) continue;
                        if(matrix[a][b]>matrix[i][j]){
                            add(i*m+j,a*m+b);
                            din[a*m+b]++;
                        }

                    }
            int res=topsort(din,n,m);
            return res;

        }
    }
}
