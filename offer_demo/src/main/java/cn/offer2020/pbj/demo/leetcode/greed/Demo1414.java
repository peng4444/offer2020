package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @pClassName: Demo1414
 * @author: pengbingjiang
 * @create: 2020/7/10 10:43
 * @description: TODO 1414. 和为 K 的最少斐波那契数字数目
 * 给你数字 k ，请你返回和为 k 的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。
 */
public class Demo1414 {
    //贪心
    public int findMinFibonacciNumbers(int k) {
        if(k<=3){
            return 1;
        }
        int[] arr = new int[46];
        arr[0] = 1;
        arr[1] = 1;
        for(int i = 2;i<arr.length;i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        int count = 0;
        int i = arr.length - 1;
        while(k>0 && i>=0){
            if(k-arr[i]>=0){
                int num = k / arr[i];
                k-=(arr[i]*num);
                count+=num;
            }
            i--;
        }
        return count;
    }
    //
    public int findMinFibonacciNumbers1(int k) {
        int[] dp = new int[45];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < 45; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int ans = 0;
        for (int j = 44; j >= 0; j--) {
            if (k >= dp[j]) {
                k = k - dp[j];
                ans++;
            }
        }
        return ans;
    }

    //不需要数组，找到最大值，然后依次衰减
    public int findMinFibonacciNumbers2(int k) {
        int p0 = 1,p1 = 1;
        while(p0 + p1 <= k){
            p1 = p0 + p1;
            p0 = p1 - p0;
        }
        int count = 0;
        while(k > 0){
            if(k >= p1){
                count++;
                k = k - p1;
            }
            p0 = p1 - p0;
            p1 = p1 - p0;
        }
        return count;
    }
}
