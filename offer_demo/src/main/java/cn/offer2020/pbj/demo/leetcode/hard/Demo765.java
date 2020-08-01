package cn.offer2020.pbj.demo.leetcode.hard;

/**
 * @pClassName: Demo765
 * @author: pengbingjiang
 * @create: 2020/8/1 16:25
 * @description: TODO 765.情侣牵手
 */
public class Demo765 {
    //贪心
    public int minSwapsCouples(int[] row) {
        int res = 0;
        for(int i = 0;i<row.length;i+=2){
            int t = row[i]^1;
            if(row[i+1]==t){
                continue;
            }
            res++;
            for(int j = i+1;j<row.length;j++){
                if(row[j]==t){
                    if(row[j]==t){
                        row[j] = row[i+1];
                        row[i+1] = t;
                        break;
                    }
                }
            }
        }
        return res;
    }

    //并查集
    private int[] parent;
    public int minSwapsCouples1(int[] row) {
        int n = row.length;
        parent = new int[n];
        for (int i = 0; i < n; i += 2) {
            parent[i] = i;
            parent[i + 1] = i;
        }
        for (int i = 0; i < n; i += 2) {
            union(row[i], row[i + 1]);
        }
        int res = n / 2;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                res--;
            }
        }
        return res;
    }

    private int find(int node) {
        return parent[node] == node ? node : (parent[node] = find(parent[node]));
    }

    private void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 == root2) {
            return;
        }
        parent[root1] = root2;
    }
}
