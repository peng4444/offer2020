package cn.offer2020.pbj.demo;


import java.util.Scanner;

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
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] nums = new int[2][M];
        int[] ans = new int[N];
        for(int i=0;i<2;i++){
            for(int j = 0;j<M;j++){
                nums[i][j] = sc.nextInt();
                ans[nums[i][j]]++;
            }
        }
        int res = 0;
        for(int i = 0;i<N;i++){
            res +=ans[i]*i%100000009;
        }
        System.out.println(res);
    }
}
