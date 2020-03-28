package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo206
 * @Author: pbj
 * @Date: 2019/12/11 11:20
 * @Description: TODO  反转链表
 */

public class Demo206 {
    class Node{
        private int val;
        private Node next;

        Node(int x) {
            val = x;
        }
    }
    /* *
     * 功能描述: 循环迭代
     * 时间复杂度O(n),空间复杂度O(1)
     * @param: [head]
     * @return: cn.offer2020.pbj.demo.leetcode.Demo206.Node
     * @auther: pbj
     * @date: 2019/12/11 11:28
     */
    public Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /* *
     * 功能描述: 递归实现
     * 时间复杂度O(n),空间复杂度O(n)
     * @param: [head]
     * @return: cn.offer2020.pbj.demo.leetcode.Demo206.Node
     * @auther: pbj
     * @date: 2019/12/11 11:31
     */
    public Node reverseList2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
