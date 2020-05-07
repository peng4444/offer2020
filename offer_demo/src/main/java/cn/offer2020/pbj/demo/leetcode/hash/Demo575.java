package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName: Demo575
 * @Author: pbj
 * @Date: 2020/5/7 11:23
 * @Description: TODO 575. 分糖果
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。
 * 返回妹妹可以获得的最大糖果的种类数。
 */
public class Demo575 {
    //集合set
    //找到唯一元素数量的另一种方法是遍历给定 candiescandies 数组的所有元素，并继续将元素放入集合中。通过集合的属性，它将只包含唯一的元素。
    // 最后，我们可以计算集合中元素的数量，例如 countcount。要返回的值将再次由 \text{min}(count, n/2)min(count,n/2) 给出，如前面的方法所述。其中 nn 表示 candiescandies 数组的大小。
    public int distributeCandies(int[] candies) {
        HashSet<Integer> set = new HashSet<>();
        for (int candy : candies) {
            set.add(candy);
        }
        return Math.min(set.size(), candies.length / 2);
    }

    //排序
    //我们可以对给定的 candiescandies 数组进行排序，并通过比较排序数组的相邻元素来找出唯一的元素。
    // 对于找到的每个新元素（与前一个元素不同），我们需要更新 countcount。最后，我们可以将所需结果返回为min(n/2,count)，如前面的方法所述。
    public int distributeCandies0(int[] candies) {
        Arrays.sort(candies);
        int count = 1;
        for (int i = 1; i < candies.length && count < candies.length / 2; i++)
            if (candies[i] > candies[i - 1])
                count++;
        return count;
    }


    public int distributeCandies1(int[] candies) {
        int count = 0;
        for (int i = 0; i < candies.length && count < candies.length / 2; i++) {
            if (candies[i] != Integer.MIN_VALUE) {
                count++;
                for (int j = i + 1; j < candies.length; j++) {
                    if (candies[j] == candies[i])
                        candies[j] = Integer.MIN_VALUE;
                }
            }
        }
        return count;
    }

    //暴力法
    //暴力法非常简单。我们可以生成代表糖果的给定 numsnums 数组的所有排列，并确定所生成数组前半部分中唯一元素的数目。
    //为了确定数组前半部分中唯一元素的数目，我们将所有需要的元素放在一个集合中，并计算集合中元素的数目。我们在生成的数组的前半部分中为所有可能的排列计算这样的唯一元素，并返回最大集合的大小。
    int max_kind = 0;
    public int distributeCandies2(int[] nums) {
        permute(nums, 0);
        return max_kind;
    }
    public void permute(int[] nums, int l) {
        if (l == nums.length - 1) {
            HashSet < Integer > set = new HashSet < > ();
            for (int i = 0; i < nums.length / 2; i++) {
                set.add(nums[i]);
            }
            max_kind = Math.max(max_kind, set.size());
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            permute(nums, l + 1);
            swap(nums, i, l);
        }
    }
    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

}
