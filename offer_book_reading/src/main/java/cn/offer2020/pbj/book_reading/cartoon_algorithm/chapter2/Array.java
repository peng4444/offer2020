package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter2;

/**
 * @ClassName: Array
 * @Author: pbj
 * @Date: 2019/9/16 10:59
 * @Description: TODO
 */
public class Array {
    private int[] array;
    private int size;

    public Array(int capacity) {
        this.array = new int[capacity];
        size = 0;
    }

    /* *
     * 功能描述: 数组插入元素(中间插入)  复杂度O(n)
     * @param: [element, index] 插入的元素和插入的位置
     * @return: void
     * @auther: pbj
     * @date: 2019/9/16 11:01
     */
    public void insertArray(int element, int index) {
        //判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        //如果实际元素达到数组容量上限，则对数组进行扩容
        if (size >= array.length) {
            resize();
        }
        //从右往左边循环，将元素逐个向右移一位
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        //空出的位置放入新元素
        array[index] = element;
        size++;
    }

    /* *
     * 功能描述: 删除数组元素  复杂度O(N) 将数组最后一个元素复制到要删除元素的位置。删除最后一个元素复杂度为O(1)
     * @param: [index] 根据下标
     * @return: int
     * @auther: pbj
     * @date: 2019/9/16 11:35
     */
    public int delete(int index) {
        //判断访问下标是否超出范围
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出数组实际元素范围");
        }
        int deleteElement = array[index];
        //从左边往右循环,将元素逐个想左边已一位
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deleteElement;
    }
    //数组扩容
    public void resize() {
        int[] arrayNew = new int[array.length * 2];
        //从旧数组复制到新数组
        System.arraycopy(array,0,arrayNew,0,array.length);
        array = arrayNew;
    }
    //打印数组
    public void printArray() {
        for (int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(10);
        array.insertArray(3,0);
        array.insertArray(7,1);
        array.insertArray(9, 2);
        array.insertArray(5, 3);
        array.insertArray(6, 1);//1号位插入元素6
        System.out.println("----数组打印输出----");
        array.printArray();
        Array array2 = new Array(4);
        array2.insertArray(3,0);
        array2.insertArray(7,1);
        array2.insertArray(9, 2);
        array2.insertArray(5, 3);
        array2.insertArray(6, 1);//1号位插入元素6
        System.out.println("----数组打印输出----");
        array2.printArray();
    }
}
