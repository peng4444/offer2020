package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo19
 * @Author: pbj
 * @Date: 2020/1/13 10:22
 * @Description: TODO  删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。给定的 n 保证是有效的。
 */
public class Demo19 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: //两次遍历算法 时间复杂度O(L)
     * @param: [head, n]
     * @return: cn.offer2020.pbj.demo.leetcode.a_link.Demo19.ListNode
     * @auther: pbj
     * @date: 2020/1/13 10:34
     */
    public ListNode removeNthFrommEnd1(ListNode head, int n) {
        //创建一个临时节点作为头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        //获取链表长度
        while (first != null) {
            length++;
            first = first.next;
        }
        length = length - n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        //删除节点
        first.next = first.next.next;
        return dummy.next;
    }

    //一次遍历算法 使用两个指针
    //第一个指针从列表的开头向前移动 n+1 步，而第二个指针将从列表的开头出发。现在，这两个指针被 n 个结点分开。
    // 我们通过同时移动两个指针向前来保持这个恒定的间隔，直到第一个指针到达最后一个结点。
    // 此时第二个指针将指向从最后一个结点数起的第 n 个结点。我们重新链接第二个指针所引用的结点的 next 指针
    // 指向该结点的下下个结点。
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
