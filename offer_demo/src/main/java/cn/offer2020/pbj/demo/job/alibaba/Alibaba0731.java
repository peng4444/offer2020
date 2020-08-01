package cn.offer2020.pbj.demo.job.alibaba;

import java.util.Scanner;

import static cn.offer2020.pbj.demo.job.alibaba.Alibaba0731.slice;

/**
 * @pClassName: Alibaba0731
 * @author: pengbingjiang
 * @create: 2020/8/1 10:37
 * @description: TODO
 */
public class Alibaba0731 {

    //小强是一个农场主，农场里有n头牛，每头牛有着独一无二的体重，每一头牛的颜色可能跟是m种颜色其中的一种，
    //小强带了一些牛（可能为0个）出来吃草。你需要回答出小强带出来的牛的组合一共有多少种可能？
    //注意：因为一头牛有自己的体重（没有两头牛体重相等），所以如果四头牛的体重分别是1,2,3,4，颜色分别是y1,y2,y3,y4和另一种方案：
    //四头牛的体重分别是1,2,3,4颜色分别是y1,y3,y2,y4即使两个方案的颜色的种类对应的数量是相同的，但是因为颜色对应的体重不同，所以是两个不同的方案。
    //由于方案书可能很大，请对1e9+7取模。
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            System.out.printf("%.0f\n",slice(m+1,n));
        }
    }

    public static double slice(int m, int n) {
        if(n==1) return m;
        double temp = slice(m, n / 2);
        return ((n%2==0?1:m)*temp*temp)%1000000007;
    }

    //小强最近在研究某个飘洋过海的游戏。
    //游戏可以看成一个n∗mn*mn∗m的方格图，从左上角(1,1)(1, 1)(1,1)到右下角的(n,m)(n, m)(n,m)有两种地面，CCC表示为陆地SSS表示海洋，
    //每次穿行只能到达上下左右四个格子之一，不能走到方格图之外。
    //在陆地之间穿行一格需要花费三点行动力，在海洋之间穿行一格需要花费2点行动力。
    //但是从陆地和从海洋到陆地穿行则需要5点行动力。
    //输入描述：
    //第一行输入两个整数n,m,qn, m, qn,m,q,表示方格图的大小和询问次数。
    //随后nnn行，每行mmm个元素每个元素为'C'或'S',详见样例。
    //随后q行每行四个数字bx,by,ex,eybx, by, ex, eybx,by,ex,ey，分别代表起点的坐标和终点的坐标。
    //输入：
    //4 4 2
    //CCCS
    //SSSS
    //CSCS
    //SSCC
    //1 1 3 4
    //3 1 1 3
    //
    //输出
    //13
    //14

    private static boolean[][] isVisit;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int n, m, q, bx, by, ex, ey, nextX, nextY, currVal, result;
    private static String[] input;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.next();
        }
        while (q-- > 0) {
            isVisit = new boolean[n][m];
            result = Integer.MAX_VALUE;
            bx = sc.nextInt();
            by = sc.nextInt();
            ex = sc.nextInt();
            ey = sc.nextInt();
            currVal = 0;
            BackTrace(bx, by);
            System.out.println(result);
        }
    }

    public static boolean isOk(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m && !isVisit[x][y];
    }

    public static void BackTrace(int x, int y) {
        if (currVal >= result) {
            return;
        }
        if (x == ex && y == ey) {
            if (currVal < result) {
                result = currVal;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            nextX = x + direction[i][0];
            nextY = y + direction[i][1];
            if (isOk(nextX, nextY)) {
                int add = 2;
                if (input[x].charAt(y) != input[nextX].charAt(nextY)) {
                    add = 5;
                } else if (input[nextX].charAt(nextY) == 'C') {
                    add = 3;
                }
                isVisit[x][y] = true;
                currVal += add;
                BackTrace(nextX, nextY);
                currVal -= add;
                isVisit[x][y] = false;
            }
        }
    }
}
