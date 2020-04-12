package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo667
 * @Author: pbj
 * @Date: 2020/3/10 23:30
 * @Description: TODO 667. 优美的排列 II
 */
public class Demo667 {

    //
    class Solution {
        public int[] constructArray(int n, int k) {
            int[] ans = new int[n];
            ans[0] = 1;
            for(int i = 1,interval = k;i<=k;i++,interval--){
                ans[i] = i %2==1?ans[i-1]+interval:ans[i-1]-interval;
            }
            for(int i = k+1; i<n;i++){
                ans[i] = i + 1;
            }
            return ans;
        }
    }

    /* *
     * 功能描述:若n=8初始状态
1 2 3 4 5 6 7 8
k=1~~~~~~~~         | 1 2 3 4 5 6 7 8 (不翻转，直接返回)
k=2~~~~~~~~         1 | 8 7 6 5 4 3 2
k=3~~~~~~~~         1 8 | 2 3 4 5 6 7
k=4~~~~~~~~         1 8 2 | 7 6 5 4 3
总结规律：当k>1时,需要翻转的次数为k-1次，每次翻转保留前m(m = 1,2,3...k-1)个数，每次翻转都在原数组进行。
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/3/10 23:31
     */
    class Solution1 {
        public int[] constructArray(int n, int k) {
            int[] res = new int[n];
            for(int i = 0; i < n; i++) res[i] = i + 1;  //产生1~n个数
            if(k == 1) return res;  //k==1直接返回
            //k != 1就要翻转k - 1次，每次翻转保留前m个数
            for(int m = 1; m < k; m++)
                reverse(res, m, n - 1);
            return res;
        }
        //翻转数组[i,j]之间的数
        void reverse(int[] res, int i, int j){
            while(i < j){
                int t = res[i];
                res[i] = res[j];
                res[j] = t;
                i++;
                j--;
            }
        }
    }

    class Solution2 {
        public int[] constructArray(int n, int k) {
            int[] res = new int[n];

            for(int i = 1;i < n - k;i++){
                res[i-1] = i;
            }
            int m = 1;
            int l = k+1;
            for(int i = n-k-1;i < n;i++){
                if((i-n-k-1) % 2 == 0){
                    res[i] = m++;
                    res[i] += n-k-1;
                }else{
                    res[i] = l--;
                    res[i] += n-k-1;
                }
            }
            return res;
        }
    }

}
