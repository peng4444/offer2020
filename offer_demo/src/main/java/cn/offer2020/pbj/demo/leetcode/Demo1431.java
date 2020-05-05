package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo1431
 * @Author: pbj
 * @Date: 2020/5/5 10:53
 * @Description: TODO 1431. 拥有最多糖果的孩子
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。
 * 注意，允许有多个孩子同时拥有 最多 的糖果数目。
 */
public class Demo1431 {
    //自己写的  100%
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<candies.length;i++){
            max = Math.max(max,candies[i]);
        }
        List<Boolean> ans = new ArrayList<>(candies.length);
        for(int i = 0;i<candies.length;i++){
            if(candies[i]+extraCandies>=max){
                ans.add(true);
            }else{
                ans.add(false);
            }
        }
        return ans;
    }
}
