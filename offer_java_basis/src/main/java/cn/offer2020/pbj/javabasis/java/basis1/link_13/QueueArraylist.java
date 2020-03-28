package cn.offer2020.pbj.javabasis.java.basis1.link_13;
/*
 * 基于数组实现循环队列
 * 由于在往队列中放数据或拉取数据的时候需要移动数组对应的下标，所以需要记录一下队尾和队头的位置
 * 1、该队列为非线程安全的，在多线程环境中可能会发生数据丢失等问题。
 * 2、队列通过移动指针来确定数组下标的位置，因为是基于数组实现的，所以队列的长度不能够超过数组的长度。
 * 3、该队列是循环队列，这就意味着数组可以重复被使用，避免了重复创建对象带来的性能的开销。
 * 4、添加数据时总是从队列尾部添加，拉取数据时总是从队列头部拉取，拉取完将对象元素删除。
 */
public class QueueArraylist {

    /**
     * 队列管道，当管道中存放的数据大于队列的长度时将不会再offer数据，直至从队列中poll数据后
     */
    private Object[] queue;
    //队列的头部，获取数据时总是从头部获取
    private int head;
    //队列尾部，push数据时总是从尾部添加
    private int tail;
    //队列长度
    private int size;
    //数组中能存放数据的最大容量
    private final static int MAX_CAPACITY = 1<<30;
    //数组长度
    private int capacity;
    //最大下标
    private int maxIndex;
    //创建一个最大长度为 initialCapacity的队列。
    public QueueArraylist(int initialCapacity){
        if (initialCapacity > MAX_CAPACITY)
            throw new OutOfMemoryError("initialCapacity too large");
        if (initialCapacity <= 0)
            throw new IndexOutOfBoundsException("initialCapacity must be more than zero");
        queue = new Object[initialCapacity];
        capacity = initialCapacity;
        maxIndex = initialCapacity - 1;
        head = tail = -1;
        size = 0;
    }
    //创建一个默认最大长度的队列，默认长度为16；
    public QueueArraylist(){
        queue = new Object[16];
        capacity = 16;
        head = tail = -1;
        size = 0;
        maxIndex = 15;
    }

    /**
     * 往队列尾部添加数据
     * @param object 数据
     */
    public void offer(Object object){
        if (size >= capacity){
            System.out.println("queue's size more than or equal to array's capacity");
            return;
        }
        if (++tail > maxIndex){
            tail = 0;
        }
        queue[tail] = object;
        size++;
    }

    /**
     * 从队列头部拉出数据
     * @return 返回队列的第一个数据
     */
    public Object poll(){
        if (size <= 0){
            System.out.println("the queue is null");
            return null;
        }
        if (++head > maxIndex){
            head = 0;
        }
        size--;
        Object old = queue[head];
        queue[head] = null;
        return old;
    }

    /**
     * 查看第一个数据
     * @return
     */
    public Object peek(){
        return queue[head];
    }

    /**
     * 队列中存储的数据量
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 清空队列
     */
    public void clear(){
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }
        tail = head = -1;
        size = 0;
    }

    @Override
    public String toString() {
        if (size <= 0) return "{}";
        StringBuilder builder = new StringBuilder(size + 8);
        builder.append("{");
        int h = head;
        int count = 0;
        while (count < size){
            if (++h > maxIndex) h = 0;
            builder.append(queue[h]);
            builder.append(", ");
            count++;
        }
        return builder.substring(0,builder.length()-2) + "}";
    }
    public static void main(String[] args) {

    }

}
