package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter2;

/**
 * @ClassName: Link
 * @Author: pbj
 * @Date: 2019/9/16 11:37
 * @Description: TODO
 */
public class Link {
    //单向链表
    private static class Node { // 定义一个链表节点
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }
    //头节点指针
    private Node head;
    //尾节点指针
    private Node last;
    //链表实际长度
    private int size;

    //插入链表
    public void insertList(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertedNode = new Node(data);
        if (size == 0) {
            //空链表
            head = insertedNode;
            last = insertedNode;
        } else if (index == 0) {
            //插入头部
            insertedNode.next = head;
            head = insertedNode;
        } else if (size == index) {
            //插入尾部
            last.next = insertedNode;
            last = insertedNode;
        }else {
            //插入中间
            Node preNode = getList(index - 1);
            insertedNode.next = preNode.next;
            preNode.next = insertedNode;
        }
        size++;
    }

    //删除链表
    public Node remodeList(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node removeNode = null;
        if (index == 0) {
            //删除头节点
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            //删除尾节点
            Node preNode = getList(index - 1);
            removeNode = preNode.next;
            preNode.next = null;
            last = preNode;
        } else {
            //删除中间节点
            Node preNode = getList(index - 1);
            Node nextNode = preNode.next.next;
            removeNode = preNode.next;
            preNode.next = nextNode;
        }
        size--;
        return removeNode;
    }

    // 链表查找元素
    public Node getList(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //打印链表
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Link link = new Link();
        link.insertList(3,0);
        link.insertList(7, 1);
        link.insertList(9,2);
        link.insertList(5, 3);
        link.insertList(6,1);
        link.remodeList(0);
        link.printList();
    }
}
