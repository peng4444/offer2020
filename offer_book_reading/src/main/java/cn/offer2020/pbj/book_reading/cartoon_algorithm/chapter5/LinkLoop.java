package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

/**
 * @ClassName: LinkLoop
 * @Author: pbj
 * @Date: 2019/10/16 00:00
 * @Description: TODO  如何判断链表有环 如果链表有环，求环长 求入环点
 * 求环长：当两个指针首次相遇，证明链表有环的时候，让两个指针从相遇点继续循环前进，并统计前进的循环次数，直到两个指针第2次相遇。此时，统计出来的前进次数就是环长。
 * 求入环点 ：只要把其中一个指针放回到头节点位置，另一个指针保持在首次相遇点，两个指针都是每次向前走1步。那么，它们最终相遇的节点，就是入环节点。
 */
public class LinkLoop {
    /* *
     * 功能描述: 如何判断链表有环
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/10/16 0:01
     * 方法1：首先从头节点开始，依次遍历单链表中的每一个节点。每遍历一个新节点，就
        从头检查新节点之前的所有节点，用新节点和此节点之前所有节点依次做比较。如
        果发现新节点和之前的某个节点相同，则说明该节点被遍历过两次，链表有环；如
        果之前的所有节点中不存在与新节点相同的节点，就继续遍历下一个新节点，继续
        重复刚才的操作。
       该解法的时间复杂度为O(n2) 空间复杂度为O(1)
     * 方法2：首先创建一个以节点ID为Key的HashSet集合，用来存储曾经遍历过的节点。然
        后同样从头节点开始，依次遍历单链表中的每一个节点。每遍历一个新节点，都用
        新节点和HashSet集合中存储的节点进行比较，如果发现HashSet中存在与之相同的
        节点ID，则说明链表有环，如果HashSet中不存在与新节点相同的节点ID，就把这
        个新节点ID存入HashSet中，之后进入下一节点，继续重复刚才的操作。
        解法的时间复杂度是O(n) 算法的空间复杂度同样是O(n)
     *  方法3：首先创建两个指针p1和p2（在Java里就是两个对象引用），让它们同时指向这
        个链表的头节点。然后开始一个大循环，在循环体中，让指针p1每次向后移动1个节
        点，让指针p2每次向后移动2个节点，然后比较两个指针指向的节点是否相同。如果
        相同，则可以判断出链表有环，如果不同，则继续下一次循环。
        算法的时间复杂度为O(n) 空间复杂度是O(1)
     */
    /*** 判断是否有环* @param head 链表头节点*/
    public static boolean isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }
    //链表开始入环的第一个节点
    public Node detectCycle(Node head) {
        Node fast,slow;
        fast = slow = head;
        boolean hasCycle = false;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                hasCycle = true;
                break;
            }
        }
        //
        if(hasCycle){
            Node q = head;
            while(slow!=q){
                slow = slow.next;
                q = q.next;
            }
            return q;
        }else{
            return null;
        }
    }
    //环形链表环的长度  fast和slow相遇后，fast不动了，然后slow指针继续遍历，同时计数，下次再相遇的时候计数值就是环长度。
    public int cycleLen(Node head) {
        Node fast,slow;
        fast = slow = head;
        boolean hasCycle = false;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                hasCycle = true;
                break;
            }
        }
        int count = 1;
        fast = fast.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return count;
            } else {
                fast = fast.next;
            }
        }
        return count;
    }
        /*** 链表节点*/
        private static class Node {
            int data;
            Node next;
            Node(int data) {
                this.data = data;
            }
        }
        public static void main(String[] args) throws Exception {
            Node node1 = new Node(5);
            Node node2 = new Node(3);
            Node node3 = new Node(7);
            Node node4 = new Node(2);
            Node node5 = new Node(6);
            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;
            node5.next = node2;
            System.out.println(isCycle(node1));
        }
}
