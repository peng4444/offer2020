package cn.offer2020.pbj.demo.leetcode.link;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo234
 * @Author: pbj
 * @Date: 2020/1/13 19:23
 * @Description: TODO  234.判断是否是回文链表
 */
public class Demo234 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //1.最简单的是用一个数组保存链表数据，再进行判断
    public boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
    //2.使用快慢指针找到链表的中间位置；转后半部分链表；逐一对比前后两部分链表；
    public boolean isPalindrome1(ListNode head) {
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

    //数学方法
    public boolean isPalindrome2(ListNode head) {
        float s1 = 0,s2 = 0,t = 1;

        while(head != null) {
            s1 = s1*10 + head.val;
            s2 = s2 + t*head.val;
            t = t*10;
            head = head.next;
        }
        return s1 == s2;
    }
}
