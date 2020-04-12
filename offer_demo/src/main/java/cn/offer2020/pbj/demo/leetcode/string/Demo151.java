package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo151
 * @Author: pbj
 * @Date: 2020/3/25 15:16
 * @Description: TODO 151. 翻转字符串里的单词
 */
public class Demo151 {

    public String reverseWords(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder(arr.length);
        for(int i = arr.length-1;i>=0;i--){
            if(!arr[i].equals("")){
                if(sb.length()>0){
                    sb.append(" ");
                }
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
