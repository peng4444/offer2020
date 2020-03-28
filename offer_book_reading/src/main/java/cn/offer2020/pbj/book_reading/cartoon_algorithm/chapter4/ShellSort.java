package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: ShellSort
 * @Author: pbj
 * @Date: 2020/3/13 14:26
 * @Description: TODO 希尔排序比较特殊，它的性能略优于O(n2)，但又比不上O(nlogn)
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，
 * 当增量减至1时，整个文件恰被分成一组，算法便终止。
 */
public class ShellSort {

    public int[] shellSort(int[] num) {
        int gap = 1;
        while (gap < num.length) {
            gap = gap*3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < num.length; i++) {
                int temp = num[i];
                int j = i - gap;
                while (j >= 0 && num[j] > temp) {
                    num[j + gap] = num[j];
                    j = j - gap;
                }
                num[j+gap] = temp;
            }
            gap = (int) Math.floor(gap / 3);
        }
        return num;
    }
}
