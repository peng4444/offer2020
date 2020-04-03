package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo86
 * @Author: pbj
 * @Date: 2020/4/1 13:24
 * @Description: TODO 86. 分隔链表
 */
public class Demo86 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public ListNode partition(ListNode head, int x) {
        ListNode dummyHead1 = new ListNode(0);
        ListNode dummyHead2 = new ListNode(0);
        ListNode node1 = dummyHead1;
        ListNode node2 = dummyHead2;
        while(head!=null){
            if(head.val<x){
                node1.next = head;
                head = head.next;
                node1 = node1.next;
                node1.next = null;
            }else{
                node2.next = head;
                head = head.next;
                node2 = node2.next;
                node2.next = null;
            }
        }
        node1.next = dummyHead2.next;
        return dummyHead1.next;
    }
}
