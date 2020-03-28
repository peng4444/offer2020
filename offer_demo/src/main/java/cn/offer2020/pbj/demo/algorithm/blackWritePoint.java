package cn.offer2020.pbj.demo.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName: blackWritePoint
 * @Author: pbj
 * @Date: 2019/9/23 21:42
 * @Description: TODO 贪心算法之——黑白点的匹配（两种实现方法）
 */
public class blackWritePoint {
    /* *https://www.cnblogs.com/Unicron/p/11574652.html
     * 功能描述: 设平面上分布着n个白点和n个黑点，每个点用一对坐标（x, y）表示。
     * 一个黑点b=（xb,yb）支配一个白点w=(xw, yw)当且仅当xb>=xw和yb>=yw。
     * 若黑点b支配白点w，则黑点b和白点w可匹配（可形成一个匹配对）。
     * 在一个黑点最多只能与一个白点匹配，一个白点最多只能与一个黑点匹配的前提下，求n个白点和n个黑点的最大匹配对数。
     * @param:
     * @return: 
     * @auther: pbj
     * @date: 2019/9/23 21:42
     */
    class vertex{//点的坐标
        public int color;//白：0，黑：1
        public double Vx;
        public double Vy;
    }

    static int graph[][];//邻接表，默认全部为0
    static int n;//节点数
    static int visit[];//是否被访问
    static int matched[];//是否已经匹配，对应的匹配点
    static vertex V[];//存储所有的点的坐标

    public void Init() {
        System.out.println("输出的黑白点总数为：");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        graph = new int[n][n];//邻接表
        visit = new int[n];
        matched = new int[n];
        V = new vertex[n];
        InitGraph();//初始化邻接矩阵
    }

    public void InitGraph(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            V[i] = new vertex();
            System.out.println("please int color/x/y");
            V[i].color = scanner.nextInt();
            V[i].Vx = scanner.nextInt();
            V[i].Vy = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && (V[i].color == 1) && (V[j].color == 0) && (V[i].Vx > V[j].Vx) && (V[i].Vy > V[j].Vy)) {
                    graph[i][j] = 1;
                }
            }
        }
    }
    //显示匹配结果
    public void show(){
        Arrays.fill(visit, 0);
        for (int i = 0; i < n; i++) {
            if (visit[i] == 0) {
                if (matched[i] != -1) {
                    System.out.println("(" + V[i].Vx + "," + V[i].Vy + ")与" + "(" + V[matched[i]].Vx + ","+ V[matched[i]].Vy + ")" + "匹配");
                }
            }
        }
    }
    /* *
     * 功能描述: 基于DFS实现 params: x:起始的未匹配点 return: 1:找到增广路 0:未找到
     */
    public int dfs_solve(int x) {
        //找到一个和节点存在边的点，并且该点在本轮中没有被访问过
        for (int i = 0; i < n; i++) {
            if (graph[x][i] != 0 && visit[i] == 0) {
                visit[i] =1;//标记为匹配过
                // 按照交替路的模式找增广路，增广路相对于交替路的特性是就是，第一个节点和最后一个节点都是未匹配过的节点
                if (matched[i] == -1 || dfs_solve(matched[i]) == 1) { // 直接跳到matched[i]能够保证匹配边和未匹配边交替
                    // 说明找到了一个未匹配节点，将所有匹配边变为未匹配边，将所有未匹配边变为匹配边，这样匹配边数会加1,这个交换过程通过回溯实现
                    matched[x] = i;
                    matched[i] = x;
                    System.out.println("(" + V[x].Vx + "," + V[x].Vy + ") 与 " + "(" + V[i].Vx + "," + V[i].Vy + ")" + "匹配");
                    return 1;
                }
            }
        }
        return 0;
    }

    public void hungarian1(){
        Arrays.fill(matched,-1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (matched[i] == -1) {
                System.out.println("从 " + "(" + V[i].Vx + "," + V[i].Vy + ")" + " 开始匹配");
                Arrays.fill(visit, 0);// 重置浏览数组，用来浏览邻接矩阵当前列
                sum += dfs_solve(i);
            }
        }
        System.out.println("\n"+"最后共有 " + sum + " 匹配项");
        show();
    }
    public static void main(String[] args) {
        blackWritePoint mm = new  blackWritePoint();
        mm.Init();
        mm.hungarian1();
    }
}
