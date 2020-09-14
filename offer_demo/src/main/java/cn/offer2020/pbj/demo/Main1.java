package cn.offer2020.pbj.demo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @pClassName: Main1
 * @author: pengbingjiang
 * @create: 2020/9/12 20:31
 * @description: TODO
 */
public class Main1 {
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int starti = sc.nextInt();
        int startj = sc.nextInt();
        int endi = sc.nextInt();
        int endj = sc.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }
        char[][] chars = new char[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                chars[i][j] = strs[i].charAt(j);
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chars = str.toCharArray();
        int left = 0,right = chars.length-1;
        int removeIndex = -1;
        while(left<right){
            if(chars[left]!=chars[right]){
                if(help(chars,left,right-1)||help(chars,left+1,right)){
                    if(help(chars,left,right-1)){
                        removeIndex = left;
                    }else{
                        removeIndex = right;
                    }
                    for(int i = 0;i<chars.length;i++){
                        if(i!=removeIndex)
                            System.out.print(chars[i]);
                    }
                }else{
                    System.out.println("false");
                }
            }
            left++;
            right--;
        }
    }

    public static boolean help(char[] chars,int left,int right){
        while(left<right){
            if(chars[left]!=chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
