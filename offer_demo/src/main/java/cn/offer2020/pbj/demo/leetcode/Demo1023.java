package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo1023
 * @Author: pbj
 * @Date: 2020/5/8 17:06
 * @Description: TODO 1023. 驼峰式匹配
 */
public class Demo1023 {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>(queries.length);
        for(int i = 0; i< queries.length; i++){
            ans.add(help(queries[i],pattern));
        }
        return ans;
    }
    public Boolean help(String queries,String pattern){
        char[] c1 = queries.toCharArray();
        char[] c2 = pattern.toCharArray();
        if(c2.length>c1.length){
            return false;
        }
        int index = 0;
        for (int i = 0; i < c1.length; i++) {
            if (index < c2.length && c1[i] == c2[index]) {
                index++;
            }else if(c1[i]>='A' && c1[i] <= 'Z'){
                return false;
            }
        }
        return index == c2.length;
    }

    public List<Boolean> camelMatch1(String[] queries, String pattern) {
        List<Boolean> res = new LinkedList<>();
        int len = pattern.length();
        for (String query: queries) {
            boolean isValid = true;
            int currIndex = 0;
            for (int j = 0; j < query.length(); j++) {

                if (currIndex < len && pattern.charAt(currIndex) == query.charAt(j))
                    currIndex++;
                else if (Character.isUpperCase(query.charAt(j))) {
                    isValid = false;
                    break;
                }
            }
            res.add(isValid && len == currIndex ? true : false);
        }
        return res;
    }
}
