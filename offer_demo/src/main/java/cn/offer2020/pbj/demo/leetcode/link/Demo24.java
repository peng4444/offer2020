package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo24
 * @Author: pbj
 * @Date: 2019/12/11 11:34
 * @Description: TODO 两两交换链表中的节点
 */
public class Demo24 {
    static class Node {
        private int val;
        private Node next;

        Node(int x) {
            val = x;
        }
    }
    /* *
     * 功能描述: 递归实现
     * @param: [head]
     * @return: cn.offer2020.pbj.demo.leetcode.Demo24.Node
     * @auther: pbj
     * @date: 2019/12/11 12:40
     */
    public static Node swapPairs(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = head.next;
        head.next = swapPairs(pre.next);
        pre.next = head;
        return pre;
    }

    /* *
     * 功能描述: 非递归实现
     * @param: [head]
     * @return: cn.offer2020.pbj.demo.leetcode.Demo24.Node
     * @auther: pbj
     * @date: 2019/12/11 12:44
     */
    public static Node swapPairs2(Node head) {
        Node pre = null;
        pre.next = head;
        Node temp = pre;
        while (temp.next != null && temp.next.next != null) {
            Node start = temp.next;
            Node end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            temp = start;
        }
        return pre.next;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        System.out.println(swapPairs(node));
    }
}
