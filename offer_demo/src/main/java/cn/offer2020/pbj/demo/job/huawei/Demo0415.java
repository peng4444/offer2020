package cn.offer2020.pbj.demo.job.huawei;

import java.util.*;

/**
 * @ClassName: Demo0415
 * @Author: pbj
 * @Date: 2020/4/16 08:59
 * @Description: TODO 20200415
 */
public class Demo0415 {

    public void demo1() {
        Scanner sc = new Scanner(System.in);
        String src = sc.next();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (c == ',' || c >= 'a' || c <= 'z' || c >= 'A' || c <= 'Z') {

            } else {
                System.out.println("error.0001");
                return;
            }
        }
        String[] split = src.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String s : split) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list, (e1, e2) -> {
            if (e1.getValue() > e2.getValue()) return -1;
            else if (e1.getValue() < e2.getValue()) return 1;
            else return e1.getKey().compareTo(e2.getKey());
        });
        System.out.println(list.get(0).getKey());
    }

    public static boolean[] b;
    public static int[] array;
    public static int[][] mat;
    public static int max;

    public void demo3() {
        max = 0;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        String[] strings = sc.nextLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        b = new boolean[n];
        mat = new int[n][n];
        array = new int[n];
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            if (s.length == 1) {
                flag = false;
                break;
            } else {
                int temp = Integer.parseInt(s[1]);
                array[i] = temp;
                String[] str = Arrays.copyOfRange(s, 2, s.length);
                for (int i1 = 0; i1 < str.length; i1++) {
                    int y = Integer.parseInt(str[i1]) - 1;
                    if (y >= n || y < 0) {
                        break;
                    }
                    mat[i][y] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            b[i] = true;
            int sum = array[i];
            int[] temp = mat[i];
            for (int j = 0; j < temp.length; j++) {
                if (b[j] == false && temp[j] == 1) {
                    b[j] = true;
                    dfs(j, sum + array[i]);
                    b[j] = false;
                }
            }
        }
        if (flag == false) {
            System.out.println("NA");
        } else {
            System.out.println(max);
        }
    }

    public void dfs(int i, int sum) {
        if (!isTrue(i)) {
            max = Math.max(max, sum);
        }
        int[] temp = mat[i];
        for (int j = 0; j < temp.length; j++) {
            if (b[j] == false && temp[j] == 1) {
                b[j] = true;
                dfs(j, sum + array[j]);
                b[j] = false;
            }
        }
    }

    public boolean isTrue(int i) {
        int[] res = mat[i];
        for (int i1 = 0; i1 < res.length; i1++) {
            if (res[i1] == 1 && b[i1] == false) {
                return true;
            }
        }
        return false;
    }

}
