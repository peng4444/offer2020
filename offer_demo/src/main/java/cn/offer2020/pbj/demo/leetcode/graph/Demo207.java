package cn.offer2020.pbj.demo.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @pClassName: Demo207
 * @author: pengbingjiang
 * @create: 2020/8/4 20:04
 * @description: TODO 207.课程表 210.课程表II 630.课程表III
 */
public class Demo207 {
    //bfs
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];
        int[] in = new int[numCourses];
        boolean[] visited = new boolean[numCourses];
        int x,y;
        for(int i = 0;i<prerequisites.length;i++){
            x = prerequisites[i][0];
            y = prerequisites[i][1];
            graph[x][y] = 1;
            in[y]+=1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i<numCourses;i++){
            if(in[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int i = queue.poll();
            count++;
            for(int j = 0;j<numCourses;j++){
                if(graph[i][j]==1){
                    graph[i][j] = 0;
                    in[j]-=1;
                    if(in[j]==0){
                        queue.offer(j);
                    }
                }
            }
        }
        return count==numCourses;
    }

    //dfs
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }


}
