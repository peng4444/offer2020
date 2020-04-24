package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo1293
 * @Author: pbj
 * @Date: 2020/4/23 15:47
 * @Description: TODO  1293. 网格中的最短路径
 * 给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
 * 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
 */
public class Demo1293 {

    public int shortestPath(int[][] grid, int k) {
        int len = grid.length,col = grid[0].length;
        if (k >= len + col -3) return len+col-2;     //没有这句，超时
        boolean[][] visited = new boolean[len][col];  //避免dfs发生原路返回的情况
        int result = shortestPathDfs(grid,0,0,len,col,0,k,visited);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public int shortestPathDfs(int[][] grid,int i,int j,int row,int col,int covered,int k,boolean[][] visited) {
        if (i < 0 || i >= row || j < 0 || j >= col) return Integer.MAX_VALUE; //递归出口
        if (i == row-1 && j == col-1) return covered;   //递归出口，结果
        if (visited[i][j]) return Integer.MAX_VALUE; //递归出口

        if (grid[i][j] == 1) {
            if (k > 0) k--;   //k做出牺牲，让1变为0
            else return Integer.MAX_VALUE; //k已经为0了，但是此块为1，则是一条死路
        }

        visited[i][j] = true;  //记录这条路径上这个节点已经访问过

        //取4个方向上可能路径的最小值返回
        int minOf4Dicrect = Integer.MAX_VALUE;
        minOf4Dicrect = Math.min(minOf4Dicrect,shortestPathDfs(grid,i-1,j,row,col,covered+1,k,visited));
        minOf4Dicrect = Math.min(minOf4Dicrect,shortestPathDfs(grid,i+1,j,row,col,covered+1,k,visited));
        minOf4Dicrect = Math.min(minOf4Dicrect,shortestPathDfs(grid,i,j+1,row,col,covered+1,k,visited));
        minOf4Dicrect = Math.min(minOf4Dicrect,shortestPathDfs(grid,i,j-1,row,col,covered+1,k,visited));

        visited[i][j] = false; //回溯
        return minOf4Dicrect;
    }

    class Node {
        int row;
        int col;
        int oneCount;  // bfs过程中某一条路径到达该节点时的路径上的1的个数
        int pathLen;   // bfs过程中到达某一节点的路径的长度（即走过的步数）
        Node (int row, int col, int oneCount, int pathLen) {
            this.row = row;
            this.col = col;
            this.oneCount = oneCount;
            this.pathLen = pathLen;
        }
    }

    //1）其实本质是一个加上约束条件的BFS。这里的约束条件是，BFS过程中路径上1的个数不能超过k个。也就是说BFS过程中某一路径上1的个数超过k个，那么这条路径就不是合法的路径，遍历过程中发现的话，应该废弃掉，不应该继续延伸。
    //2）这里还需要注意的是，由于多条路径可能会走到同一个节点上，这时候之后当新路径上1的个数小于旧路径上1的个数的时候才需要重新加入队列中。我们想象一下，因为是BFS，所以后到达某一节点的新路径的长度肯定比旧路径的长度要长，如果此时新路径的1的个数又比旧路径的1的个数多，说明新路径没有比旧路径更优，不应该继续延伸。
    public int shortestPath2(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        LinkedList<Node> queue = new LinkedList<>();
        Node rootNode = new Node(0, 0, 0, 0);
        queue.add(rootNode);
        Node[][] visited = new Node[m][n];
        visited[0][0] = rootNode;
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            List<Node> nodeList = new ArrayList<>();
            while (!queue.isEmpty()) {
                nodeList.add(queue.removeFirst());
            }

            for (Node node : nodeList) {
                int row = node.row;
                int col = node.col;
                int oneCount = node.oneCount;
                int pathLen = node.pathLen;

                // 通过bfs到达终点，那么就是最短路径
                if (row == m - 1 && col == n - 1) {
                    return pathLen;
                }

                // 上下左右四个方向
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dx[i];
                    int nextCol = col + dy[i];
                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                        continue;
                    }

                    int curOneCount = grid[nextRow][nextCol] == 1 ? oneCount + 1 : oneCount;
                    if (curOneCount <= k) { // 这里判断是关键，也就是说进入队列的路径上的1的个数都必须<=k，这样就能消除
                        if (visited[nextRow][nextCol] == null || curOneCount < visited[nextRow][nextCol].oneCount) {
                            // curOneCount < visited[nextRow][nextCol].oneCount
                            // 这个判断条件是只有新路径的1的个数小于旧路径1的个数的时候，才需要加入到队列中。
                            Node nextNode = new Node(nextRow, nextCol, curOneCount, pathLen + 1);
                            queue.add(nextNode);
                            visited[nextRow][nextCol] = nextNode;
                        }
                    }
                }
            }
        }

        return -1;
    }
}
