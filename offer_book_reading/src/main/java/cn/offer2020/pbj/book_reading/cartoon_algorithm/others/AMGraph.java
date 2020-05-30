package cn.offer2020.pbj.book_reading.cartoon_algorithm.others;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName: AMGraph
 * @Author: pbj
 * @Date: 2020/5/29 17:08
 * @Description: TODO
 */
public class AMGraph {
    public static void main(String[] args) {
        Graph g = new Graph(9, 11);
        g.create();
        for (int i = 0; i < g.vnum; i++) {
            for (int j = 0; j < g.vnum; j++) {
                //if(g.a[i][j]!=32767)
                System.out.print(g.a[i][j] + "    ");
            }
            System.out.println();
        }
        criticalPath(g);
    }
//		String[] s=topological(g);
//		System.out.println(Arrays.toString(s));

    //		int e[]=early(g);
//		System.err.println(Arrays.toString(e));
//
    //关键路径：
    public static void criticalPath(Graph g) {
        int[] ve = early(g);
        int[] vl = late(g);
        System.out.println(Arrays.toString(ve));
        System.out.println(Arrays.toString(vl));
        for (int i = 0; i < g.vnum; i++) {
            for (int j = 0; j < g.vnum; j++) {
                if (g.a[i][j] != 32767) {
                    if (ve[i] == vl[j] - g.a[i][j]) {
                        System.out.println("v" + i + " " + "v" + j);
                    }
                }
            }
        }
    }

    //每个事件的最迟发生时间：
    public static int[] late(Graph g) {
        int vl[] = new int[g.vnum];
        String[] s = topological(g);
        int n = g.vnum;
        for (int i = 0; i < vl.length; i++)
            vl[i] = early(g)[n - 1];
        String str = null;
        for (int i = n - 1; i >= 0; i--) {
            str = s[i].substring(1);
            int k = Integer.valueOf(str);
            for (int j = 0; j < n; j++) {
                if (g.a[j][k] != 32767) {
                    if (vl[j] > vl[i] - g.a[j][k]) {
                        vl[j] = vl[i] - g.a[j][k];
                    }
                }
            }
        }
        return vl;
    }

    //求每个事件的最早发生时间：
    public static int[] early(Graph g) {
        int[] ve = new int[g.vnum];
        String[] s = topological(g);
        for (int i = 0; i < g.vnum; i++)
            ve[i] = 0;
        String str = null;
        for (int i = 0; i < g.vnum; i++) {
            str = s[i].substring(1);
            int k = Integer.valueOf(str);
            for (int j = 0; j < g.vnum; j++) {
                if (g.a[k][j] != 32767) {
                    if (ve[j] < ve[k] + g.a[k][j]) {
                        ve[j] = ve[k] + g.a[k][j];
                    }
                    //	System.out.println(e[j]);
                }
            }
        }
        return ve;
    }

    //拓扑排序：
    public static String[] topological(Graph g) {
        int[] r = new int[g.vnum];
        String[] str = new String[g.vnum];
        int[][] a1 = new int[g.vnum][g.vnum];
        for (int i = 0; i < g.vnum; i++) {
            for (int j = 0; j < g.vnum; j++) {
                a1[i][j] = g.a[i][j];
            }
        }
        for (int i = 0; i < g.vnum; i++) {
            int k = 0;
            for (int j = 0; j < g.vnum; j++) {
                if (a1[j][i] < 32767) {
                    k++;
                }
            }
            r[i] = k;
        }
        String s1;
        Stack s = new Stack();
        int n = 0;
        while (n < g.vnum) {
            for (int i = 0; i < g.vnum; i++) {
                if (r[i] == 0) {
                    s1 = "v" + i;
                    int v = Integer.valueOf(i);
                    str[n++] = s1;
                    s.push(v);
                }
            }
            while (!s.isEmpty()) {
                int x = (int) s.pop();
                r[x] = 1;
                for (int i = 0; i < g.vnum; i++)
                    if (g.a[x][i] < 32767)
                        r[i]--;
            }
        }


        return str;
    }
}

