package cn.offer2020.pbj.javabasis.java.basis1.link_13;
/*
 * 对于单项链表，我们如果想在尾部添加一个节点，
 * 那么必须从头部一直遍历到尾部，找到尾节点，
 * 然后在尾节点后面插入一个节点。这样操作很麻烦，
 * 如果我们在设计链表的时候多个对尾节点的引用，那么会简单很多。
 * 就是头尾都可以添加数据
 */
//双端链表的具体实现:就是头尾都可以添加数据
public class DoublePointLinkedList {
    private Node head;//头节点
    private Node tail;//尾节点
    private int size;//节点的个数

    private class Node{
        private Object data;
        private Node next;

        public Node(Object data){
            this.data = data;
        }
    }

    public DoublePointLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    //链表头新增节点
    public void addHead(Object data){
        Node node = new Node(data);
        if(size == 0){//如果链表为空，那么头节点和尾节点都是该新增节点
            head = node;
            tail = node;
            size++;
        }else{
            node.next = head;
            head = node;
            size++;
        }
    }

    //链表尾新增节点
    public void addTail(Object data){
        Node node = new Node(data);
        if(size == 0){//如果链表为空，那么头节点和尾节点都是该新增节点
            head = node;
            tail = node;
            size++;
        }else{
            tail.next = node;
            tail = node;
            size++;
        }
    }

    //删除头部节点，成功返回true，失败返回false
    public boolean deleteHead(){
        if(size == 0){//当前链表节点数为0
            return false;
        }
        if(head.next == null){//当前链表节点数为1
            head = null;
            tail = null;
        }else{
            head = head.next;
        }
        size--;
        return true;
    }
    //判断是否为空
    public boolean isEmpty(){
        return (size ==0);
    }
    //获得链表的节点个数
    public int getSize(){
        return size;
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

