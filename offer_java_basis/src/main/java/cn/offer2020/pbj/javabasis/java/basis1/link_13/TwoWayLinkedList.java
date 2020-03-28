package cn.offer2020.pbj.javabasis.java.basis1.link_13;
//双向链表
//它可以从两个方向遍历

public class TwoWayLinkedList {
    private Node head;//表示链表头
    private Node tail;//表示链表尾
    private int size;//表示链表的节点个数

    private class Node{
        private Object data;
        private Node next;
        private Node prev;

        public Node(Object data){
            this.data = data;
        }
    }

    public TwoWayLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    //在链表头增加节点
    public void addHead(Object value){
        Node newNode = new Node(value);
        if(size == 0){
            head = newNode;
            tail = newNode;
            size++;
        }else{
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            size++;
        }
    }

    //在链表尾增加节点
    public void addTail(Object value){
        Node newNode = new Node(value);
        if(size == 0){
            head = newNode;
            tail = newNode;
            size++;
        }else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    //删除链表头
    public Node deleteHead(){
        Node temp = head;
        if(size != 0){
            head = head.next;
            head.prev = null;
            size--;
        }
        return temp;
    }

    //删除链表尾
    public Node deleteTail(){
        Node temp = tail;
        if(size != 0){
            tail = tail.prev;
            tail.next = null;
            size--;
        }
        return temp;
    }

    //获得链表的节点个数
    public int getSize(){
        return size;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return (size == 0);
    }

    //显示节点信息
    public void display(){
        if(size >0){
            Node node = head;
            int tempSize = size;
            if(tempSize == 1){//当前链表只有一个节点
                System.out.println("["+node.data+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.data+"->");
                }else if(node.next == null){
                    System.out.print(node.data+"]");
                }else{
                    System.out.print(node.data+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }

    }
}
