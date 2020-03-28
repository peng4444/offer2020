package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo328
 * @Author: pbj
 * @Date: 2020/1/13 14:19
 * @Description: TODO 328奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 */
public class Demo328 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //结点1作为奇数链的头 结点2作为偶数链的头.从第3个点开始遍历，依次轮流附在奇、偶链的后面.遍历完后，奇数链的尾连向偶链的头，偶链的尾为空， 返回奇数链的头
    public ListNode oddEventList(ListNode head) {
        if (head == null || head.next == null || head.next.next==null) {
            return head;
        }
        ListNode odd = head,even = head.next,evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
