package cn.offer2020.pbj.demo;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @pClassName: Demo
 * @author: pengbingjiang
 * @create: 2020/8/19 11:27
 * @description: TODO
 */
public class Demo {

    //    public static void main(String[] args) {
//        String str1 = "通话";
//        String str2 = "重地";
//        System.out.println(String.format("str1:%d|str2:%d",str1.hashCode(),str2.hashCode()));
//        //str1:1179395|str2:1179395
//        System.out.println(str1.equals(str2));
//        //false
//    }
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int Y = sc.nextInt();
//        int M = sc.nextInt();
//        int D = sc.nextInt();
//        System.out.println(help(Y,M)+D);
//    }

    private static int help(int y,int m){
        int sum = 0;
        int[] months;
        if(checkY(y)){
            months = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        }else{
            months = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        }
        for(int i = 0;i<m-1;i++){
            sum+=months[i];
        }
        return sum;
    }

    private static boolean checkY(int y){
        if((y%4==0&&y%100!=0)||(y%400==0)) return true;
        else return false;
    }

    public static void main(String[] args) {
        int m = 5;
        int[] arr = new int[]{4,1,2,5,3};
        System.out.println(m-demo1(m,arr));
    }

    public static int demo1(int m,int[] arr) {
        int[] dp = new int[m + 1];
        int res = 0;
        for(int num : arr) {
            dp[num] = dp[num - 1] + 1;
            res = Math.max(dp[num], res);
        }
        return res;
    }
}
