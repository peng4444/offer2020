package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo923
 * @Author: pbj
 * @Date: 2020/6/10 11:29
 * @Description: TODO 923. 三数之和的多种可能
 */
public class Demo923 {
    /*
     * 数学计算法:
     * 1、三个数相加为target，三个数都不相等，出现的可能为count[x]*count[y]*count[z]
     * 2、三个数相加为target，其中有两个数相等，出现的可能为count[x]*(count[x]-1)/2 * count[z]
     * 3、三个数相加为target，三个数都相等，出现的可能为count[x]*(count[x]-1)*(count[x]-2)/3*2
     */
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1000000007;
        long count[] = new long[101];
        for (int x : A) {
            count[x]++;
        }
        long ans = 0;
        for (int x = 0; x < 101; x++) {
            for (int y = x + 1; y < 101; y++) {
                int z = target - x - y;
                if (z > y && z <= 100) {
                    ans += count[x] * count[y] * count[z];
                    ans = ans % MOD;
                }
            }
        }
        for (int x = 0; x < 101; x++) {
            int z = target - x * 2;
            if (z >= 0 && z < 101 && z != x) {
                ans += count[x] * (count[x] - 1) * count[z]/2;
                ans = ans % MOD;
            }
        }

        if (target % 3 == 0) {
            int x = target / 3;
            if(x >= 0 && x < 101) {
                ans +=  count[x] * (count[x] - 1) * (count[x]-2) / 6;
                ans = ans % MOD;
            }
        }
        return (int)ans;
    }

    //桶排序
    public int threeSumMulti1(int[] A, int target) {
        long result = 0;
        long[] numbers = new long[101]; // 0 <= A[i] <= 100
        for (int i: A) {
            numbers[i]++;
        }
        int min = 0;
        while (numbers[min] == 0) {
            min++;
        }
        int max = 100;
        while (numbers[max] == 0) {
            max--;
        }
        int i = min;
        while (i <= max) {
            int j = i;
            while (j <= max) {
                int key = target - i - j;
                if (key >= i && key <= j && numbers[key] > 0) {
                    if (i == key && i != j) {
                        result += numbers[j] * ((numbers[i] - 1) * numbers[i] / 2);
                    }else if (j == key && i != j) {
                        result += numbers[i] * ((numbers[j] - 1) * numbers[j] / 2);
                    }else if (i == j && j == key) {
                        result += numbers[i] * (numbers[i] - 1) * (numbers[i] - 2) / 6;
                    }else{
                        result += numbers[i] * numbers[j] * numbers[key];
                    }
                }
                do {
                    j++;
                } while (j < max && numbers[j] == 0);
            }
            do {
                i++;
            } while (i < max && numbers[i] == 0);
        }
        return (int)(result % 1000000007);
    }
}
