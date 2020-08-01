package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @pClassName: Demo233
 * @author: pengbingjiang
 * @create: 2020/8/1 11:52
 * @description: TODO 233.数字1的个数
 */
public class Demo233 {

    //
    public int countDigitOne1(int n) {
        int count = 0;

        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }

        return count;
    }

    //暴力法，一个数一个数的判断，一位一位的判断。
    public int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i;
            while (temp > 0) {
                if(temp%10==1){
                    count++;
                }
                temp = temp/10;
            }
        }
        return count;
    }
}
