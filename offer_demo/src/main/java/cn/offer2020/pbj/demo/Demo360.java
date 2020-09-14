package cn.offer2020.pbj.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @pClassName: Demo360
 * @author: pengbingjiang
 * @create: 2020/9/11 20:53
 * @description: TODO
 */
public class Demo360 {
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.nextLine();
            char[] chars = str.toCharArray();
            int shuzi = 0;
            int A = 0;
            int a = 0;
            int others = 0;
            if(chars.length<8){
                System.out.println("Irregular password");
            }else{
                for(int i = 0;i<chars.length;i++){
                    if(chars[i]>='0'&&chars[i]<='9') shuzi++;
                    else if(chars[i]>='a'&&chars[i]<='z') a++;
                    else if(chars[i]>='A'&&chars[i]<='Z') A++;
                    else others++;
                }
                if(shuzi>0&&A>0&&a>0&&others>0){
                    System.out.println("Ok");
                }else{
                    System.out.println("Irregular password");
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 3;//sc.nextInt();
        int m = 2;//sc.nextInt();
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{1,0};
//        for(int i = 0;i<m;i++){
//            nums1[i] = sc.nextInt();
//            nums2[i] = sc.nextInt();
//        }
        //元工工号存入list
        ArrayList<Integer> list = new ArrayList<>(n);
        for(int i = 1;i<=n;i++){
            list.add(i);
        }
        //如果第一个和最后一个是正常打卡上班打卡下班，就可能是A
        if(nums1[0]==nums1[m-1]&&nums2[0]==1&&nums2[m-1]==0){
            for(int i = 1;i<m-1;i++){
                Iterator<Integer> it = list.iterator();
                while(it.hasNext()){
                    if(it.next().equals(nums1[i])){
                        it.remove();
                    }
                }
            }
        }else{
            for(int i = 0;i<m;i++){
                Iterator<Integer> it = list.iterator();
                while(it.hasNext()){
                    if(it.next().equals(nums1[i])){
                        it.remove();
                    }
                }
            }
        }
        for(int i = 0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
    }
}
