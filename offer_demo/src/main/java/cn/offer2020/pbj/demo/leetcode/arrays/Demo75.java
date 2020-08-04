package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo75
 * @Author: pbj
 * @Date: 2020/4/6 11:11
 * @Description: TODO 75.颜色分类
 * 给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 */
public class Demo75 {
    //快速排序
    public void sortColors(int[] nums) {
        int zero = -1,one = 0,two = nums.length;
        while(one<two){
            if(nums[one]==0){
                swap(nums,++zero,one++);
            }else if(nums[one]==2){
                swap(nums,--two,one);
            }else{
                ++one;
            }
        }
    }

    //冒泡排序
    public void sortColors2(int[] nums) {
        for(int i = 0;i < nums.length - 1;i++) {
            for(int j = 0;j < nums.length - 1 - i;j++) {
                if(nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
                }
            }
        }
    }

    public void swap(int[] num, int i,int j){
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    //双指针
    public void sortColors1(int[] nums) {
        int low = 0, high = nums.length - 1;
        int i = 0;
        while(i <= high){
            if(nums[i] == 0){
                int tmp = nums[i];
                nums[i] = nums[low];
                nums[low] = tmp;
                ++low; ++i;
            }else if(nums[i] == 1){
                ++i;
            }else if(i <= high && nums[i] == 2){
                int tmp = nums[i];
                nums[i] = nums[high];
                nums[high] = tmp;
                --high;
            }
        }
    }

    //使用三路快排，定义当前移动索引指针cur，左边放0的索引l，右边放2的索引r
    public void sortColors4(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int l = -1;//定义左边索引位置
        int r = len - 1;//定义右边索引位置
        int cur = 0;//定义当前移动索引指针

        while (cur <= r) {//当前移动索引指针位置不能超过右索引指针访问位置
            if (nums[cur] == 0) {//如果当前访问元素是0
                l++;//要将一开始定义的-1变成0
                swap(nums, cur, l);
                cur++;
            } else if (nums[cur] == 1) {//如果当前访问元素是1
                cur++;
            } else {
                swap(nums, cur, r);//如果当前访问元素是2
                r--;
            }
        }
    }
}
