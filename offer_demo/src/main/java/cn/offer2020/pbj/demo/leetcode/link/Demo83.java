package cn.offer2020.pbj.demo.leetcode.link;

import org.junit.Test;

/**
 * @ClassName: Demo83
 * @Author: pbj
 * @Date: 2020/1/13 10:51
 * @Description: TODO 83.删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 */
public class Demo83 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode first = head;
        while (first!=null && first.next != null) {
            if (first.next.val == first.val) {
                first.next = first.next.next;
            } else {
                first = first.next;
            }
        }
        return head;
    }

    //递归实现
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates1(head.next);
        if(head.val == head.next.val) head = head.next;
        return head;
    }

    @Test
    public void testDemo() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        deleteDuplicates(head);
    }

}
