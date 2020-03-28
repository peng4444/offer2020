package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * List是Collection的字接口，功能扩展 List集合 有序，可重复 包括 ArrayList(异步处理) ,LinkedList
 * Vector(同步处理 包括（stack）)
 *
 * @author PBJ
 * public E get(int index) 取得索引编号的内容
 * public E set(int index,E element) 修改指定索引编号的内容
 * public ListIterator<E> listIterator() 为ListIterator 接口实例化
 * Vector与ArrayList,LinkedList的区别
 * Vector是线程安全的，增删改查的方法都有synchronized，效率低,可以使用Enumeration输出 ，Vector默认扩成原来的2倍
 * ArrayList是线程不安全，效率高的，ArrayList每次扩1.5倍
 * 共同点是：都是利用数组来实现
 * ArrayList与LinkList的区别
 * ArrayList底层是数组结构，查询和修改速度快
 * LinkList底层是链表结构，双向链表，增和删除比较快，查询和修改比较慢
 * 共同点是：都是线程不安全的
 * get/set：ArrayList直接通过下标在数组中取，LinkedList需要从头/尾遍历到index
 * add/remove：ArrayList需要将index之后的元素都复制一遍，如果需要扩容需要将原数组所有元素复制到新数组
 * LinkedList需要从头/尾遍历到该元素并操作前后节点
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> all = new ArrayList<String>();
        System.out.println("长度：" + all.size() + "，算法为空：" + all.isEmpty());
        all.add("Hello");
        all.add("Hello");
        all.add(" World");
        System.out.println("长度：" + all.size() + "，算法为空：" + all.isEmpty());
        for (int x = 0; x < all.size(); x++) {
            String str = all.get(x);
            System.out.println(str);
        }
        //使用LinkedList，查看	与ArrayList 的区别
        List<String> link = new LinkedList<String>();
        link.add("A");
        link.add("B");
        link.add("C");
        System.out.println(link);
        //
        System.out.println(all.get(0));
        all.set(2, "World");// 修改集合中的元素
        all.add(3, "nihao");
        all.remove(0);
        System.out.println(all.indexOf("Hello"));// index = 0
        all.add(3, "Hello");
        System.out.println(all.lastIndexOf("Hello"));// 倒序查找到的第一个元素的index
        System.out.println("-------Iterator输出---------");
        Iterator<String> iter = all.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            System.out.print(str + " -- ");
        }
        System.out.println();
        System.out.println("-------ListIterator输出，双向输出---------");
        ListIterator<String> Liter = all.listIterator();
        while (Liter.hasNext()) {
            String str = Liter.next();
            System.out.print(str + " -- ");
        }
        System.out.println();
        System.out.println("反向输出");
        while (Liter.hasPrevious()) {
            System.out.print(Liter.previous() + ",");
        }
        System.out.println();
        System.out.println("从index = 2的元素开始");
        ListIterator<String> Liter2 = all.listIterator(2);
        while (Liter2.hasNext()) {
            String str = Liter2.next();
            System.out.print(str + " -- ");
        }
        System.out.println();
        System.out.println("从index = 1的元素开始到index= 2的元素");
        List<String> Liter3 = all.subList(1, 2);// 其作用是返回一个以fromIndex为起始索引（包含），以toIndex为终止索引（不包含）的子列表（List）。
        System.out.println(Liter3);
        all.add("zainali");
        System.out.println();
        System.out.println("------subList查询过后--------");
        System.out.println(all);
    }

}
