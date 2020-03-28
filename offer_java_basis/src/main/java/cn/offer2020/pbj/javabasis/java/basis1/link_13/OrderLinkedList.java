package cn.offer2020.pbj.javabasis.java.basis1.link_13;
//有序链表
//在有序链表中，数据是按照关键值有序排列的。
//一般在大多数需要使用有序数组的场合也可以使用有序链表。
//有序链表优于有序数组的地方是插入的速度（因为元素不需要移动），
//另外链表可以扩展到全部有效的使用内存，而数组只能局限于一个固定的大小中。
/**
 * 在有序链表中插入和删除某一项最多需要O(N)次比较，平均需要O(N/2)次，
 * 因为必须沿着链表上一步一步走才能找到正确的插入位置，然而可以最快速度删除最值，
 * 因为只需要删除表头即可，如果一个应用需要频繁的存取最小值，
 * 且不需要快速的插入，那么有序链表是一个比较好的选择方案。比如优先级队列可以使用有序链表来实现。
 * @author PBJ
 *
 */
public class OrderLinkedList {
    private Node head;

    private class Node{
        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
        }
    }

    public OrderLinkedList(){
        head = null;
    }

    //插入节点，并按照从小打到的顺序排列
    public void insert(int value){
        Node node = new Node(value);
        Node pre = null;
        Node current = head;
        while(current != null && value > current.data){
            pre = current;
            current = current.next;
        }
        if(pre == null){
            head = node;
            head.next = current;
        }else{
            pre.next = node;
            node.next = current;
        }
    }

    //删除头节点
    public void deleteHead(){
        head = head.next;
    }

    public void display(){
        Node current = head;
        while(current != null){
            System.out.print(current.data+" ");
            current = current.next;
        }
        System.out.println("");
    }

}
