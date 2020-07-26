package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @pClassName: Demo410
 * @author: pengbingjiang
 * @create: 2020/7/25 15:52
 * @description: TODO 410.分割数组的最大值
 * 给定一个非负整数数组和一个整数m，你需要将这个数组分成m个非空的连续子数组。设计一个算法使得这m个子数组各自和的最大值最小。
 */
public class Demo410 {
    public int splitArray(int[] nums, int m) {
        /*
        二分法
        nums = [7,2,5,10,8]
            m = 1，那么整个数组作为一部分，最小的最大值为 32
            m = n，那么每个元素作为一个子数组，从所有元素选取最大值，最小的最大值小为 10
            m 的取值范围为 1 <= m <= n，因此，最大值的最小值的范围为 [10, 32]

        我们利用二分法查找，找出符合 m 的最大值的最小的结果
        二分过程：
            left = 10;
            right = 32
            mid = (left + right) >>> 1 = 21（这个 21 就是一个子数组的最大容量）
        我们假设刚开辟的用来存储的子数组个数 cnt = 1
        那么根据贪心思想，我们将数组元素按顺序逐个往里放
        因此就有如下过程：
            7 < 21
            7 + 2 < 21
            7 + 2 + 5 < 21
            7 + 2 + 5 + 10 > 21
         至此，我们可以看出一个 21 容量的子数组是无法容纳整个数组元素的，因此我们需要开辟第二个子数组来存储剩下的数组元素
        cnt = cnt + 1 = 2
        10 < 21
        10 + 8 < 21
        我们发现，两个子数组可以将整个数组元素放入，而 cnt 刚好等于 m，因此 [7,2,5] 和 [10,8] 就是分割出来的两个子数组，最小的最大值为 18

        为什么是放入元素直到放不下为止？因为要求的是连续子数组，我们需要保证每个连续的子数组的元素和都尽可能的接近 21

        如果我们最终得到的cnt > m，那么表示我们划分出太多的子数组，也就是意味着一个子数组的容量太少，我们需要再扩大容量，即left = mid + 1，然后继续进行二分
        如果我们最终得到的cnt < m，那么表示我们划分出太少的子数组，也就是意味着一个子数组的容量太大，需要减少容量，即right = mid - 1
        */
        int left = 0;
        int right = 0;
        for(int num : nums){
            left = Math.max(left, num);
            right += num;
        }
        //二分 logn，内部遍历数组 n，时间复杂度 O(nlogn)
        while(left < right){
            int cnt = 1;
            int mid = (left + right) >>> 1;
            int sum = 0;
            for(int num : nums){
                if(sum + num > mid){
                    sum = 0;
                    cnt++;
                }
                sum += num;
            }
            if(cnt > m){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    /*
    给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。
    设计一个算法使得这 m 个子数组各自和的最大值最小。
    输入:
    nums = [7,2,5,10,8]
    m = 2

    输出:
    18
     */
    public int splitArray1(int[] nums, int m) {
        // dp[i][j] 定义为将 nums[0..i] 分成 j 份时能得到的最小的分割子数组最大值
        int n = nums.length;
        if(m == n){
            int maxVal = Integer.MIN_VALUE;
            for(int i=0;i<n;i++) {
                maxVal = Math.max(nums[i], maxVal);
            }
            return maxVal;
        }
        int[][] dp = new int[n][m+1];
        int[] maxVal = new int[n];
        // j 为1的情况
        for(int i=0;i<n;i++){
            if(i == 0){
                dp[0][1] = nums[i];
                maxVal[i] = nums[i];
            }else {
                dp[i][1] = nums[i] + dp[i-1][1];
                maxVal[i] = Math.max(nums[i], maxVal[i-1]);
            }
        }
        for(int j=2;j<=m;j++){
            for(int i=j-1;i<n;i++){
                dp[i][j] = Integer.MAX_VALUE;
                if(i == j-1){ // 分成一份,和就是自己，最大就是自己
                    dp[i][j] = Math.min(maxVal[i], dp[i][1]);
                }else {
                    // 最后一份可以是i与前面k个位置组合，前面k个元素组成j-1份
                    int sumsTmp = 0;
                    for(int k=i;k>=j-1;k--){
                        sumsTmp = sumsTmp + nums[k];
                        // 各自和的最大值
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k-1][j-1], sumsTmp));
                    }
                }
            }
        }
        return dp[n-1][m];
    }
}
