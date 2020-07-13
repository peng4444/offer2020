package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo152
 * @Author: pbj
 * @Date: 2019/12/29 19:12
 * @Description: TODO 152.乘积最大子序列
 */
public class Demo152 {

    /* *
     * 功能描述: 动态规划
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 20:35
     */
    public int maxProduct(int[] nums) {
        int max=nums[0];
        int min=nums[0];
        int res=nums[0];
        for(int x=1;x<nums.length;x++){
            int cur=nums[x];
            // cur*max cur*min cur 取三者最大值,三者最小值
            int temp = Math.max(Math.max(cur * max, cur * min), cur);
            min=Math.min(Math.min(cur * max,cur * min), cur);
            max = temp;
            //只有当最大值大于前最大值,才互换
            if(res<max) res=max;
        }
        return res;
    }

    /* *
     * 功能描述:动态规划
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/4 10:09
     */
    class Solution {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE, imax = 1, imin = 1;//一个保存最大的，一个保存最小的。
            for(int i=0; i<nums.length; i++){
                if(nums[i] < 0){//如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
                    int tmp = imax;
                    imax = imin;
                    imin = tmp;
                }
                imax = Math.max(imax*nums[i], nums[i]);
                imin = Math.min(imin*nums[i], nums[i]);

                max = Math.max(max, imax);
            }
            return max;
        }
    }
}
