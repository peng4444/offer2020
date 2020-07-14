package cn.offer2020.pbj.demo.leetcode.graph;

import javafx.util.Pair;

import java.util.*;

/**
 * @pClassName: Demo433
 * @author: pengbingjiang
 * @create: 2020/7/14 19:50
 * @description: TODO 433. 最小基因变化
 */
public class Demo433 {
    //邻接矩阵
    public int minMutation2(String start, String end, String[] bank) {
        if(start==null || end ==null){
            return -1;
        }
        if(bank.length == 0){
            return  -1;
        }
        List<String> bankList = new ArrayList<>();
        for(String b:bank){
            bankList.add(b);
        }
        if(!bankList.contains(end)) return -1;
        int len = start.length();
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        bankList.forEach(curr->{
            for(int i = 0;i<len;i++){
                String combo = curr.substring(0,i)+"*"+curr.substring(i+1,len);
                ArrayList<String> comboList = allComboDict.getOrDefault(combo,new ArrayList<>());
                comboList.add(curr);
                allComboDict.put(combo,comboList);
            }
        });
        // 广度优先遍历队列
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        HashMap<String, Boolean> hasVistedList = new HashMap<>();
        queue.add(new Pair<>(start, 1));
        hasVistedList.put(start, true);
        while(!queue.isEmpty()){
            Pair<String, Integer> node = queue.remove();
            String currWord = node.getKey();
            int level = node.getValue();
            for(int i = 0;i<len;i++){
                String currCombo = currWord.substring(0,i)+"*"+currWord.substring(i+1,len);
                ArrayList<String> currComboList = allComboDict.getOrDefault(currCombo,new ArrayList<>());
                for(String word:currComboList){
                    if(word.equals(end)) return level;
                    if(!hasVistedList.containsKey(word)){
                        queue.add(new Pair<>(word,level+1));
                        hasVistedList.put(word,true);
                    }
                }
            }
        }
        return -1;
    }

    public int minMutation(String start, String end, String[] bank) {
        if(start==null || end ==null){
            return -1;
        }
        if(bank.length == 0){
            return  -1;
        }
        // 用set判断是否存在
        Set<String> bankSet = new HashSet<>();
        bankSet.addAll(Arrays.asList(bank));
        // 放入本轮新可变的字符串
        Set<String> alreadyChange = new HashSet<>();
        alreadyChange.add(start);
        return minMutationHelper(end,bankSet,0,alreadyChange);
    }
    public int minMutationHelper(String end,Set<String> bank,int steps,Set<String> alreadyChange){
        // 若已处理完 直接返回
        if(alreadyChange.contains(end)){
            return steps;
        }
        Set<String> newSet = new HashSet<>();
        // 防止并发修改异常
        Set<String> bankClone = new HashSet<>();
        bankClone.addAll(bank);
        for(String  current : alreadyChange){
            for(String target: bank){
                // 若可以在一个字符间变为目标库中字符 则将其加入下一轮处理中
                // 并将库中的对应字符串删除
                if(changeable(current,target)){
                    newSet.add(target);
                    bankClone.remove(target);
                }
            }
        }
        // 如果下一轮没有要处理的字符串 则说明无法处理
        if(newSet.size() ==0){
            return -1;
        }

        return minMutationHelper(end,bankClone,steps+1,newSet);
    }
    // 遍历判断是否可以修改一个字符内变为目标字符串
    private boolean changeable(String current,String target){
        int count = 0;
        for(int i=0;i<current.length();i++){
            if(current.charAt(i) != target.charAt(i)){
                count+=1;
            }
            if(count>1){
                return false;
            }
        }
        return true;
    }

    public int minMutation1(String start, String end, String[] bank) {
        Set<String> banks = new HashSet<>(Arrays.asList(bank));
        if (!banks.contains(end)) return -1;
        Set<String> visited = new HashSet<>();
        Deque<String> deque = new ArrayDeque<>();
        deque.offer(start);
        visited.add(start);
        int ans = 0;
        while (!deque.isEmpty()) {
            ans++;
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String cur = deque.poll();
                for (String bank_ : banks) {
                    if (!visited.contains(bank_) && check(cur, bank_)) {
                        if (bank_.equals(end)) return ans;
                        visited.add(bank_);
                        deque.offer(bank_);
                    }
                }
            }
        }
        return -1;
    }
    private boolean check(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
