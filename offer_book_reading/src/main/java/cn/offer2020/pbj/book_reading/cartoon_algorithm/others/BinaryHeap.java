package cn.offer2020.pbj.book_reading.cartoon_algorithm.others;

import java.util.Arrays;

/**
 * @ClassName: BinaryHeap
 * @Author: pbj
 * @Date: 2020/6/2 10:47
 * @Description: TODO [基础数据结构 二叉堆 了解二叉堆的元素插入、删除、构建二叉堆的代码方式](https://www.cnblogs.com/ChromeT/p/13023646.html)
 */
public class BinaryHeap {
    public static void main(String[] args) {
        int[] array = {7, 1, 3, 5, 6, 4, 2, 8, 9};
        buildBinaryHeap(array);
        System.out.println(Arrays.toString(array));
    }

    public static void buildBinaryHeap(int[] array) {
        //除去叶子节点、将每个节点进行下沉操作
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            sinking(array, i);
        }
    }

    /**
     * 构建二叉堆、让当前元素下沉
     *
     * @param array     被操作的数组
     * @param itemIndex 当前元素下标
     */
    public static void sinking(int[] array, int itemIndex) {
        //数组长度
        int length = array.length - 1;
        //父节点值
        int parent = array[itemIndex];

        //默认操作的是左孩子
        int childIndex = 2 * itemIndex + 1;

        while (childIndex < length) {

            //存在右边子元素、并且右边子元素值小于左边
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                //切换到右边元素
                childIndex++;
            }

            //小于等于则无需交换
            if (parent <= array[childIndex]) break;

            //无需交换、只需要将子元素移动到父元素位置即可
            array[itemIndex] = array[childIndex];
            itemIndex = childIndex;

            //改变左右子元素的下标
            childIndex = 2 * itemIndex + 1;
        }
        //最终将父元素移动到指定位置即可。
        array[itemIndex] = parent;
    }
}
