package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @pClassName: Demo547
 * @author: pengbingjiang
 * @create: 2020/8/28 16:44
 * @description: TODO 547.547. 朋友圈朋友圈
 */
public class Demo547 {
    public int findCircleNum(int[][] M) {
        if(M.length==0||M[0].length==0) return 0;
        /**
         使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
         **/
        boolean[] visited = new boolean[M.length];
        int sum = 0;
        for(int i = 0;i<M.length;i++){
            if(!visited[i]){
                dfs(M,visited,i);
                sum++;
            }
        }
        return sum;
    }

    private void dfs(int[][] m,boolean[] visited,int i){
        for(int j = 0;j<m.length;j++){
            if(m[i][j]==1&&!visited[j]){
                visited[j] = true;
                dfs(m,visited,j);
            }
        }
    }
    //广度优先遍历
    public class Solution {
        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            Queue< Integer > queue = new LinkedList< >();
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    queue.add(i);
                    while (!queue.isEmpty()) {
                        int s = queue.remove();
                        visited[s] = 1;
                        for (int j = 0; j < M.length; j++) {
                            if (M[s][j] == 1 && visited[j] == 0)
                                queue.add(j);
                        }
                    }
                    count++;
                }
            }
            return count;
        }
    }

    //并查集
    public class Solution1 {
        int find(int parent[], int i) {
            if (parent[i] == -1)
                return i;
            return find(parent, parent[i]);
        }

        void union(int parent[], int x, int y) {
            int xset = find(parent, x);
            int yset = find(parent, y);
            if (xset != yset)
                parent[xset] = yset;
        }
        public int findCircleNum(int[][] M) {
            int[] parent = new int[M.length];
            Arrays.fill(parent, -1);
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        union(parent, i, j);
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1)
                    count++;
            }
            return count;
        }
    }
}
