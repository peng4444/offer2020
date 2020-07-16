package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @pClassName: Demo190
 * @author: pengbingjiang
 * @create: 2020/7/16 09:49
 * @description: TODO 190.颠倒二进制位
 * 颠倒给定的32位无符号整数的二进制位。
 */
public class Demo190 {
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int temp = n >> i;//一位一位来
            temp &= 1;//取出最低位
            temp <<=(31 - i);//颠倒，左移到前面
            res |= temp;//“累加”
        }
        return res;
    }

    public int reverseBits2(int n) {
        int ans = 0;
        for(int i=0;i<32;i++){
            // ans = ans * 2 + n%2;
            // 其实这个等价于下面的位运算
            // 首先ans*2 = ans << 1不讲了
            // 其次 n%2 = n&1 ，这个就是和hashmap的索引机制有关，本应该hash%length,但是采用了hash&(length-1),当然这个Length是有条件的，必须是2的整数次方
            // 最后 再这里的或运算等于加运算。这里说一个特殊或运算的一个理解，就是任何一个数字和0做或运算，就是将自己的数字原封不动的写下来，直接写下来的话就是相当于这个数字和0进行了相加，这里就用到了这样的想法。 因为首先n&1是一个一位二进制数，而ans<<1的最后一位二进制数字肯定是0，所以n&1与ans<<1相或就是将n&1的结果写下来。假如最后结果是res，n&1的结果是1,那么res的最后一位结果是0|1=1.如果n&1是0，那0|0=0。和0+1=1与0+0=0是一致的。 由于ans的一位之上的位，可能是0可能是1，但是n&1的一位以上的位都是0，所以ans<<1一位以上的数字和n&1想或，就是相加。
            ans = (ans<<1) | (n&1);
            n = n >> 1;
        }
        return ans;
    }

    public int reverseBits1(int n) {
        String str = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return Integer.parseInt(sb.toString(), 10);
    }
}
