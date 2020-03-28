package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo48
 * @Author: pbj
 * @Date: 2019/9/3 21:38
 * @Description: TODO  多维数组顺时针旋转
 */
public class Demo48 {

    public void rotate(int[][] matrix) {
        for (int layer = 0; layer < matrix.length / 2; layer++) {
            rotateOneLayer(matrix,layer,matrix.length-layer*2);
        }
    }

    private void rotateOneLayer(int[][] matrix, int offset, int size) {
        for (int pos = 0; pos < size - 1; ++pos) {
            int temp = matrix[offset][offset + pos];
            matrix[offset][offset + pos] = matrix[offset + size - 1 - pos][offset];
            matrix[offset + size - 1 - pos][offset] = matrix[offset + size - 1][offset + size - 1 - pos];
            matrix[offset + size - 1][offset + size - 1 - pos] = matrix[offset + pos][offset + size - 1];
            matrix[offset + pos][offset + size - 1] = temp;
        }
    }
}
