package cn.offer2020.pbj.demo.leetcode.ainputoutput;

/**
 * @ClassName: Main
 * @Author: pbj
 * @Date: 2020/5/27 17:00
 * @Description: TODO >> [JAVA算法竞赛输入输出专题](https://blog.csdn.net/weixin_42292229/article/details/85220104)
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a=1, b=1;
        while (sc.hasNext()) {
            a = sc.nextInt();
            b = sc.nextInt();//读入一个整数
            double d = sc.nextDouble();//读入一个双精度浮点数
            long l = sc.nextLong();//读入一个长整型数
        }
        System.out.println(a + b);
    }

    public static void main1(String[] args) {
        int size = 3;
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[size];//使用new关键字来为其分配存储空间
        char ch = sc.next().charAt(0);//读入一个字符
        String str1 = sc.next();//读入一个字符串
        String str2 = sc.nextLine();//读入下一行
        char s1[] = sc.next().toCharArray();
        char s2[] = sc.nextLine().toCharArray();
    }

    public static void main2(String[] args) {//输出到文件中：
        Scanner sc = new Scanner(System.in);
        String str = sc.next();//读入一个字符串
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("E://result.txt")));
            bw.write(str);//str表示写入文件的内容
            bw.flush();//立刻将缓存区的数据写入数据流
            bw.close();//将BufferedWriter流关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

