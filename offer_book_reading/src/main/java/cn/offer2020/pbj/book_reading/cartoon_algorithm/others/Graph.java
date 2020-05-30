package cn.offer2020.pbj.book_reading.cartoon_algorithm.others;

import java.util.Scanner;

/**
 * @ClassName: Graph
 * @Author: pbj
 * @Date: 2020/5/29 17:08
 * @Description: TODO 图
 */
public class Graph {
    final int  max=32767;
    final int num=100;
    String []s;
    int a[][];
    int vnum,anum;
    public Graph(int vnum, int anum) {
        super();
        this.vnum = vnum;
        this.anum = anum;
        s=new String[vnum];
        a=new int[vnum][vnum];
    }
    public void create() {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入顶点:");
        for(int i=0;i<vnum;i++)
            s[i]=scanner.next();
        for(int i=0;i<vnum;i++)
            for(int j=0;j<vnum;j++)
                a[i][j]=max;
        System.out.println("请输入边和权值:");
        int k;
        for(k=0;k<anum;k++) {
            int w;
            String s1=null;
            String s2=null;
            s1=scanner.next();
            s2=scanner.next();
            w=scanner.nextInt();

            int c=0,d = 0;
            for(int t=0;t<vnum;t++) {
                if(s[t].equals(s1)) {
                    c=t;
                }
                if(s[t].equals(s2))
                    d=t;
            }
            a[c][d]=w;
            if(k<anum-1)
                System.out.println("请输入边:");
        }
    }
}
