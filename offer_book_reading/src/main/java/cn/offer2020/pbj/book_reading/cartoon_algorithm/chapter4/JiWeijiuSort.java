package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @ClassName: JiWeijiuSort
 * @Author: pbj
 * @Date: 2019/9/19 09:20
 * @Description: TODO 鸡尾酒排序：的元素比较和交换过程是双向的 每次循环则将最小元素和最大元素分别推至左右两侧 有序区【最后面几个或者最前面几个有序】不再排序
 * [数据结构 7 基础排序算法详解 鸡尾酒排序法、了解钟摆排序实现](https://www.cnblogs.com/ChromeT/p/12867378.html#_labelTop)
 *  场景：大部分有序   代码量增加
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
    }
}
