package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: InsertSort
 * @Author: pbj
 * @Date: 2020/3/13 14:13
 * @Description: TODO 插入排序 时间复杂度O(n^2)空间复杂度：只使用了一个临时变量，所以为O(1)；
 * 第一位数已经有序从第二位开始遍历，当前数（第一趟是第二位数）与前面的数依次比较，如果前面的数大于当前数，则将这个数放在当前数的位置上，当前数的下标-1，
 * 重复以上步骤，直到当前数不大于前面的某一个数为止，这时，将当前数，放到这个位置。
 * 1-3步就是保证当前数的前面的数都是有序的，内层循环的目的就是将当前数插入到前面的有序序列里
 * 重复以上3步，直到遍历到最后一位数，并将最后一位数插入到合适的位置，插入排序结束。
 */
public class InsertSort {

    public int[] insertSort(int[] num) {
        if (null == num || num.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        for (int i = 1, j, current; i < num.length; i++) {
            current = num[i];
            for (j = i - 1; j >= 0 && num[j] > current; j--) {
                num[j + 1] = num[j];
            }
            num[j + 1] = current;
        }
        return num;
    }

    public int[] insertSort2(int[] num) {
        if (null == num || num.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        for (int i = 1, j, current; i < num.length; i++) {
            current = num[i];
            j = i -1;
            while (j >= 0 && num[j] > current) {
                num[j+1] = num[j];
                j--;
            }
            num[j + 1] = current;
        }
        return num;
    }
}
