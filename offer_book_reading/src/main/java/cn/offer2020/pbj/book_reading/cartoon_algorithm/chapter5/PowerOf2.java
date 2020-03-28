package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

/**
 * @ClassName: PowerOf2
 * @Author: pbj
 * @Date: 2019/10/30 09:43
 * @Description: TODO 判断一个数是否是2的整数次幂
 */
public class PowerOf2 {

    /* *
     * 功能描述: 暴力递归求解
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/10/30 9:46
     */
    public static boolean isPowerOd2(int num) {
        int temp = 1;
        while (temp <= num) {
            if (temp == num) {
                return true;
            }
            temp = temp * 2;
        }
        return false;
    }

    /* *
     * 功能描述: 位移  O(log(n))
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/10/30 9:49
     */
    public static boolean isPowerOd2V2(int num) {
        int temp = 1;
        while (temp <= num) {
            if (temp == num) {
                return true;

            }
            temp = temp << 1;
        }
        return false;
    }

    /* *
     * 功能描述: 与运算   O(1)
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/10/30 9:51
     */
    public static boolean isPowerOfOver(int num) {
        return (num & num - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOd2(32));
        System.out.println(isPowerOd2(23));
        System.out.println(isPowerOd2V2(32));
        System.out.println(isPowerOd2V2(23));
    }
}
