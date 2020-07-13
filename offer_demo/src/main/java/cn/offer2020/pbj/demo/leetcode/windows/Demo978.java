package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @pClassName: Demo978
 * @author: pengbingjiang
 * @create: 2020/7/12 15:34
 * @description: TODO 978. 最长湍流子数组
 */
public class Demo978 {
    //动态规划
    public int maxTurbulenceSize2(int[] A) {
        if(A.length==1){
            return 1;
        }
        int[] dp = new int[A.length];
        for(int i=1;i<A.length;i++){
            //如果i和i-1的值相等，那么i位的初始值为1，譬如A={9,9}，它返回的长度为1，而不是2。
            dp[i] = A[i]==A[i-1]?1:2;
        }
        //初始化dp以后，从2到N去计算最长长度。
        //状态转移方程: dp[i] = dp[i-1] + 1;
        //i位的可能最大长度可能是：i-1位上最大长度 + 1（包含i自己）
        //那么什么时候可以加上自己算总长度呢，当i位和i-1位的大小正好跟i-1和i-2的大小情况相反。说明i成功可以加入到前面已经计算的总长度里。
        //否则i位就是默认初始化的长度。
        int max = dp[1];
        for(int i=2;i<A.length;i++){
            if(A[i-1]-A[i-2]>0&&A[i]-A[i-1]<0 || A[i-1]-A[i-2]<0&&A[i]-A[i-1]>0){
                dp[i] = dp[i-1] + 1;
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    //滑动窗口
    public int maxTurbulenceSize(int[] A) {
        int len = A.length;
        int ans = 1;
        int anchor = 0;
        for (int i = 1; i < len; ++i) {
            int c = Integer.compare(A[i - 1], A[i]);
            if (i == len - 1 || c * Integer.compare(A[i], A[i + 1]) != -1) {
                if (c != 0) {
                    ans = Math.max(ans, i - anchor + 1);
                }
                anchor = i;
            }
        }
        return ans;
    }
    //双指针
    public int maxTurbulenceSize1(int[] A) {
        if(A==null||A.length==0) return 0;
        if(A.length==1) return 1;
        int n = A.length;
        int left = 0,right = 1;
        int res = 1 + (A[left]==A[right]?0:1);
        while(right<n-1){
            if(A[right+1]>A[right]&&A[right]<A[right-1]||A[right+1]<A[right]&&A[right]>A[right-1]){
                right++;
                res = Math.max(res,right-left+1);
            }else{
                left = right;
                right++;
            }
        }
        return res;
    }
}
