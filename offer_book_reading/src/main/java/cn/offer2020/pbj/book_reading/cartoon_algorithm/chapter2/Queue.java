package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter2;

/**
 * @ClassName: Queue
 * @Author: pbj
 * @Date: 2019/9/16 13:55
 * @Description: TODO 队列操作  循环队列
 */
public class Queue {
    private int[] array;
    private int front;
    private int rear;

    public Queue(int capacity) {
        this.array = new int[capacity];
    }

    //入队
    public void enQueue(int element) throws Exception {
        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满");
        }
        array[rear]  = element;
        rear = (rear + 1) % array.length;
    }
    //出队
    public int deQueue() throws Exception {
        if (rear == front) {
            throw new Exception("队列已空");
        }
        int deQueueElement = array[front];
        front = (front+1)%array.length;
        return deQueueElement;
    }
    //打印队列
    public void printQueue() {
        for (int i = front; i != rear; i = (i + 1) % array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Queue queue = new Queue(6);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.deQueue();
        queue.deQueue();
        queue.printQueue();
    }
}
