package cn.offer2020.pbj.javabasis.java.basis1.link_13;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName: PriorityQueueDemo
 * @Author: pbj
 * @Date: 2019/12/12 19:55
 * @Description: TODO 优先队列(https://blog.csdn.net/ayzxx/article/details/82810009)
 */
public class PriorityQueueDemo {
    private int id;
    private String name;

    public PriorityQueueDemo(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        String st = id+" "+name;
        return st;

    }

    public static void main(String[] args) {
        //这个是匿名类实现Comparator接口 中compare方法。
        Comparator<PriorityQueueDemo> compare = new Comparator<PriorityQueueDemo>() {
            //定义id为他们的比较级
            public int compare(PriorityQueueDemo number1,PriorityQueueDemo number2) {
                if(number1.getId()<=number2.getId())
                    return 1;
                else return -1;
            }
        };
        PriorityQueue<PriorityQueueDemo> priorityqueue = new PriorityQueue<PriorityQueueDemo>(compare);
        PriorityQueueDemo t1 = new PriorityQueueDemo(1, "张三");
        PriorityQueueDemo t2 = new PriorityQueueDemo(2, "张三");
        PriorityQueueDemo t3 = new PriorityQueueDemo(3, "张三");
        PriorityQueueDemo t4 = new PriorityQueueDemo(4, "张三");
        PriorityQueueDemo t5 = new PriorityQueueDemo(5, "张三");
        priorityqueue.add(t1);
        priorityqueue.add(t2);
        priorityqueue.add(t3);
        priorityqueue.add(t4);
        priorityqueue.add(t5);
        int number = priorityqueue.size();
        for(int i=0;i<number;i++)
        {
            //取队元素，并把队首元素从队伍里删除。
            PriorityQueueDemo t = priorityqueue.poll();
            System.out.println(t.toString());
        }
    }
}
