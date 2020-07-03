package cn.offer2020.pbj.demo.leetcode.queue_stack;

/**
 * @pClassName: Demo641
 * @author: pengbingjiang
 * @create: 2020/7/1 19:32
 * @description: TODO 641. 设计循环双端队列
 */
public class Demo641 {
    //数组实现
    private int[] element;
    private int size;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public Demo641(int k) {
        element = new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull())return false;
        for(int i = size-1;i>=0;i--){
            element[i+1] = element[i];
        }
        element[0] = value;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        element[size] = value;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) return false;
        element[0] = 0;
        for(int i = 0;i<size-1;i++){
            element[i] = element[i+1];
        }
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) return false;
        element[size-1] = 0;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return size==0?-1:element[0];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return size==0?-1:element[size-1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size==element.length;
    }
}
//链表实现
class MyCircularDeque {
    class Node {
        private int val;
        private Node next;
        private Node pre;

        public Node(int val, Node next, Node pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
    }

    private int size;
    private int current = 0;
    private Node head;
    private Node tail;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        this.size = k;
        this.current = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (current == size) {
            return false;
        }
        if (head == null) {
            head = new Node(value, null, null);
            tail = head;
        } else {
            Node node = new Node(value, head, null);
            head.pre = node;
            head = node;
        }
        current++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (current == size) {
            return false;
        }
        if (tail == null) {
            tail = new Node(value, null, null);
            head = tail;
        } else {
            Node node = new Node(value, null, tail);
            tail.next = node;
            tail = node;
        }
        current++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (current == 0) {
            return false;
        }
        if (current == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.pre = null;
        }
        current--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (current == 0) {
            return false;
        }
        if (current == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        current--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (current == 0) {
            return -1;
        }
        return head.val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (current == 0) {
            return -1;
        }
        return tail.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return current == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return current == size;
    }
}
