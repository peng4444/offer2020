package cn.offer2020.pbj.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Demo141
 * @Author: pbj
 * @Date: 2019/12/11 14:23
 * @Description: TODO 判断环形链表
 */
public class Demo141 {
    static class Node {
        private int val;
        private Node next;

        Node(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: 双指针
     * @param: [head]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/11 14:27
     */
    public boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /* *
     * 功能描述: 哈希表set集合实现
     * @param: [head]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/11 14:29
     */
    public boolean hasCycle2(Node head) {
        Set<Node> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return true;
    }
}
