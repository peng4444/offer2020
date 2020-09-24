package cn.offer2020.pbj.demo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @pClassName: Demo0924
 * @author: pengbingjiang
 * @create: 2020/9/24 21:39
 * @description: TODO
 */
public class Demo0924 {
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[9][9];
        for(int i =0;i<9;i++){
            for(int j = 0;j<9;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        dfs(arr,0,0,nums);
        for(int i = 0;i<9;i++){
            for(int j =0;j<9;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean dfs(int[][] arr, int x, int y, int[] nums) {
        if(y>=arr[0].length) return dfs(arr,x+1,0,nums);
        if(x>=arr.length) return true;
        if(arr[x][y]!=-1) return dfs(arr, x, y + 1, nums);
        for (int num:nums) {
            if (check(arr, x, y, num)) {
                arr[x][y] = num;
                if (dfs(arr, x, y + 1, nums)) {
                    return true;
                }else{
                    arr[x][y] = -1;
                }
            }
        }
        return false;
    }

    public static boolean check(int[][] arr, int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if(arr[x][i]==num||arr[i][y]==num) return false;
        }

        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                if (arr[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[9][9];
        for(int i =0;i<9;i++){
            for(int j = 0;j<9;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        for(int i = 0;i<9;i++){
            for(int j =0;j<9;j++){
                if(arr[i][j]==0){
                    arr[i][j] = help(arr,i,j);
                }
            }
        }
        for(int i = 0;i<9;i++){
            for(int j =0;j<9;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int help(int[][] arr,int x,int y){
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i<9;i++){
            set.add(arr[x][i]);
            set.add(arr[i][y]);
        }
        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                set.add(arr[x][y]);
            }
        }
        for(int i = 1;i<=9;i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return 0;
    }
}
