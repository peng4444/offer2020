package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: MergeSort
 * @Author: pbj
 * @Date: 2020/3/6 12:01
 * @Description: TODO 归并排序
 * 归并排序就是递归得将原始数组递归对半分隔，直到不能再分（只剩下一个元素）后，开始从最小的数组向上归并排序
 * 1.  向上归并排序的时候，需要一个暂存数组用来排序，
 * 2.  将待合并的两个数组，从第一位开始比较，小的放到暂存数组，指针向后移，
 * 3.  直到一个数组空，这时，不用判断哪个数组空了，直接将两个数组剩下的元素追加到暂存数组里，
 * 4.  再将暂存数组排序后的元素放到原数组里，两个数组合成一个，这一趟结束。
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int lo, int hi) {
        if(lo==hi){
            return;
        }
        int mid = ((hi-lo)>>1)+lo;
        mergeSort(arr, lo, mid);
        mergeSort(arr,mid+1,hi);
        merge(arr,lo,mid,hi);
    }

    //二路归并的实现
    public static void merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = arr.clone();
        int k = lo, i = lo, j = mid + 1;
        while (k <= hi) {
            if (i > mid) {
                arr[k++] = temp[j++];
            } else if (j > hi) {
                arr[k++] = temp[i++];
            } else if (temp[j] < temp[i]) {
                arr[k++] = temp[j++];
            } else {
                arr[k++] = temp[i++];
            }
        }
    }

    public void mergeSort1(int[] num, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = left + (right - left) /2;
        mergeSort1(num, left, mid);
        mergeSort1(num, mid + 1, right);
        merge1(num, left, mid, right);
    }

    public static void merge1(int[] num, int left, int mid, int right) {
        int[] temp = new int[right-left+1];//中间数组
        int i = left,j = mid + 1,k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = num[i] <= num[j] ? num[i++] : num[j++];
        }
        while (i<=mid) temp[k++] = num[i++];
        while (j<=mid) temp[k++] = num[j++];
        for (int p = 0; p < temp.length; p++) {
            num[left + p] = temp[p];
        }
    }
}
