package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @pClassName: Demo334
 * @author: pengbingjiang
 * @create: 2020/7/19 11:15
 * @description: TODO
 */
public class Demo334 {
    //把small看做最小值，second看做第二元素最小的递增对的第二元素，那么算法的实质就是不断更新small和second而已。
    public boolean increasingTriplet1(int[] nums) {
        int len=nums.length;
        if(nums.length<3) return false;
        int min=Integer.MAX_VALUE; //最小值
        int second=Integer.MAX_VALUE;//第二元素最小的递增对的第二元素
        for(int num:nums){
            if(num<=min){//num比min小或相等，肯定不存在递增三元素。刷新的时候由不可能存在以num结尾的递增对，因此，只需要刷新min
                min=num;
            }else if(num<=second){//num比second小或相等，肯定不存在递增三元素。由于存在以num结尾的递增对且num不大于second，因此可以更新second
                second=num;
            }else {//num比second大，那就存在递增三元素，因为second是一个递增对的第二元素，加上num后就是递增三元素了
                return true;
            }
        }
        return false;
    }
    //dp 暴力法
    public boolean increasingTriplet(int[] nums) {
        if(nums==null||nums.length<3) return false;
        int[] dp = new int[nums.length+1];
        int maxLen = 1;
        for(int i = 0;i<nums.length;i++){
            dp[i] = 1;
        }
        for(int i = 1;i<nums.length;i++){
            for(int j = 0;j<i;j++){
                if(nums[i]>nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen>=3?true:false;
    }
    //定义两个数组forward[i]和backward[i]，
    //forward[i]从前向后遍历，保存[0, i]之间最小元素值，backward[i]从后向前遍历，保存[i, size - 1]间最大元素值。
    // 然后从前向后遍历，如果找到一个数满足forward[i] < nums[i] < backward[i]，则表示三元子序列存在。
    public boolean increasingTriplet3(int[] nums) {
        if(nums.length<3) return false;
        int len = nums.length;
        int[] forward = new int[len];
        int[] backward = new int[len];
        forward[0] = nums[0];
        backward[0] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            forward[i] = Math.min(forward[i - 1], nums[i]);
        }
        for (int j = len - 2; j >= 0; j--) {
            backward[j] = Math.max(backward[j + 1], nums[j]);
        }
        for (int i = 0; i < len; i++) {
            if (forward[i] < nums[i] && nums[i] < backward[i]) {
                return true;
            }
        }
        return false;
    }

    //单调栈
    public boolean increasingTriplet2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] stack = new int[3];
        int ptr = 0;
        for (int num : nums) {
            while (ptr > 0 && stack[ptr - 1] >= num) {
                ptr--;
            }
            stack[ptr++] = num;
            if (ptr == 3) {
                return true;
            }
        }
        ptr = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (ptr > 0 && stack[ptr - 1] <= nums[i]) {
                ptr--;
            }
            stack[ptr++] = nums[i];
            if (ptr == 3) {
                return true;
            }
        }
        return false;
    }
}
