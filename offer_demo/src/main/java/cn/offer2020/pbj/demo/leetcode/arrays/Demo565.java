package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo565
 * @Author: pbj
 * @Date: 2020/4/19 10:14
 * @Description: TODO 565. 数组嵌套
 */
public class Demo565 {
    public int arrayNesting(int[] nums) {
        int max = 0;
        for(int i = 0;i<nums.length;i++){
            int cnt = 0;
            for(int j = i;nums[j]!=-1;){
                cnt++;
                int temp = nums[j];
                nums[j] = -1;
                j = temp;
            }
            max = Math.max(max,cnt);
        }
        return max;
    }

    //暴力求解
    public int arrayNesting1(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i], count = 0;
            do {
                start = nums[start];
                count++;
            }
            while (start != nums[i]);
            res = Math.max(res, count);

        }
        return res;
    }
}
