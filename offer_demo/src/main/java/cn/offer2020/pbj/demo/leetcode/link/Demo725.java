package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo725
 * @Author: pbj
 * @Date: 2020/3/11 14:36
 * @Description: TODO 725. 分隔链表
 */
public class Demo725 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //创建新列表
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur!=null){
            cur = cur.next;
            N++;
        }
        int width = N/k,rem = N%k;
        ListNode[] ans = new ListNode[k];
        cur = root;
        for(int i = 0;i<k;i++){
            ListNode head = new ListNode(0),write = head;
            for(int j= 0;j<width+(i<rem?1:0);j++){
                write = write.next = new ListNode(cur.val);
                if(cur!=null){
                    cur = cur.next;
                }
                ans[i] = head.next;
            }
        }
        return ans;
    }
    //拆分链表
    public ListNode[] splitListToParts2(ListNode root, int k) {
        ListNode cur = root;
        int N = 0;
        while (cur!=null){
            cur = cur.next;
            N++;
        }
        int width = N/k,rem = N%k;
        ListNode[] ans = new ListNode[k];
        cur = root;
        for(int i = 0;i<k;i++){
            ListNode head = cur;
            for(int j= 0;j<width+(i<rem?1:0);j++){
                if(cur!=null){
                    cur = cur.next;
                }
            }
            if(cur!=null){
                ListNode pre = cur;
                cur = cur.next;
                pre.next = null;
            }
            ans[i] = head;
        }
        return ans;
    }
}
