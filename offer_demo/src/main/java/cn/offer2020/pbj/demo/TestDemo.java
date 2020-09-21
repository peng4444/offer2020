package cn.offer2020.pbj.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class TestDemo {
    /**
     * 本方法会把 array 里面的 Item 顺序随机打乱，达到 “洗牌” 的效果。
     * 例如:
     * 调用本方法前，数组的内容是 {3, 2, 9}，
     * 调用本方法后，数组的内容可能就是 {2, 3, 9}、{2, 9, 3}、{3, 2, 9}、{3, 9, 2}、{9, 2, 3}、{9, 3, 2}
     **/
    public static void myFunc(int[] array) {
        Arrays.sort(array);
        int len = array.length;
        int[][] ans = new int[len][len];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<len;i++){
            list.add(array[i]);
        }
        int index = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i<len;i++){
            ans[i][index++]  = list.get(Math.round(len));
            //map.add()
        }
    }

    public static void main(String[] args){
        Integer[] arr = {3,2,9};
        flushArr(arr);
        for(int num : arr){
            System.out.print(num + " ");
        }
    }

    public static void flushArr(Integer[] arr){
        int length = arr.length;

        int index = length - 1;

        for(int i = 0; i < length && index > 0 ; i++){
            int num = createRandom(index);
            int temp = arr[num];
            arr[num] = arr[index];
            arr[index] = temp;
            index--;
        }
    }

    public static int createRandom(int end){
        return (new Random().nextInt(end));
    }
}