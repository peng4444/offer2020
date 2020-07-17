package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo125
 * @Author: pbj
 * @Date: 2020/4/5 16:53
 * @Description: TODO 125.验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 */
public class Demo125 {

    //双指针大法好,受我一拜!
    public boolean isPalindrome2(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (right > left) {
            if ((chars[left] >= '0' && chars[left] <= '9') || (chars[left] >= 'a' && chars[left] <= 'z')) {
                if ((chars[right] >= '0' && chars[right] <= '9') || (chars[right] >= 'a' && chars[right] <= 'z')) {
                    if (chars[left] != chars[right]) {
                        return false;
                    } else {
                        left++;
                        right--;
                    }
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        return true;
    }

    public boolean isPalindrome1(String s) {
        if (s.length() == 0) {
            return true;
        }
        String low = s.toLowerCase();
        int i = 0;
        int j = low.length()-1;
        while (i < j) {
            if (!Character.isLetterOrDigit(low.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(low.charAt(j))) {
                j--;
                continue;
            }
            if (low.charAt(i) != low.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
    //
    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if(Character.isLetterOrDigit(c)){
                sb.append(c);
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}
