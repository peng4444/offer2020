package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @ClassName: MaopaoSort
 * @Author: pbj
 * @Date: 2019/9/18 23:12
 * @Description: TODO 冒泡排序  稳定排序  平均时间复杂度是O(n2)
 * 按照冒泡排序的思想，我们要把相邻的元素两两比较，当一个元素大于右侧相邻元素时，交换它们的位置；
 * 当一个元素小于或等于右侧相邻元素时，位置不变。
 */
public class MaopaoSort {
    //冒泡排序算法思想
    public static void MaopaoSort(int array[]) {
        int sortBorder = array.length -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j] + 1) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = array[j];
                }
            }
        }
    }
    //优化1：在最后一次交换开始之前，已经有序，就提前终止循环排序
    public static void MaopaoSort2(int array[]) {
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j] + 1) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = array[j];
                    //因为有元素进行交换，所以不是有序的，标记为false
                    sortFlag = false;
                }
            }
            if (sortFlag) {
                break;//如果某一轮有序标记为true，说明当前已有序，可以终止循环
            }
        }
    }

    //优化2：我们可以在每一轮排序后，记录下来最后一次元素交换的位置，该位置即为有序无序数列的边界，再往后就是有序区了
    public static void MaopaoSort3(int array[]) {
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length -1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j] + 1) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = array[j];
                    //因为有元素进行交换，所以不是有序的，标记为false
                    sortFlag = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (sortFlag) {
                break;//如果某一轮有序标记为true，说明当前已有序，可以终止循环
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        MaopaoSort(array);
        System.out.println(Arrays.toString(array));
    }
}
