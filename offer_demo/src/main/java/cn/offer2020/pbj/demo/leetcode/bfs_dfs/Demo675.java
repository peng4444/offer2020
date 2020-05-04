package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.*;

/**
 * @ClassName: Demo675
 * @Author: pbj
 * @Date: 2020/5/4 14:29
 * @Description: TODO 675. 为高尔夫比赛砍树
 */
public class Demo675 {
    //宽度优先搜索(BFS)
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int val = forest.get(i).get(j);
                if (val > 1) {
                    trees.add(new int[]{val, i, j});
                }
            }
        }
        Collections.sort(trees,(a,b)->Integer.compare(a[0],b[0]));
        int ans = 0, sr = 0, sc = 0;
        for (int[] tree : trees) {
            int d = bfs(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) {
                return -1;
            }
            ans += d;
            sr = tree[1];
            sc = tree[2];
        }
        return ans;
    }

    public int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];
            for (int di = 0; di < 4; ++di) {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }
        }
        return -1;
    }

    //A*搜索
    public int cutOffTree1(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (a, b) -> Integer.compare(a[0], b[0]));
        heap.offer(new int[]{0, 0, sr, sc});

        HashMap<Integer, Integer> cost = new HashMap();
        cost.put(sr * C + sc, 0);

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int g = cur[1], r = cur[2], c = cur[3];
            if (r == tr && c == tc) return g;
            for (int di = 0; di < 4; ++di) {
                int nr = r + dr[di], nc = c + dc[di];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                    int ncost = g + 1 + Math.abs(nr-tr) + Math.abs(nc-tr);
                    if (ncost < cost.getOrDefault(nr * C + nc, 9999)) {
                        cost.put(nr * C + nc, ncost);
                        heap.offer(new int[]{ncost, g+1, nr, nc});
                    }
                }
            }
        }
        return -1;
    }

    //Hadlock 算法
    public int hadlocks(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Set<Integer> processed = new HashSet();
        Deque<int[]> deque = new ArrayDeque();
        deque.offerFirst(new int[]{0, sr, sc});
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int detours = cur[0], r = cur[1], c = cur[2];
            if (!processed.contains(r*C + c)) {
                processed.add(r*C + c);
                if (r == tr && c == tc) {
                    return Math.abs(sr-tr) + Math.abs(sc-tc) + 2 * detours;
                }
                for (int di = 0; di < 4; ++di) {
                    int nr = r + dr[di];
                    int nc = c + dc[di];
                    boolean closer;
                    if (di <= 1) closer = di == 0 ? r > tr : r < tr;
                    else closer = di == 2 ? c > tc : c < tc;
                    if (0 <= nr && nr < R && 0 <= nc && nc < C && forest.get(nr).get(nc) > 0) {
                        if (closer) deque.offerFirst(new int[]{detours, nr, nc});
                        else deque.offerLast(new int[]{detours+1, nr, nc});
                    }
                }
            }
        }
        return -1;
    }

}
