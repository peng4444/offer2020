package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo82
 * @Author: pbj
 * @Date: 2020/4/1 13:01
 * @Description: TODO 82.删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 */
public class Demo82 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    //双指针
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = head;
        ListNode pre = dummyHead;
        while(cur!=null ){
            //循环找出重复元素的所有节点。
            while(cur.next!=null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // 有重复元素， 去重。
            if(pre.next!= cur){
                cur= cur.next;
                pre.next = cur;
            }else{    //无重复元素，正常遍历。
                cur = cur.next;
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }


    //比较好理解的递归
    public static ListNode deleteDuplicates(ListNode head) {
        //baseCase
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        //如果是这种情况
        //      1 --> 1 --> 1 --> 2 --> 3
        //     head  next
        //1.则需要移动next直到出现与当前head.value不相等的情况（含null）
        //2.并且此时的head已经不能要了，因为已经head是重复的节点
        //--------------else-------------
        //      1 --> 2 --> 3
        //     head  next
        //3.如果没有出现1的情况，则递归返回的节点就作为head的子节点
        if (head.val == next.val) {
            //1
            while (next != null && head.val == next.val) {
                next = next.next;
            }
            //2
            head = deleteDuplicates(next);
        } else {
            //3
            head.next = deleteDuplicates(next);
        }
        return head;
    }
}
