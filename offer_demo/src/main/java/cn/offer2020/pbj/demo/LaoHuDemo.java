package cn.offer2020.pbj.demo;

import java.util.ArrayList;

/**
 * @pClassName: LaoHuDemo
 * @author: pengbingjiang
 * @create: 2020/10/25 17:15
 * @description: TODO
 */
public class LaoHuDemo {
    public static ArrayList<ArrayList<Integer>> combinationSum (int[] prices, int m) {
        // write code here
        int len = prices.length;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<len;i++){
            for(int j = i;j<len;j++){
                ArrayList<Integer> list = new ArrayList<>();
                int sum = 0;
                for(int k = i;k<=j;k++){
                    sum+=prices[k];
                    list.add(prices[k]);
                }
                if(sum==m){
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] p = new int[]{3,8,6};
        combinationSum(p,14);
    }
}
