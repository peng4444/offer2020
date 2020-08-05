package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo61
 * @Author: pbj
 * @Date: 2020/4/1 10:53
 * @Description: TODO 61.旋转链表
 */
public class Demo61 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //先将链表闭合成环,找到相应的位置断开这个环，确定新的链表头和链表尾
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;
        if(head.next==null) return head;
        ListNode old_tail = head;
        int n;
        for(n = 1;old_tail.next!=null;n++){
            old_tail = old_tail.next;
        }
        old_tail.next = head;
        ListNode new_tail = head;
        for(int i = 0;i<n-k%n-1;i++){
            new_tail = new_tail.next;
        }
        ListNode new_head = new_tail.next;
        new_tail.next = null;
        return new_head;
    }

    //1.求出链表的长度len
    //2.k = k%len取余就是我们要右移的距离。
    //3.找到倒数第k个位置。可以使用双指针法。
    //4.记录慢指针的next节点，这就是最后要返回的节点。
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode tmp = head;
        int len = 0;
        //求出链表的长度
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        k = k % len;  //以len为一个周期
        if (k == 0) {
            return head;
        }
        //保存一下头节点
        ListNode node = head;
        //快慢指针
        tmp = head;
        while (k > 0) {
            k--;
            tmp = tmp.next;
        }
        while (tmp.next != null) {
            head = head.next;
            tmp = tmp.next;
        }
        //记录next的位置，也就是返回值的头结点
        ListNode res = head.next;
        //断开连接
        head.next = null;
        //后一段的末尾指向前一段的开头
        tmp.next = node;
        return res;
    }
}
