package cn.offer2020.pbj.javabasis.java.basis1.array_3;
/* 数组排序
 * java.util.Arrays.sort(数组名称)
 * java.lang.System
 * 数组拷贝
 * public static void arraycopy(Object src,
                             int srcPos,
                             Object dest,
                             int destPos,
                             int length)
 */
public class ArrayOne {

	public static void main(String[] args) {
		int data[] = new int[] { 3, 4, 2, 8, 6, 5, 1 };
		print(data);
		arraySort(data);
		print(data);
		arrayReverse(data);
		print(data);

	}

	// print
	public static void print(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public static void arraySort(int data[]) {
		
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				if (data[i] > data[j]) {
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
	}
	//reverse
	public static void arrayReverse(int data[]) {
		int mid = data.length /2;
		int head = 0;
		int tail = data.length -1;
		for(int i = 0;i<mid;i++) {
			int temp = data[head];
			data[head] = data[tail];
			data[tail] = temp;
			head ++;
			tail --;
		}
	}
}
