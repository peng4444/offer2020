package cn.offer2020.pbj.demo;

/**
 * @pClassName: PopSort
 * @author: pengbingjiang
 * @create: 2020/9/14 18:29
 * @description: TODO
 */
public class PopSort {

    public static int[] popSort(int[] nums){
        if(nums==null||nums.length==0) return new int[]{};
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,5,4};
        popSort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
    }
}
