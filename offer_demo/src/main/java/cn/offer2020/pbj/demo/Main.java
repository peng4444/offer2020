package cn.offer2020.pbj.demo;

import java.util.*;

/**
 * @pClassName: Main
 * @author: pengbingjiang
 * @create: 2020/9/12 19:45
 * @description: TODO
 */
public class Main {
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            str = str.replace("coc","");
            System.out.println(str);

    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String input = sc.nextLine();
        text = text.replace("\"","");
        input = input.replace("\"","");
        String[] texts = text.split(" ");
        String[] inputs =  input.split(" ");
        Set<String> set1 = new HashSet<>();
        for(int i = 0;i<texts.length;i++){
            set1.add(texts[i]);
        }
        List<String> ans = new ArrayList<>();
        Set<String> set2 = new HashSet<>();
        for(int i = 0;i<inputs.length;i++){
            set2.add(inputs[i]);
        }
        for(int i = 0;i<texts.length;i++){
            if(!set2.contains(texts[i])){
                ans.add(texts[i]);
            }
        }
        for(int i = 0;i<inputs.length;i++){
            if(!set1.contains(inputs[i])){
                ans.add(inputs[i]);
            }
        }
        for(int i = 0;i<ans.size();i++){
            System.out.print(ans.get(i)+" ");
        }
    }
}
