package cn.offer2020.pbj.demo.job.ByteDance;

import java.util.Scanner;

/**
 * @pClassName: Main0704
 * @author: pengbingjiang
 * @create: 2020/7/5 09:06
 * @description: TODO
 * 1. 给出一个整数n，要找重新组合后比n小，同时是组合后最大的数 例如 11，找不到 not  found， 132 为123；
 */
public class Main070401 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] chars = (n+"").toCharArray();
        if(chars.length<=1){
            System.out.println("Not found");
        }
        boolean isExist = false;
        for(int i = chars.length-1;i>0;i--){
            if((chars[i]-'0')<chars[i-1]-'0'){
                char temp = chars[i-1];
                chars[i-1] = chars[i];
                chars[i] = temp;
                isExist = true;
                break;
            }
        }
        int ans = chars[0]-'0';
        for(int i = 1;i<chars.length;i++){
            ans = ans*10 + chars[i]-'0';
        }
        if(isExist){
            System.out.println("result exist:"+ans);
        }else {
            System.out.println("result not found");
        }
    }
}
