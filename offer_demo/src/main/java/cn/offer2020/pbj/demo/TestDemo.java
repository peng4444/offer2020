package cn.offer2020.pbj.demo;

import java.util.*;

public class TestDemo {
    public static int[] arrayMerge (int[] array1, int n, int[] array2, int m) {
        // write code here
        Arrays.sort(array2);
        int len = m+n-1;
        int i = n - 1;
        int j = m -1;
        while(i>=0&&j>=0){
            array1[len--] = (array1[i]>array2[j])?array1[i--]:array2[j--];
        }
        while(j>=0){
            array1[len--] = array2[j--];
        }
        return array1;
    }

    public static void main(String[] args) {
        int[] n1 = new int[]{1,2,3};
        int[] n2 = new int[]{6,5,4};
        int[] ints = arrayMerge(n1, 3, n2, 3);
        for(int i = 0;i<ints.length;i++){
            System.out.print(ints[i]+" ");
        }
    }
}