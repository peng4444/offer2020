package cn.offer2020.pbj.demo.leetcode.a_dp;

import java.util.*;

/**
 * @ClassName: Demo140
 * @Author: pbj
 * @Date: 2020/3/26 16:04
 * @Description: TODO
 */
public class Demo140 {

    class Solution {
        private int maxLength = -1;
        private Set<String> wordSet = new HashSet<>();
        private Map<Integer, List <String>> mapPosToStrings = new HashMap<>();

        List<String> calculateResult(String s, int currentPos) {
            if (currentPos == s.length()) {
                List<String> result = new ArrayList<>();
                result.add("");
                return result;
            }
            if (mapPosToStrings.containsKey(currentPos)) {
                return mapPosToStrings.get(currentPos);
            }
            List<String> result = new ArrayList<>();
            mapPosToStrings.put(currentPos, result);

            for (int i = 1; i <= maxLength && currentPos + i <= s.length(); i++) {
                String subString = s.substring(currentPos, currentPos + i);
                if (wordSet.contains(subString)) {
                    List<String> returnStrings = calculateResult(s, currentPos + i);
                    for (String returnString: returnStrings) {
                        if (returnString.equals("")) {
                            result.add(subString);
                        } else {
                            result.add(subString + " " + returnString);
                        }
                    }
                }
            }

            return result;
        }

        public List<String> wordBreak(String s, List<String> wordDict) {
            for (String word: wordDict) {
                wordSet.add(word);
                maxLength = Math.max(maxLength, word.length());
            }

            return calculateResult(s, 0);
        }
    }
    //动态规划 O(N^2)
    public List<String> wordBreak2(String s, Set<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }


    //记忆化回溯O(N^3)
    public List<String> wordBreak1(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }
    HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> word_Break1(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break1(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }


    //暴力法O(N^N)
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return word_Break(s, wordDict, 0);
    }
    public List<String> word_Break(String s, Set<String> wordDict, int start) {
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return res;
    }
}
