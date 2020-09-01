package cn.offer2020.pbj.demo;

import java.util.*;
public class TestDemo{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] ans = new int[n][n];
        if(n%2==0){
            help4(n,ans);
        }else{
            help5(n,ans);
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void help4(int n,int[][] ans){
        ans[0][n/2] = 1;
        ans[0][n/2-1] = 2;
        ans[n/2-1][0] = 3;
        ans[n/2][0] = 4;
        ans[n-1][n/2-1] = 5;
        ans[n-1][n/2] = 6;
        ans[n/2][n-1] = 7;
        ans[n/2-1][n-1] = 8;
    }

    public static void help5(int n,int[][] ans){
        ans[0][n/2+1] = 1;
        ans[0][n/2-1] = 2;
        ans[n/2-1][0] = 3;
        ans[n/2+1][0] = 4;
        ans[n-1][n/2-1] = 5;
        ans[n-1][n/2+1] = 6;
        ans[n/2+1][n-1] = 7;
        ans[n/2-1][n-1] = 8;
    }
//}
//
//import java.util.*;
//public class TestDemo{
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int M = sc.nextInt();
//        int[] nums = new int[M];
//        for(int i = 0;i<M;i++){
//            nums[i] = sc.nextInt();
//        }
//        int ans = 0;
//        for(int i = 1;i<=N;i++){
//            for(int j = 0;j<M;j++){
//                if(i%nums[j]==0){
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//    }

    public static void help6(int n,int[][] ans){
        int half = n/2;
        for(int i=0;i<=half;i++){
            for(int j= 0;j<=half;j++){
                if(i<j){
                    ans[i][j] = 2;
                }else if(i>j){
                    ans[i][j] = 3;
                }
            }
        }

        for(int i=0;i<=half;i++){
            for(int j= half;j<n;j++){
                if(i+j<=5&&j!=5){
                    ans[i][j] = 1;
                }else if(i+j>5){
                    ans[i][j] = 8;
                }
            }
        }

        for(int i = half;i<=n;i++){
            for(int j = 0;j<=half;j++){
                if(i+j<=5&&i!=5){
                    ans[i][j] = 4;
                }else if(i+j>5){
                    ans[i][j] = 5;
                }
            }
        }

        for(int i = half;i<=n;i++){
            for(int j = half;j<=n;j++){
                if(i<j){
                    ans[i][j] = 7;
                }else if(i>j){
                    ans[i][j] = 6;
                }
            }
        }
    }
}