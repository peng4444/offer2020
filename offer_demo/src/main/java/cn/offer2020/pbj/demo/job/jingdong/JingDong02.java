package cn.offer2020.pbj.demo.job.jingdong;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: JingDong02
 * @Author: pbj
 * @Date: 2019/8/25 17:08
 * @Description: TODO 链接：https://www.nowcoder.com/discuss/232678
 */
/*
男女编号[1,n] [n+1,2n]，映射到(n+1)X(n+1)矩阵，
第一列记录每个男生的关系总数，第一行记录每个女生的关系总数，
arr[0][0]记录男女关系对数，为0表示无男女关系，
矩阵剩下的位置表示对应编号的男女关系：1存在、0不存在。
每次从第一列第一行找出关系最多的编号搬出，并移除他的关系，直到arr[0][0]==0
 */
public class JingDong02 {
    static int n;
    static int[][] arr;
    static List<Integer> list=new LinkedList();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        arr=new int[n+1][n+1];
        arr[0][0]=sc.nextInt();
        for(int i=0;i<arr[0][0];i++){
            int boyId=sc.nextInt();
            int girlId=sc.nextInt();
            arr[boyId][girlId-n]=1;
            arr[boyId][0]+=1;
            arr[0][girlId-n]+=1;
        }
        while (arr[0][0]!=0){
            move();
        }
        //最后保证字典序最小
        Collections.sort(list);
        System.out.println(list.size());
        System.out.println(list);
    }

    static void move(){
        int maxId=-1,maxVal=0;
        //找到关系最多的男或者女，且字典序最小
        for(int i=1;i<=2*n;i++){
            if(i<=n){
                if (arr[i][0]>maxVal){
                    maxId=i;
                    maxVal=arr[i][0];
                }
            }else {
                if (arr[0][i-n]>maxVal){
                    maxId=i;
                    maxVal=arr[0][i-n];
                }
            }
        }
        //搬出男或者女
        //并且移除他和其他人的关系，同时减少关系对数
        if(maxId<=n){
            arr[maxId][0]=0;
            for(int i=1;i<=n;i++){
                if(arr[maxId][i]==1){
                    arr[0][i]--;
                    arr[0][0]--;
                }
            }
        }else {
            arr[0][maxId-n]=0;
            for(int i=1;i<=n;i++){
                if(arr[i][maxId-n]==1){
                    arr[i][0]--;
                    arr[0][0]--;
                }
            }
        }
        //记录本次搬出的编号
        list.add(maxId);
    }
}
