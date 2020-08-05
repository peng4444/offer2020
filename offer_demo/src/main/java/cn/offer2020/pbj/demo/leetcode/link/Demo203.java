package cn.offer2020.pbj.demo.leetcode.link;

/**
 * @pClassName: Demo203
 * @author: pengbingjiang
 * @create: 2020/8/5 10:11
 * @description: TODO 203. 移除链表元素
 */
public class Demo203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while(curr.next!=null){
            if(curr.next.val==val){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
