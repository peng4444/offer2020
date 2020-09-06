package cn.offer2020.pbj.demo;

import java.util.*;

public class TestDemo {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 50;//sc.nextInt();
        int m = 5;//sc.nextInt();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int size = sc.nextInt();
            List<Integer> list = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                list.add(sc.nextInt());
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (ans.get(i).contains(0)) {
                set.addAll(ans.get(i));
            }
        }
        for(int i = 0;i<ans.size();i++){
            for(int j = 0;j<ans.get(i).size();j++){
                if(set.contains(ans.get(i).get(j))){
                    set.addAll(ans.get(i));
                }
            }
        }
        System.out.println(set.size());
    }

    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        int half = n/2;
        int index = 0;
        while(index<half){
            System.out.println(nums[half]);
            index++;
        }
        while(index<n){
            System.out.println(nums[half-1]);
            index++;
        }
    }

    public static void main2(String[] args){
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        for(int i = 0;i<n;i++){
            System.out.println(help(nums,i));
        }
    }

    public static int help(int[] nums,int i){
        int[] arr = new int[nums.length-1];
        int index = 0;
        for(int j = 0;j<nums.length;j++){
            if(j!=i){
                arr[index++] = nums[j];
            }
        }
        Arrays.sort(arr);
        return arr[nums.length/2 - 1];
    }
}