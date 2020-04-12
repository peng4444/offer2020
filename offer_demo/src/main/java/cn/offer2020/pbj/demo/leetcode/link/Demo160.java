package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo160
 * @Author: pbj
 * @Date: 2020/1/13 11:17
 * @Description: TODO 相交链表 编写一个程序，找到两个单链表相交的起始节点。
 */
public class Demo160 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，
    // 令它从链表 A 的头部开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

    //翻转链表，找到第一个不相同的链表，返回上一个节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        reverseListNode(headA);
        reverseListNode(headB);
        ListNode a = headA;
        ListNode b = headB;
        while (a.next != null && b.next!= null) {
            if (a.next.val != b.next.val) {
                return a;
            }
        }
        return null;
    }

    //链表翻转
    public ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseListNode(next);
        next.next = head;
        head.next = null;
        return newHead;
    }
}
