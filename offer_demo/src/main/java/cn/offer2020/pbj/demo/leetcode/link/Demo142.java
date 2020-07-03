package cn.offer2020.pbj.demo.leetcode.link;

import java.util.HashSet;

/**
 * @ClassName: Demo142
 * @Author: pbj
 * @Date: 2020/3/13 16:12
 * @Description: TODO 142.判断环形链表2
 */
public class Demo142 {

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //双指针
    public ListNode detectCycle1(ListNode head) {
        ListNode fast,slow;
        fast = slow = head;
        boolean hasCycle = false;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                hasCycle = true;
                break;
            }
        }
        //
        if(hasCycle){
            ListNode q = head;
            while(slow!=q){
                slow = slow.next;
                q = q.next;
            }
            return q;
        }else{
            return null;
        }
    }
    //hash
    public ListNode detectCycle(ListNode head) {
        if (head == null){
            return head;
        }
        HashSet<ListNode> listNodeHashSet = new HashSet<>();
        boolean result = false;
        while (listNodeHashSet.add(head)){
            if (head.next == null){
                result = true;
                break;
            }
            head = head.next;
        }
        return result ? null : head;
    }
}
