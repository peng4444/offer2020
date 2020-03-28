package cn.offer2020.pbj.demo.job.tencent;

import org.junit.Test;

import java.util.Scanner;
import java.util.Stack;

/**
 * @ClassName: LookHeading
 * @Author: pbj
 * @Date: 2020/3/3 11:22
 * @Description: TODO 腾讯 牛客 逛街
 * https://www.nowcoder.com/questionTerminal/35fac8d69f314e958a150c141894ef6a?f=discussion
 */
public class LookHeading {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }
        //return
        int[] rightLook = new int[len];
        //stack 保存能够看到的楼的index
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            rightLook[i] = stack.size();
            while ((!stack.isEmpty()) && (arr[i] >= arr[stack.peek()])) {
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i < len; i++) {
            int total = rightLook[i] + 1 + stack.size();
            while ((!stack.isEmpty()) && (arr[i] >= arr[stack.peek()])) {
                stack.pop();
            }
            System.out.print(total+" ");
            stack.push(i);
        }
    }

    public int[] lookHeading(int len, int[] arr) {
        int[] rightLook = new int[len];
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            rightLook[i] = stack.size();
            while ((!stack.isEmpty()) && (arr[i] >= arr[stack.peek()])) {
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i < len; i++) {
            int total = rightLook[i] + 1 + stack.size();
            while ((!stack.isEmpty()) && (arr[i] >= arr[stack.peek()])) {
                stack.pop();
            }
            ans[i] = total;
            stack.push(i);
        }
        return ans;
    }

    @Test
    public void test() {
        int[] array = new int[]{5,3,8,3,2,5};
        int[] lookHeading = lookHeading(6, array);
        for (int i = 0; i < lookHeading.length; i++) {
            System.out.print(lookHeading[i]+" ");
        }
    }
}
