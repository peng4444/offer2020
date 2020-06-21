package cn.offer2020.pbj.demo.leetcode.graph;

import java.util.Arrays;
import java.util.Stack;
//785. 判断二分图
public class Demo785 {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors,-1);
        for(int i = 0;i<graph.length;i++){
            if(colors[i]==-1&&!isBitpartite(i,0,colors,graph)){
                return false;
            }
        }
        return true;
    }
    private boolean isBitpartite(int curNode,int curColor,int[] colors,int[][] graph){
        if(colors[curNode]!=-1){
            return colors[curNode] == curColor;
        }
        colors[curNode] = curColor;
        for(int next:graph[curNode]){
            if(!isBitpartite(next,1-curColor,colors,graph)){
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite2(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色， 1黑  2白
        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++) {
            // lastColor为0
            if (!dfs(graph, i, colors, 0)) return false;
        }
        return true;
    }
    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 注意，被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[i] != 0) return colors[i] != lastColor;
        // 未被染色，染成与相邻结点不同的颜色（lastColor为0时，就染成1）
        colors[i] = lastColor == 1 ? 2 : 1;
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i])) return false;
        }
        return true;
    }

    //深度优先搜索着色
    class Solution {
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            int[] color = new int[n];
            Arrays.fill(color, -1);

            for (int start = 0; start < n; ++start) {
                if (color[start] == -1) {
                    Stack<Integer> stack = new Stack();
                    stack.push(start);
                    color[start] = 0;

                    while (!stack.empty()) {
                        Integer node = stack.pop();
                        for (int nei: graph[node]) {
                            if (color[nei] == -1) {
                                stack.push(nei);
                                color[nei] = color[node] ^ 1;
                            } else if (color[nei] == color[node]) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
