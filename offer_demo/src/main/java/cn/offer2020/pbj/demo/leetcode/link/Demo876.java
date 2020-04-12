package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo876
 * @Author: pbj
 * @Date: 2020/4/1 13:47
 * @Description: TODO 876. 链表的中间结点
 */
public class Demo876 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public ListNode middleNode(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
