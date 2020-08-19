package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo206
 * @Author: pbj
 * @Date: 2019/12/11 11:20
 * @Description: TODO  206.反转链表
 */

public class Demo206 {
    class ListNode{
        private int val;
        private ListNode next;

        ListNode(int x) {
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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; //前指针节点
        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
        while (curr != null) {
            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
            curr.next = prev; //将当前节点指向它前面的节点
            prev = curr; //前指针后移
            curr = nextTemp; //当前指针后移
        }
        return prev;
    }

    /* *
     * 功能描述: 递归实现
     * 时间复杂度O(n),空间复杂度O(n)
     * @param: [head]
     * @return: cn.offer2020.pbj.demo.leetcode.Demo206.Node
     * @auther: pbj
     * @date: 2019/12/11 11:31
     */
    public ListNode reverseList2(ListNode head) {
        ////1.递归结束条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
