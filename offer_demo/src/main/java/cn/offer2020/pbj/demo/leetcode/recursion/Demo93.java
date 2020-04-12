package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo93
 * @Author: pbj
 * @Date: 2020/4/9 15:12
 * @Description: TODO 93. 复原IP地址
 */
public class Demo93 {

    public List<String> restoreIpAddresses(String s) {
        List<String> addAddress = new ArrayList<>();
        StringBuilder tempAddress = new StringBuilder();
        doRestore(0,tempAddress,addAddress,s);
        return addAddress;
    }
    private void doRestore(int k,StringBuilder tempAddress,List<String> addAddress,String s){
        if(k==4||s.length()==0){
            if(k==4&&s.length()==0){
                addAddress.add(tempAddress.toString());
            }
            return;
        }
        for(int i=0;i<s.length()&&i<=2;i++){
            if(i!=0&&s.charAt(0)=='0'){
                break;
            }
            String part = s.substring(0,i+1);
            if(Integer.valueOf(part)<=255){
                if(tempAddress.length()!=0){
                    part = "."+part;
                }
                tempAddress.append(part);
                doRestore(k+1,tempAddress,addAddress,s.substring(i+1));
                tempAddress.delete(tempAddress.length()-part.length(),tempAddress.length());
            }
        }
    }
}
