package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: InsertSort
 * @Author: pbj
 * @Date: 2020/3/13 14:13
 * @Description: TODO 插入排序 时间复杂度O(n^2)
 * 从第二位开始遍历， 当前数（第一趟是第二位数）与前面的数依次比较，如果前面的数大于当前数，则将这个数放在当前数的位置上，当前数的下标-1，
 * 重复以上步骤，直到当前数不大于前面的某一个数为止，这时，将当前数，放到这个位置。
 * 1-3步就是保证当前数的前面的数都是有序的，内层循环的目的就是将当前数插入到前面的有序序列里
 * 重复以上3步，直到遍历到最后一位数，并将最后一位数插入到合适的位置，插入排序结束。
 */
public class InsertSort {

    public int[] insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int temp = num[i];
            int j = i;
            while (j > 0 && temp < num[j - 1]) {
                num[j] = num[j - 1];
                j--;
            }
            if (j != i) {
                num[j] = temp;
            }
        }
        return num;
    }
}
