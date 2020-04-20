package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo769
 * @Author: pbj
 * @Date: 2020/4/19 10:24
 * @Description: TODO 769. 最多能完成排序的块
 * 数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 */
public class Demo769 {

    /* *
     * 功能描述: 首先找到从左块开始最小块的大小。如果前 k 个元素为 [0, 1, ..., k-1]，可以直接把他们分为一个块。
     * 当我们需要检查 [0, 1, ..., n-1] 中前 k+1 个元素是不是 [0, 1, ..., k] 的时候，只需要检查其中最大的数是不是 k 就可以了。
     * @param: [arr]
     * @return: int
     * @auther: pbj
     * @date: 2020/4/19 10:24
     */
    public int maxChunksToSorted(int[] arr) {
        if(arr==null) return 0;
        int ret = 0;
        int right = arr[0];
        for(int i = 0;i<arr.length;i++){
            right = Math.max(right,arr[i]);
            if(right==i) ret++;
        }
        return ret;
    }
}
