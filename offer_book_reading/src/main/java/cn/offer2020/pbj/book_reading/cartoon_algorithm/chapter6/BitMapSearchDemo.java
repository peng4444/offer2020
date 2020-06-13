package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter6;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: BitMapSearchDemo
 * @Author: pbj
 * @Date: 2020/6/13 09:05
 * @Description: TODO [如何从亿量级中判断一个数是否存在？](https://www.cnblogs.com/kubidemanong/p/10147685.html)
 */
public class BitMapSearchDemo {
    /* *
     * 功能描述: 现在有五十亿个int类型的正整数，要从中找出重复的数并返回。
     * @param: [arr]
     * @return: java.util.Set<java.lang.Integer>
     * @auther: pbj
     * @date: 2020/6/13 9:06
     * 判断50亿个数有哪些是重复和刚才上面那个判断是否存在，其实是一样的。我们采用bitmap算法来做。不过这里50亿个数，
     * 别人肯定是以文件流的形式给你的。这样我们为了方便，我们就假设这些数是以存在int型数组的形式给我们的。
     */
    public static Set<Integer> test(int[] arr) {
        int j = 0;
        //用来把重复的数返回，存在Set里，这样避免返回重复的数。
        Set<Integer> output = new HashSet<>();
        BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        int i = 0;
        while (i < arr.length) {
            int value = arr[i];
            //判断该数是否存在bitSet里
            if (bitSet.get(value)) {
                output.add(value);
            } else {
                bitSet.set(value, true);
            }
            i++;
        }
        return output;
    }
    //测试
    public static void main(String[] args) {
        int[] t = {1,2,3,4,5,6,7,8,3,4};
        Set<Integer> t2 = test(t);
        System.out.println(t2);
    }
}
