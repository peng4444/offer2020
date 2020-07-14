package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.*;

/**
 * @pClassName: Demo773
 * @author: pengbingjiang
 * @create: 2020/7/14 20:05
 * @description: TODO 773. 滑动谜题
 */
public class Demo773 {

    public int slidingPuzzle2(int[][] board) {
        int[][] dir = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        String end = "123450";
        String curr = toString(board);
        if (curr.equals(end)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(curr);
        Set<String> visited = new HashSet<>();
        visited.add(curr);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                for (int i = 0; i < curr.length(); i++) {
                    if (curr.charAt(i) != '0') continue; // 非0不做操作
                    for (int j : dir[i]) { // 获取0所在位置所有能移动的点
                        char[] nextValue = curr.toCharArray(); // 每次复制一份做操作
                        swap(nextValue, i, j); // 交换0和其可移动位置
                        String next = new String(nextValue);
                        if (next.equals(end)) return count + 1; // 谜板被解开
                        if (visited.contains(next)) continue; // 过滤已经访问数据
                        queue.offer(next);
                        visited.add(next);
                    }
                    break;
                }
            }
            count++;
        }
        return -1;
    }

    String toString(int[][] board) {
        char[] value = new char[6];
        for (int i = 0; i < 6; i++) value[i] = (char) (board[i / 3][i % 3] + '0');
        return new String(value);
    }

    void swap(char[] arr, int i, int j) {
        if (i != j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public int slidingPuzzle1(int[][] board) {
        int[][] dir = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int[] endBoard = {1, 2, 3, 4, 5, 0};
        Node curr = new Node(board);
        if (Arrays.equals(curr.board, endBoard)) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(curr);
        HashSet<Node> visited = new HashSet<>();
        visited.add(curr);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                for (int nextZeroPos : dir[curr.zeroPos]) {
                    int[] nextBoard = Arrays.copyOf(curr.board, 6);
                    swap(nextBoard, curr.zeroPos, nextZeroPos);
                    if (Arrays.equals(nextBoard, endBoard)) return count + 1;
                    Node next = new Node(nextBoard, nextZeroPos);
                    if (visited.contains(next)) continue;
                    queue.offer(next);
                    visited.add(next);
                }
            }
            count++;
        }
        return -1;
    }

    void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // 自定义类将当前的board和0的位置绑定，减少循环
    // 重写equals-hashCode是用于visited.contains()判断
    static class Node {
        int[] board;
        int zeroPos;

        public Node(int[][] board) {
            this.board = new int[6];
            for (int i = 0; i < 6; i++) {
                this.board[i] = board[i / 3][i % 3];
                if (this.board[i] == 0) this.zeroPos = i;
            }
        }

        public Node(int[] board, int zeroPos) {
            this.board = board;
            this.zeroPos = zeroPos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return zeroPos == node.zeroPos && Arrays.equals(board, node.board);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(zeroPos);
            result = 31 * result + Arrays.hashCode(board);
            return result;
        }
    }
}
