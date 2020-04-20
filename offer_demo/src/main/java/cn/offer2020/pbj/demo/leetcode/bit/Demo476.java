package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo476
 * @Author: pbj
 * @Date: 2020/4/19 14:41
 * @Description: TODO 476. 数字的补数
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 */
public class Demo476 {
    public int findComplement(int num) {
        /*找到数字所在的区间[2^(i-1), 2^i]
          找到上区间，然后减一，取得是全一
          这样num与max-1异或后就是所需要的结果
          例子：5的补数为2，5^2=7，找到5的区间在[4,8],然后8-1=7，得到7
          所以5的补数2=5^7,同理任何数均可如此处理。
          注意：数字类型转换，浮点型和整型。
        */
        double max = 0;
        // 循环找到上限
        for(int index = 0; num >= max; index++) {
            max = Math.pow(2, index);
        }
        // 上限减一取的此区间2进制为全一的数
        int res = (int)(max - 1);
        return num ^ res;
    }
    //
    public int findComplement1(int num) {
        if(num==0) return 1;
        int mask = 1 << 30;
        while((num&mask)==0) mask >>=1;
        mask = (mask<<1) - 1;
        return num^mask;
    }
}
