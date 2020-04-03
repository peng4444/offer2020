package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: LRUCache
 * @Author: pbj
 * @Date: 2020/3/19 15:56
 * @Description: TODO LRU
 */
public class LRUCache {
    class Node {

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Node pre;
        public Node next;
        public String key;
        public String value;
    }

    private Node head;private Node end;
    // 缓存存储上限
    private int limit;
    private HashMap<String, Node> hashMap;

    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            //如果Key 不存在，则插入Key-Value
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            //如果Key 存在，则刷新Key-Value
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    /*** 刷新被访问的节点位* @param node 被访问的节点*/
    private void refreshNode(Node node) {
        //如果访问的是尾节点，则无须移动节点
        if (node == end) {
            return;
        }
        //移除节点
        removeNode(node);
        //重新插入节点
        addNode(node);
    }

    /*** 删除节点* @param node 要删除的节点*/
    private String removeNode(Node node) {
        if (node == head && node == end) {
            //移除唯一的节点
            head = null;
            end = null;
        } else if (node == end) {
            //移除尾节点
            end = end.pre;
            end.next = null;
        } else if (node == head) {
            //移除头节点
            head = head.next;
            head.pre = null;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /*** 尾部插入节点* @param node 要插入的节点*/
    private void addNode(Node node) {
        if (end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", " 用户1信息");
        lruCache.put("002", " 用户1信息");
        lruCache.put("003", " 用户1信息");
        lruCache.put("004", " 用户1信息");
        lruCache.put("005", " 用户1信息");
        lruCache.get("002");
        lruCache.put("004", " 用户2信息更新");
        lruCache.put("006", " 用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
//题目要求实现 LRU 缓存机制，需要在 O(1)时间内完成如下操作：
//有一种叫做有序字典的数据结构，综合了哈希表和链表，在 Java 中为 LinkedHashMap。
class LRUCache1 extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache1(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

