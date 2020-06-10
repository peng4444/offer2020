package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: SearchDemo
 * @Author: pbj
 * @Date: 2020/6/10 19:51
 * @Description: TODO 查找算法 [常见查找算法（Java代码实现）](https://blog.csdn.net/abcdef314159/article/details/85097414?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase)
 */
public class SearchDemo {

    //插值查找  递归
    public static int insertSearch1(int[] array, int key) {
        return search2(array, key, 0, array.length - 1);
    }
    private static int search2(int array[], int key, int left, int right) {
        if (left > right)
            return -1;
        if (array[right] == array[left]) {
            if (array[right] == key)
                return right;
            else return -1;
        }
        int mid = left + (key - array[left]) / (array[right] - array[left]) * (right - left);
        if (array[mid] == key)
            return mid;
        if (array[mid] > key)
            return search2(array, key, left, mid - 1);
        return search2(array, key, mid + 1, right);
    }
    //插值查找
    public static int insertSearch(int[] array, int key) {
        return search4(array, key, 0, array.length - 1);
    }
    private static int search4(int[] array, int key, int left, int right) {
        while (left <= right) {
            if (array[right] == array[left]) {
                if (array[right] == key)
                    return right;
                else return -1;
            }
            int middle = left + ((key - array[left]) / (array[right] - array[left])) * (right - left);
            if (array[middle] == key) {
                return middle;
            }
            if (key < array[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }


    public static int search2(int[] a, int key) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    public static int search3(int[] a, int key) {
        if (key == a[0])
            return 0;
        int index = a.length - 1;
        a[0] = key;
        while (a[index--] != key) ;
        return index == -1 ? index : index == 0 ? 1 : index + 1;
    }
    //顺序查找优化 使用一个哨兵，免去了每次都要越界的判断，但通过实际测试运行效率并没有提高，
    public static int search1(int[] a, int key) {
        int index = a.length - 1;
        if (key == a[index])
            return index;
        a[index] = key;
        int i = 0;
        while (a[i++] != key) ;
        return i == index + 1 ? -1 : i - 1;
    }
    //顺序查找
    public static int search(int[] a, int key) {
        for (int i = 0, length = a.length; i < length; i++) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }
}
