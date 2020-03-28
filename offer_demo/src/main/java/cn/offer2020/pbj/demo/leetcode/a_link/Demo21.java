package cn.offer2020.pbj.demo.leetcode.a_link;

/**
 * @ClassName: Demo21
 * @Author: pbj
 * @Date: 2019/12/11 09:19
 * @Description: TODO 合并两个有序链表
 */


public class Demo21 {
    static class ListCode {
        int val;
        ListCode next;

        ListCode(int x) {
            val = x;
        }
    }

    public static ListCode mergeTwoList(ListCode l1, ListCode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListCode prev = null;
        ListCode root = l1.val < l2.val ? l1 : l2;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (prev == null) {
                    prev = l1;
                } else {
                    prev.next = l1;
                    prev = l1;
                }
                l1 = l1.next;
            } else {
                if (prev == null) {
                    prev = l2;
                } else {
                    prev.next = l2;
                    prev = l2;
                }
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            prev.next = l1;
            prev = l1;
            l1 = l1.next;
        }
        while (l2 != null) {
            prev.next = l2;
            prev = l2;
            l2 = l2.next;
        }
        return root;
    }

    /* *
     * 功能描述: 官方解答
     * 时间复杂度O(n+m) 空间复杂度 O(n+m)
     * @param: [l1, l2]
     * @return: cn.offer2020.pbj.demo.leetcode.ListCode
     * @auther: pbj
     * @date: 2019/12/11 9:53
     */
    public static ListCode mergeTwoLists(ListCode l1, ListCode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String args[]) {
        ListCode listCode = new ListCode(1);
        listCode.next = new ListCode(2);
        listCode.next.next = new ListCode(4);

        ListCode listCode1 = new ListCode(1);
        listCode1.next = new ListCode(3);
        listCode1.next.next = new ListCode(4);
        ListCode listCode2 = mergeTwoList(listCode, listCode1);
        if (listCode2.next != null) {
            System.out.print(listCode2.val + "->");
            listCode2 = listCode2.next;
        } else {
            System.out.println(listCode2.val);
        }
        System.out.println();
        System.out.println("--------------------------");
        ListCode listCode3 = mergeTwoLists(listCode, listCode1);
        if (listCode3.next != null) {
            System.out.print(listCode3.val + "->");
            listCode3 = listCode3.next;
        } else {
            System.out.println(listCode3.val);
        }
    }
}
