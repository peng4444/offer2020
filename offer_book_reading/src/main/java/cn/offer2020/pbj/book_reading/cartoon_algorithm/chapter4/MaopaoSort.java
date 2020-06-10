package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @ClassName: MaopaoSort
 * @Author: pbj
 * @Date: 2019/9/18 23:12
 * @Description: TODO 冒泡排序  稳定排序  平均时间复杂度是O(n^2)  空间复杂度：只使用了一个临时变量，所以为O(1)；
 * 按照冒泡排序的思想，我们要把相邻的元素两两比较，当一个元素大于右侧相邻元素时，交换它们的位置；
 * 当一个元素小于或等于右侧相邻元素时，位置不变。
 * [如何优化冒泡排序？](https://www.cnblogs.com/9dragon/p/10705097.html)
 */
public class MaopaoSort {
    //冒泡排序
    public static void MaopaoSort(int[] array) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        //外循环是趟数，每一趟都会将未排序中最大的数放到尾端
        for (int i = 0; i < array.length - 1; i++) {
            //内循环是从第一个元素开始，依次比较相邻元素，
            // 比较次数随着趟数减少，因为每一趟都排好了一个元素
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //优化1：在最后一次交换开始之前，已经有序，就提前终止循环排序
    public static void MaopaoSort2(int array[]) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true，假设每一趟开始前都假设已经有序
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
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
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //当前趟无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true，假设每一趟开始前都假设已经有序
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
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
        // 还可以进一步优化， 有兴趣的可以去看看鸡尾酒排序
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        MaopaoSort(array);
        System.out.println(Arrays.toString(array));
        int[] array1 = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        MaopaoSort2(array1);
        System.out.println(Arrays.toString(array1));
        int[] array2 = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        MaopaoSort3(array2);
        System.out.println(Arrays.toString(array2));
    }
}
