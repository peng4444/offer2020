package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @pClassName: Demo786
 * @author: pengbingjiang
 * @create: 2020/7/9 17:50
 * @description: TODO 786. 第K个最小的素数分数
 */
public class Demo786 {
    // 模板题，之前的思路考虑如何比对分数，这道题类似719题，
    // 先画个表格，找出规律，思路卡在了右界最大值，其实最大值可以设为1
    // 本来所有的值都在0-1区间内
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        double left = 0;
        double right = 1.0;
        while (left < right) {
            int p = 0, q = 0;
            int j = 1;
            double mid = (left + right) / 2;
            int count = 0, i = 0;
            double tmpMax = 0.0;
            for (; i < n - 1; i++) {
                while (j < n && A[i] > mid * A[j]) {
                    j++;
                }
                count += (n - j);
                if (j == n) break;
                if (A[i] * 1.0 / A[j] > tmpMax) {
                    tmpMax = A[i] * 1.0 / A[j];
                    p = i;
                    q = j;
                }
            }
            if (count == K) {
                return new int[]{A[p], A[q]};
            }
            if (count < K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return new int[]{};
    }

    class Solution {
        public int[] kthSmallestPrimeFraction(int[] A, int K) {
            if(A == null || A.length == 0) {
                return new int[0];
            }
            int len = A.length;
            int nodeCount = len*(len-1)/2;
            if(K > nodeCount) {
                return new int[0];
            }
            PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.val.compareTo(o1.val);
                }
            });
            for(int i = 0; i < len-1; i++) {
                for(int j = i+1; j < len; j++) {
                    Node node = new Node(A[i], A[j]);
                    if(queue.size() < K) {
                        queue.add(node);
                    } else if(queue.peek().val > node.val) {
                        queue.remove();
                        queue.add(node);
                    }
                }
            }

            Node rs = queue.peek();
            return new int[] {rs.fenzi, rs.fenmu};
        }

        class Node {
            Double val;
            int fenzi;
            int fenmu;
            Node(int fenzi, int fenmu) {
                this.fenzi = fenzi;
                this.fenmu = fenmu;
                val = (double)fenzi/(double)fenmu;
            }
        }
    }
}
