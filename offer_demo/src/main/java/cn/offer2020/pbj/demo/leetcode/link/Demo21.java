package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo21
 * @Author: pbj
 * @Date: 2019/12/11 09:19
 * @Description: TODO 21.合并两个有序链表
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class Demo21 {
    static class ListCode {
        int val;
        ListCode next;

        ListCode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: 官方解答
     * 时间复杂度O(n+m) 空间复杂度 O(n+m)
     * @param: [l1, l2]
     * @return: cn.offer2020.pbj.demo.leetcode.ListCode
     * @auther: pbj
     * @date: 2019/12/11 9:53
     */
    public static ListCode mergeTwoLists(ListCode l1, ListCode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 任一为空，直接连接另一条链表
        cur.next = l1==null? l2: l1;
        return dummyHead.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            }else{
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            curr.next = l2;
        }else{
            curr.next = l1;
        }
        return dummy.next;
    }
}
