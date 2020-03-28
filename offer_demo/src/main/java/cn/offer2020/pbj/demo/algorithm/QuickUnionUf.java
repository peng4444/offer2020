package cn.offer2020.pbj.demo.algorithm;

/**
 * @ClassName: QuickUnionUf
 * @Author: pbj
 * @Date: 2020/1/1 14:27
 * @Description: TODO 并查集 Java实现路径压缩
 */
public class QuickUnionUf {
    private  int[] roots;

    //初始化并查集，每个集合的root为自身
    public QuickUnionUf(int N) {
        roots = new int[N];
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
    }

    //查看节点的root
    private int findRoot(int i) {
        int root = i;
        while (root != roots[root]) {
            root = roots[root];
        }
        //进行路径压缩
        while (i != roots[i]) {
            int tmp = roots[i];
            roots[i] =root;
            i = tmp;
        }
        return root;
    }

    //判断是否有关联
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int qroot = findRoot(q);
        int proot = findRoot(p);
        roots[proot] = qroot;
    }
}
