package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo234
 * @Author: pbj
 * @Date: 2020/1/13 19:23
 * @Description: TODO  判断是否是回文链表
 */
public class Demo234 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //使用快慢指针找到链表的中间位置；转后半部分链表；逐一对比前后两部分链表；
    public boolean isPalindrome(ListNode head) {
        // 边界条件：空链表或只有一个节点的链表
        if (head == null || head.next == null) {
            return true;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        //慢指针一次走一步，快指针一次走两步，当快指针走到终点，慢指针刚好处于中点位置
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast指针置于下半段链表的起点
        fast = slow.next;
        // 断开前后两个链表
        slow.next = null;
        // slow指针置于前半段链表的起点
        slow = dummyNode.next;
        // 反转后半段链表
        ListNode pre = null; // 保存指针前一节点的信息，用于反转
        while (fast != null) {
            ListNode nextTemp = fast.next;
            fast.next = pre;
            pre = fast;
            fast = nextTemp;
        }

        //前后半链表逐一比较，当链表长度为奇数时前半段链表长度比后半段多1，所以以后半段为准
        while (pre != null) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }
}
