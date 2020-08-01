package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @pClassName: Demo836
 * @author: pengbingjiang
 * @create: 2020/8/1 18:34
 * @description: TODO 836.矩形重叠
 */
public class Demo836 {
    //可以先找他们相交的临界情况，
    //比如方块1的右边和方块2的左边重合的时候 这就是一个临界情况，1的左边和2的右边，同理，1的上边和2的下边，1的下边和2的上边 重合， 一共就这四种不相交的临界情况
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec2[1]>=rec1[3]||rec1[1]>=rec2[3]){
            return false;
        }
        if(rec1[0]>=rec2[2]||rec1[2]<=rec2[0]){
            return false;
        }
        return true;
    }

    public boolean isRectangleOverlap1(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }
}
