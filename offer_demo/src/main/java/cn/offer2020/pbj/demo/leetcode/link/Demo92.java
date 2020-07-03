package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo92
 * @Author: pbj
 * @Date: 2020/5/11 11:42
 * @Description: TODO 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 */
public class Demo92 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 1;i<m;i++){
            pre = pre.next;
        }
        head = pre.next;
        for(int i = m;i<n;i++){
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return dummy.next;
    }

    class Solution {
        private boolean stop;
        private ListNode left;

        public void recurseAndReverse(ListNode right, int m, int n) {
            if (n == 1) {
                return;
            }
            right = right.next;

            if (m > 1) {
                this.left = this.left.next;
            }

            this.recurseAndReverse(right, m - 1, n - 1);

            if (this.left == right || right.next == this.left) {
                this.stop = true;
            }

            if (!this.stop) {
                int t = this.left.val;
                this.left.val = right.val;
                right.val = t;

                this.left = this.left.next;
            }
        }
        //递归实现
        public ListNode reverseBetween(ListNode head, int m, int n) {
            this.left = head;
            this.stop = false;
            this.recurseAndReverse(head, m, n);
            return head;
        }
    }
}
