package cn.offer2020.pbj.demo.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: Demo131
 * @author: pengbingjiang
 * @create: 2020/7/27 20:10
 * @description: TODO 131.分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 */
public class Demo131 {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        dfs(s,0, new ArrayList<String>(), list);
        return list;
    }

    private void dfs(String s, int start, ArrayList<String> path, List<List<String>> list) {
        if (start == s.length()) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String s1 = s.substring(start, i + 1);
            if (!isPalindrome(s1)) {
                continue;
            }
            path.add(s1);
            dfs(s,i + 1, path, list);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //分治优化
    public List<List<String>> partition1(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int length = s.length();
        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        return partitionHelper(s, 0, dp);
    }

    private List<List<String>> partitionHelper(String s, int start, boolean[][] dp) {
        if (start == s.length()) {
            List<String> list = new ArrayList<>();
            List<List<String>> ans = new ArrayList<>();
            ans.add(list);
            return ans;
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                for (List<String> l : partitionHelper(s, i + 1, dp)) {
                    l.add(0, left);
                    ans.add(l);
                }
            }

        }
        return ans;
    }


}
