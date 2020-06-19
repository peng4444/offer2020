package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo4
 * @Author: pbj
 * @Date: 2020/3/27 09:42
 * @Description: TODO 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 */
public class Demo4 {

    /*
     * 1.首先，让我们在任一位置 i 将 A(长度为m) 划分成两个部分：
     *            leftA            |                rightA
     *   A[0],A[1],...      A[i-1] |  A[i],A[i+1],...A[m - 1]
     * 由于A有m个元素，所以有m + 1中划分方式(i = 0 ~ m)
     * 我们知道len(leftA) = i, len(rightA) = m - i;
     * 注意：当i = 0时，leftA是空集，而当i = m时，rightA为空集。
     * 2.采用同样的方式，将B也划分为两部分：
     *            leftB            |                rightB
     *   B[0],B[1],...      B[j-1] |   B[j],B[j+1],...B[n - 1]
     *  我们知道len(leftA) = j, len(rightA) = n - j;
     *  将leftA和leftB放入一个集合，将rightA和rightB放入一个集合。再把这两个集合分别命名为leftPart和rightPart。
     *            leftPart         |                rightPart
     *   A[0],A[1],...      A[i-1] |  A[i],A[i+1],...A[m - 1]
     *   B[0],B[1],...      B[j-1] |  B[j],B[j+1],...B[n - 1]
     *   如果我们可以确认：
     *   1.len(leftPart) = len(rightPart); =====> 该条件在m+n为奇数时，该推理不成立
     *   2.max(leftPart) <= min(rightPart);
     *   median = (max(leftPart) + min(rightPart)) / 2;  目标结果
     *   要确保这两个条件满足：
     *   1.i + j = m - i + n - j(或m - i + n - j + 1)  如果n >= m。只需要使i = 0 ~ m，j = (m+n+1)/2-i =====> 该条件在m+n为奇数/偶数时，该推理都成立
     *   2.B[j] >= A[i-1] 并且 A[i] >= B[j-1]
     *   注意:
     *   1.临界条件：i=0,j=0,i=m,j=n。需要考虑
     *   2.为什么n >= m ? 由于0 <= i <= m且j = (m+n+1)/2-i,必须确保j不能为负数。
     *   按照以下步骤进行二叉树搜索
     *   1.设imin = 0,imax = m，然后开始在[imin,imax]中进行搜索
     *   2.令i = (imin+imax) / 2, j = (m+n+1)/2-i
     *   3.现在我们有len(leftPart) = len(rightPart)。而我们只会遇到三种情况：
     *      ①.B[j] >= A[i-1] 并且 A[i] >= B[j-1]  满足条件
     *      ②.B[j-1] > A[i]。此时应该把i增大。 即imin = i + 1;
     *      ③.A[i-1] > B[j]。此时应该把i减小。 即imax = i - 1;
     * */
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft;
                if (i == 0) {//A分成的leftA(空集) 和 rightA(A的全部)  所以leftPart = leftA(空集) + leftB,故maxLeft = B[j-1]。
                    maxLeft = B[j - 1];
                } else if (j == 0) { //B分成的leftB(空集) 和 rightB(B的全部)  所以leftPart = leftA + leftB(空集),故maxLeft = A[i-1]。
                    maxLeft = A[i - 1];
                } else { //排除上述两种特殊情况，正常比较
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) { //奇数，中位数正好是maxLeft
                    return maxLeft;
                }
                //偶数
                int minRight;
                if (i == m) {//A分成的leftA(A的全部) 和 rightA(空集)  所以rightPart = rightA(空集) + rightB,故minRight = B[j]。
                    minRight = B[j];
                } else if (j == n) {//B分成的leftB(B的全部) 和 rightB(空集)  所以rightPart = rightA + rightB(空集),故minRight = A[i]。
                    minRight = A[i];
                } else {//排除上述两种特殊情况，正常比较
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    //使用归并排序将两个数组排序。
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] temp=new int[nums1.length+nums2.length];
        int i=0;
        int j=0;
        int t=0;
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]<=nums2[j]){
                temp[t++]=nums1[i++];
            }else{
                temp[t++]=nums2[j++];
            }
        }
        while(i<nums1.length){
            temp[t++]=nums1[i++];
        }
        while(j<nums2.length){
            temp[t++]=nums2[j++];
        }
        double b=(temp[(temp.length-1)/2]+temp[temp.length/2])*1.0/2;
        return b;
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }
    //i: nums1的起始位置 j: nums2的起始位置
    public int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if( i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if( j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if(k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return findKth(nums1, i + k / 2, nums2, j , k - k / 2);
        }else{
            return findKth(nums1, i, nums2, j + k / 2 , k - k / 2);
        }
    }

    //自己写的低效算法 开辟一个新数组，将两个数组放入，并且排序取中位数。
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length+nums2.length];
        for(int i = 0;i<nums.length;i++){
            if(i<nums1.length){
                nums[i] = nums1[i];
            }else{
                nums[i] = nums2[i-nums1.length];
            }
        }
        Arrays.sort(nums);
        if(nums.length%2==1) return nums[nums.length/2]/1.0;
        else return (nums[nums.length/2-1]+nums[nums.length/2])/2.0;
    }
}
