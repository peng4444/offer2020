package cn.offer2020.pbj.demo.job.jingdong;

/**
 * @ClassName: JingDong01
 * @Author: pbj
 * @Date: 2019/8/25 16:46
 * @Description: TODO https://www.nowcoder.com/discuss/232738
 */

import java.util.*;

/**
 * 题目描述： 合唱队的N名学生站成一排且从左到右编号为1到N，其中编号为i的学生身高为Hi。
 * 现在将这些学生分成若干组（同一组的学生编号连续），并让每组学生从左到右按身高从低到高进行排列，
 * 使得最后所有学生同样满足从左到右身高从低到高（中间位置可以等高），那么最多能将这些学生分成多少组？
 * 输入
 * 第一行包含一个整数N，1≤N≤105。
 * 第二行包含N个空格隔开的整数H1到HN，1≤Hi≤109。
 * 输出
 * 输出能分成的最多组数。
 * 样例输入
 * 4
 * 2 1 3 2
 * 样例输出
 * 2
 * 提示
 * 补充样例
 * 输入样例2
 * 10
 * 69079936 236011312 77957850 653604087 443890802 277126428 755625552 768751840
 * 993860213 882053548
 * 输出样例2
 * 6
 * 此时分组为：【69079936】【236011312 77957850】【653604087 443890802 277126428】
 * 【755625552】 【768751840】【 993860213 882053548】调整顺序后即可满足条件
 * @author silverzoey
 */
public class JingDong01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();// N名学生
        int[] req = new int[N];
        for (int i = 0; i < N; i++) {
            req[i] = sc.nextInt();
        }
        System.out.println(solution(req));
    }

    private static int solution(int[] req) {
        int count = 0;
        List<Help> sortList = new ArrayList();
        for (int i = 0; i < req.length; i++) {
            Help help = new Help(req[i], i);
            sortList.add(help);
        }
        Collections.sort(sortList, new Comparator<Help>() {
            public int compare(Help h1, Help h2) {
                return h1.getValue() - h2.getValue();
            }
        });
        // 编号最后出现的位置
        int[] arr = new int[sortList.size()];
        for (int i = 0; i < sortList.size(); i++) {
            arr[sortList.get(i).getIndex()] = i;
        }
        List<Integer> list = new ArrayList();
        int low = 0, high = 0;
        for (int i = 0; i < arr.length; i++) {
            high = Math.max(high, arr[i]);
            if (i == high) {
                list.add(high - low + 1);
                low = high + 1;
            }
        }
        return list.size();
    }

}

class Help {
    int value;
    int index;

    public Help(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}





