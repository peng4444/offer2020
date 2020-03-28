package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

import java.util.Arrays;

/**
 * @ClassName: GetMaxSortedDistance
 * @Author: pbj
 * @Date: 2020/3/18 11:22
 * @Description: TODO 无序数组排序后的最大相邻差 leetcode164
 */
public class GetMaxSortedDistance {

    //时间复杂度O(N)空间复杂度O(N)
    public int maximumGap(int[] nums) {
        int ans = 0;
        if(nums.length<2){
            return ans;
        }
        Arrays.sort(nums);
        for(int i = 1;i<nums.length;i++){
            int temp = Math.abs(nums[i]-nums[i-1]);
            ans = Math.max(ans,temp);
        }
        return ans;
    }
    //尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
    public static int maximumGap1(int[] array){
        if(array.length < 2) return 0;
        //1.得到数列的最大值和最小值
        int max = array[0];
        int min = array[0];
        for(int i=1; i<array.length; i++) {
            if(array[i] > max) {
                max = array[i];
            }
            if(array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //如果max和min相等，说明数组所有元素都相等，返回0
        if(d == 0){
            return 0;
        }

        //2.初始化桶
        int bucketNum = array.length+1;
        Bucket[] buckets = new Bucket[bucketNum];
        for(int i = 0; i < bucketNum; i++){
            buckets[i] = new Bucket();
        }
        int index = 0;
        int bucketSize = (int)Math.ceil(d*1.0/ (bucketNum-1));
        //3.遍历原始数组，确定每个桶的最大最小值
        for(int i = 0; i < array.length; i++){
            //确定数组元素所归属的桶下标
            index = ((array[i] - min)  / bucketSize);
            System.out.println(array[i]+min+":"+":"+index+":"+":"+bucketSize+":"+d);
            if(buckets[index].min==null || buckets[index].min>array[i]){
                buckets[index].min = array[i];
            }
            if(buckets[index].max==null || buckets[index].max<array[i]){
                buckets[index].max = array[i];
            }
        }

        //4.遍历桶，找到最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i=1; i<buckets.length; i++) {
            if (buckets[i].min == null) {
                continue;
            }
            if (buckets[i].min - leftMax > maxDistance) {
                maxDistance = buckets[i].min - leftMax;
            }
            leftMax = buckets[i].max;
        }

        return maxDistance;
    }

    /**
     * 桶
     */
    private static class Bucket {
        Integer min;
        Integer max;
    }

    public static void main(String[] args) {
        int[] array = new int[] {601408776,63967816,431363697,242509930,15970592,60284088,228037800,147629558,220782926,55455864,456541040,106650540,17290078,52153098,103139530,294196042,16568100,426864152,61916064,657788565,166159446,1741650,101791800,28206276,6223796,524849590,125389882,84399672,153834912,164568204,1866165,283209696,560993994,16266096,219635658,9188983,485969304,782013650,120332636,44659356,444517408,36369045,47370708,18542592,98802990,137690000,124889895,56062800,265421676,309417680,4634176,801661539,510541206,258227892,398938089,47255754,152260962,409663140,102847688,45756553,377936600,269498,375738702,263761134,53797945,329493948,224442208,508336845,189507850,40944620,127879560,119629476,186894520,62409156,693721503,4289916,523899936,28955240,266488028,20356650,40769391,483694272,97988044,84102,67246047,310688630,41288643,58965588,42881432,152159462,94786355,174917835,119224652,525034376,261516,274800528,62643819,23613832,8397240,797832131,855155367,337066320,26341480,61932200,20661075,515542796,390337500,522552030,43538516,150800550,116747540,152989123,488640056,700610304,233604,344277340,21439176,9397864,16365822,73027584,453041413,197374275,157735188,15273822,187081152,379611084,865005504,223099767,80478651,377729400,186738219,34738263,16634072,112791343,99631856,119364960,477106486,583953920,624509809,188442472,294181256,213023715,146645884,149530380,497592753,132170327,72770643,126683010,405141255,590214306,26670714,95582385,162080790,231120099,8946432,204967980,592849110,54120698,375915096,602145859,5346440,226337825,425156369,653591624,578483360,572410800,32290700,381384563,149939976,183225375,155695620,38307636,457513760,97085778,75200576,8068176,221650296,556889418,252495726,895020231,19932465,156334887,191383314,348432526,368701264,14315598,148936587,279419435,237325542,252587218,322929504,26331343,355297676,600420786,652017765,51673622,159015675};
        //int[] array = {1,1,1,1,5,5,5,5};
        System.out.println(maximumGap1(array));
    }

}
