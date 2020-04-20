package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: DemoLCP02
 * @Author: pbj
 * @Date: 2020/4/20 17:29
 * @Description: TODO LCP 02. 分式化简
 */
public class DemoLCP02 {
    public int[] fraction(int[] cont) {
        if(cont.length==1) return new int[]{1,cont[0]};
        int[] ans = new int[2];
        ans[0] = 1;
        ans[1] = 0;
        for(int i = cont.length-1;i>=0;i--){
            int temp = ans[1];
            ans[1] = ans[0];
            ans[0] = cont[i]*ans[1]+temp;
        }
        return ans;
    }

    public int[] fraction1(int[] cont) {
        int[] res = new int[2];
        res[0] = cont[cont.length - 1];
        res[1] = 1;
        for (int i = cont.length - 1; i > 0; i--) {
            int temp = res[0];
            res[0] = cont[i - 1] * res[0] + res[1];
            res[1] = temp;
        }
        return res;
    }
}
