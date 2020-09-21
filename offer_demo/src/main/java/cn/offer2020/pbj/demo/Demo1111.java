package cn.offer2020.pbj.demo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @pClassName: Demo1111
 * @author: pengbingjiang
 * @create: 2020/9/20 21:19
 * @description: TODO
 */
public class Demo1111 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] arr1 = new int[26];
        for(int i =0;i<chars1.length;i++){
            arr1[chars1[i]-'A']++;
        }
        int ans = 0;
        for(int i = 0;i<chars2.length;i++){
            if(arr1[chars2[i]-'A']>0){
                ans++;
                arr1[chars2[i]-'A']--;
            }
        }
        System.out.println(ans);
    }

    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        int index = 0;
        int ans = 0;
        for(int i = 0;i<chars1.length&&index<chars2.length;i++){
            if(chars1[i]==chars2[index]){
                ans++;
                index++;
            }else if(chars1[i]>chars2[index]){
                index++;
            }
        }
        System.out.println(ans);
    }
}
