package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo2
 * @Author: pbj
 * @Date: 2019/12/11 14:35
 * @Description: TODO 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 */
public class Demo2 {
    static class Node {
        private int val;
        private Node next;

        Node(int x) {
            val = x;
        }
    }

    public Node addTwoNumbers(Node l1, Node l2) {
        Node head = new Node(0);
        Node p = l1;
        Node q = l2;
        Node curr = head;
        int carry = 0;
        while (p!=null || q!= null){
            int x = (p!=null)?p.val:0;
            int y = (q!=null)?q.val:0;
            int sum = carry+x+y;
            carry = sum/10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            if(p!=null) p = p.next;
            if(q!=null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        return head.next;

    }
}
