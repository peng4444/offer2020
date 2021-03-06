package cn.offer2020.pbj.demo.leetcode.link;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName: Demo23
 * @Author: pbj
 * @Date: 2020/5/21 11:30
 * @Description: TODO 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 */
public class Demo23 {
    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //最小堆解法
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        if(lists.length==1) return lists[0];
        if(lists.length==2) return mergeTwoLists(lists[0],lists[1]);
        int mid = lists.length/2;
        ListNode[] l1 = new ListNode[mid];
        for(int i = 0;i<mid;i++){
            l1[i] = lists[i];
        }
        ListNode[] l2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
    }
    //合并两个链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = null;
        if (l1.val <= l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int len = lists.length;
        for (int step = 1; step < len; step++) {
            for (int i = step; i < len; i += 2 * step) {
                lists[i - step] = mergeTwoLists(lists[i - step], lists[i]);
                lists[i] = null;
            }
        }
        return lists[0];
    }

    //用容量为K的最小堆优先队列，把链表的头结点都放进去，然后出队当前优先队列中最小的，挂上链表，
    //然后让出队的那个节点的下一个入队，再出队当前优先队列中最小的，直到优先队列为空。
    public ListNode mergeKLists2(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }
}
