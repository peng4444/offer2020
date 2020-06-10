package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @ClassName: JiWeijiuSort
 * @Author: pbj
 * @Date: 2019/9/19 09:20
 * @Description: TODO
 * 鸡尾酒排序：元素比较和交换过程是双向的 每次循环则将最小元素和最大元素分别推至左右两侧 有序区【最后面几个或者最前面几个有序】不再排序
 * 参考漫画算法
 *  场景：大部分有序   缺点：代码量增加
 */
public class JiWeijiuSort {
    public static void JiWeijiuSort(int array[]) {
        int temp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            //有序标记，每一轮的初始值都是true
            boolean sortFalg = true;
            //奇数轮，从左向右比较和交换
            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j +1] = temp;
                    //有元素交换，所以不是有序的，标记为false
                    sortFalg = false;
                }
            }
            if (sortFalg){
                break;
            }
            //在偶数轮之前，将sortFlag重新标记为true
            sortFalg = true;
            //偶数轮，从右往左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    //因为有元素的交换，所以不是有序的，标记为false
                    sortFalg = false;
                }
            }
            if (sortFalg) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,5,6,7,8,1};
        JiWeijiuSort(array);
        System.out.println(Arrays.toString(array));
        int[] array1 = new int[]{2,3,4,5,6,7,8,1};
        sort4(array1);
        int[] array2 = new int[]{2,3,4,5,6,7,8,1};
        sort5(array2);
    }

    //[数据结构 7 基础排序算法详解 鸡尾酒排序法、了解钟摆排序实现](https://www.cnblogs.com/ChromeT/p/12867378.html#_labelTop)
    //鸡尾酒排序法 :在钟摆排序的基础上有序优化
    public static void sort5(int[] array) {
        //在发生变化的时候记录当前位置，下次循环到本次记录的位置停止则可以了。
        //通过两个边界变量 leftBorder 与 rightBorder 控制程序比较位置的终止。
        //左循环从左边界比较到右边界停止
        //右循环从右边界比较到左边界停止
        int temp = 0;
        int allChange = 0;

        //记录左边界
        int leftBorder = 0;
        //右边界
        int rightBorder = array.length - 1;
        //左边发生变化的位置
        int leftChangeIndex = 0;
        //右边发生变化的位置
        int rightChangeIndex = 0;

        /**
         * 外层循环用于控制程序总体循环次数
         */
        for (int i = 0; i < array.length / 2; i++) {

            /**
             * 有无交换元素标记
             */
            boolean isChange = true;

            //从左向右摆动
            for (int j = leftBorder; j < rightBorder; j++) {

                if (array[j] > array[j + 1]) {

                    //交换元素
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    isChange = false;

                    //记录右边边界
                    rightChangeIndex = j;
                }
                allChange++;
            }
            //用于下次循环终止
            rightBorder = rightChangeIndex;

            if (isChange) break;

            isChange = true;
            //从右向左摆动
            for (int g = rightBorder; g > leftBorder; g--) {

                if (array[g] < array[g - 1]) {

                    temp = array[g];

                    array[g] = array[g-1];
                    array[g-1] = temp;

                    isChange = false;
                    //记录左边界
                    leftChangeIndex = g;

                }
                allChange++;
            }
            leftBorder = leftChangeIndex;

            if (isChange) break;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("总体比较次数："+allChange);
    }

    //钟摆排序
    public static void sort4(int[] array) {

        int temp = 0;
        int allChange = 0;
        /**
         * 外层循环用于控制程序总体循环次数
         */
        for (int i = 0; i < array.length / 2; i++) {

            /**
             * 有无交换元素标记
             */
            boolean isChange = true;

            //从左向右摆动
            for (int j = i; j < array.length - i - 1; j++) {

                if (array[j] > array[j + 1]) {

                    //交换元素
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    isChange = false;
                }
                allChange++;
            }

            if (isChange) break;

            isChange = true;
            //从右向左摆动
            for (int g = array.length - i - 1; g > i; g--) {

                if (array[g] < array[g - 1]) {

                    temp = array[g];

                    array[g] = array[g-1];
                    array[g-1] = temp;

                    isChange = false;
                }
                allChange++;
            }
            if (isChange) break;
        }
        System.out.println(Arrays.toString(array));
        System.out.println("总体比较次数："+allChange);
    }
}
