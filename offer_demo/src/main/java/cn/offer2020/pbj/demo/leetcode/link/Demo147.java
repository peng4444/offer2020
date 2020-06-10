package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @ClassName: Demo147
 * @Author: pbj
 * @Date: 2020/5/22 11:57
 * @Description: TODO 147，148. 对链表进行插入排序,148. 排序链表
 */
public class Demo147 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //想要排序块，就要尽可能少的做比较
    //需要一个指针指向当前已排序的最后一个位置，这里用的是head指针
    //需要另外一个指针pre,每次从表头循环，这里用的是dummy表头指针。
    //每次拿出未排序的节点，先和前驱比较，如果大于或者等于前驱，就不用排序了，直接进入下一次循环
    //如果前驱小，则进入内层循环，依次和pre指针比较，插入对应位置即可。
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0), pre;
        dummy.next = head;
        //每次排序的是head.next.val
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            pre = dummy;
            //找到待插入位子
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }
            ListNode curr = head.next; //记录待插入节点
            head.next = curr.next;//将待插入节点从原链表删除
            curr.next = pre.next;//插入结点，连接待插节点及后方链表
            pre.next = curr;//插入结点，连接待插节点及前方链表
        }
        return dummy.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head != null) {
            if (curr.next == null || curr.next.val > head.val) {
                ListNode p = head.next;
                head.next = curr.next;
                curr.next = head;
                curr = dummy;
                head = p;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }


    /**
     * 参考：Sort List——经典（链表中的归并排序） https://www.cnblogs.com/qiaozhoulin/p/4585401.html
     * <p>
     * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，
     * 需要复制出相等的空间来进行赋值归并。对于链表，实际上是可以实现常数空间占用的（链表的归并
     * 排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后merge，分两段的方
     * 法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后
     * 慢的所在位置就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向
     * 较小的，且记录第一个节点，然后 两段的头一步一步向后走，p也一直向后走，总是指向较小节点，
     * 直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
     * <p>
     * 主要考察3个知识点，
     * 知识点1：归并排序的整体思想
     * 知识点2：找到一个链表的中间节点的方法
     * 知识点3：合并两个已排好序的链表为一个新的有序链表
     */
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    // 归并排序
    private ListNode mergeSort(ListNode head){
        // 如果没有结点/只有一个结点，无需排序，直接返回
        if (head==null||head.next==null) return head;
        // 快慢指针找出中位点
        ListNode slowp=head,fastp=head.next.next,l,r;
        while (fastp!=null&&fastp.next!=null){
            slowp=slowp.next;
            fastp=fastp.next.next;
        }
        // 对右半部分进行归并排序
        r=mergeSort(slowp.next);
        // 链表判断结束的标志：末尾节点.next==null
        slowp.next=null;
        // 对左半部分进行归并排序
        l=mergeSort(head);
        return mergeList(l,r);
    }
    // 合并链表
    private ListNode mergeList(ListNode l,ListNode r){
        // 临时头节点
        ListNode tmpHead=new ListNode(-1);
        ListNode p=tmpHead;
        while (l!=null&&r!=null){
            if (l.val<r.val){
                p.next=l;
                l=l.next;
            }else {
                p.next=r;
                r=r.next;
            }
            p=p.next;
        }
        p.next=l==null?r:l;
        return tmpHead.next;
    }

    //快排版本
    public ListNode sortList2(ListNode head) {
        if(head==null||head.next==null) return head;
        // 没有条件，创造条件。自己添加头节点，最后返回时去掉即可。
        ListNode newHead=new ListNode(-1);
        newHead.next=head;
        return quickSort(newHead,null);
    }
    // 带头结点的链表快速排序
    private ListNode quickSort(ListNode head,ListNode end){
        if (head==end||head.next==end||head.next.next==end) return head;
        // 将小于划分点的值存储在临时链表中
        ListNode tmpHead=new ListNode(-1);
        // partition为划分点，p为链表指针，tp为临时链表指针
        ListNode partition=head.next,p=partition,tp=tmpHead;
        // 将小于划分点的结点放到临时链表中
        while (p.next!=end){
            if (p.next.val<partition.val){
                tp.next=p.next;
                tp=tp.next;
                p.next=p.next.next;
            }else {
                p=p.next;
            }
        }
        // 合并临时链表和原链表，将原链表接到临时链表后面即可
        tp.next=head.next;
        // 将临时链表插回原链表，注意是插回！（不做这一步在对右半部分处理时就断链了）
        head.next=tmpHead.next;
        quickSort(head,partition);
        quickSort(partition,end);
        // 题目要求不带头节点，返回结果时去除
        return head.next;
    }
}
