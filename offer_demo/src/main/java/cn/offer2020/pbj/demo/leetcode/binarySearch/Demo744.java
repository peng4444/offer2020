package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo744
 * @Author: pbj
 * @Date: 2020/4/8 11:48
 * @Description: TODO 744. 寻找比目标字母大的最小字母
 */
public class Demo744 {
    //记录存在的字母
    public char nextGreatestLetter2(char[] letters, char target) {
        boolean[] seen = new boolean[26];
        for (char c: letters)
            seen[c - 'a'] = true;

        while (true) {
            target++;
            if (target > 'z') target = 'a';
            if (seen[target - 'a']) return target;
        }
    }
    //线性扫描
    public char nextGreatestLetter1(char[] letters, char target) {
        for (char c: letters)
            if (c > target) return c;
        return letters[0];
    }

    //二分查找 时间复杂度O(logN)
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        int low = 0, high = len -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(letters[mid]<=target){
                low = mid +1;
            }else{
                high = mid -1;
            }
        }
        return low < len?letters[low]:letters[0];
    }
}
