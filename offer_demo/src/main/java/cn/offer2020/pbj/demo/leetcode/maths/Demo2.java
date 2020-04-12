package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo2
 * @Author: pbj
 * @Date: 2019/12/11 14:35
 * @Description: TODO两数相加
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
