package cn.offer2020.pbj.javabasis.java.basis1.link_13;
//用双端链表实现队列  FIFO先进先出
public class QueueLinkedList {

    private DoublePointLinkedList dp;

    public QueueLinkedList(){
        dp = new DoublePointLinkedList();
    }
    public void insert(Object data){
        dp.addTail(data);
    }

    public void delete(){
        dp.deleteHead();
    }

    public boolean isEmpty(){
        return dp.isEmpty();
    }

    public int getSize(){
        return dp.getSize();
    }

    public void display(){
        dp.display();
    }

}
