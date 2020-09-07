package cn.offer2020.pbj.demo;

import java.util.Scanner;

/**
 * @pClassName: Demo2222
 * @author: pengbingjiang
 * @create: 2020/9/7 21:01
 * @description: TODO
 */
public class Demo2222 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println((x&(x-1))==0);
    }
}
