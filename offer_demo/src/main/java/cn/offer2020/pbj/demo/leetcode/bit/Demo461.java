package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo461
 * @Author: pbj
 * @Date: 2020/4/19 10:53
 * @Description: TODO 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 */
public class Demo461 {

    //对两个数进行异或操作，位级表示不同的那一位为 1，统计有多少个 1 即可。
    public int hammingDistance(int x, int y) {
        int  z = x^y;
        int cnt = 0;
        while(z!=0){
            if((z&1)==1) cnt++;
            z = z>>1;
        }
        return cnt;
    }

    //使用 z&(z-1) 去除 z 位级表示最低的那一位。
    public int hammingDistance1(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while (z != 0) {
            z &= (z - 1);
            cnt++;
        }
        return cnt;
    }

    //可以使用 Integer.bitcount() 来统计 1 个的个数。
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
