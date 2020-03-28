package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter6;

/**
 * @ClassName: BitMap
 * @Author: pbj
 * @Date: 2020/3/19 15:47
 * @Description: TODO BitMap 位运算存储用户画像信息
 * 想要深入研究Bitmap算法的读者，可以看一下JDK中BitSet类的源码。同时，缓存数据库Redis中也有对Bitmap算法的支持。
 */
public class BitMap {
    // 每一个word是一个long类型元素，对应一个64位二进制数据
    private long[] words;
    //Bitmap的位数大小
    private int size;

    public BitMap(int size) {
        this.size = size;
        this.words = new long[(getWordIndex(size - 1) + 1)];
    }

    /*** 判断Bitmap某一位的状态* @param bitIndex 位图的第bitIndex位*/
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException(" 超过Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    /*** 把Bitmap某一位设置为tru* @param bitIndex 位图的第bitIndex位*/
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException(" 超过Bitmap有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    /*** 定位Bitmap某一位所对应的word* @param bitIndex 位图的第bitIndex位*/
    private int getWordIndex(int bitIndex) {
        //右移6位，相当于除以64
        return bitIndex >> 6;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(128);
        bitMap.setBit(126);
        bitMap.setBit(75);
        System.out.println(bitMap.getBit(126));
        System.out.println(bitMap.getBit(78));
    }
}
