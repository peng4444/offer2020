package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Arrays;

/**
 * @ClassName: Demo238
 * @Author: pbj
 * @Date: 2020/4/15 11:33
 * @Description: TODO 238. 除自身以外数组的乘积
 */
public class Demo238 {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] products = new int[n];
        Arrays.fill(products,1);
        int left = 1;
        for(int i = 1;i<n;i++){
            left *= nums[i-1];
            products[i] *=left;
        }
        int right = 1;
        for(int i = n-2;i>=0;i--){
            right*=nums[i+1];
            products[i]*=right;
        }
        return products;
    }
}
