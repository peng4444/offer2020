package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @pClassName: Demo717
 * @author: pengbingjiang
 * @create: 2020/7/15 17:19
 * @description: TODO 717. 1比特与2比特字符
 */
public class Demo717 {

    //
    public boolean isOneBitCharacter(int[] bits) {
        int start = 0;
        while(start<bits.length-1){
            if(bits[start]==0){
                start++;
            }else{
                start+=2;
            }
        }
        return start==bits.length-1;
    }

    //不需要遍历所有
    //分类讨论如下：
    //1.如果倒数第二位为0，则必然正确；
    //2.如果倒数第二位为1，则连续1的个数必须为偶数；
    //由于第1种情况（0个1）也算偶数，所以只要判断从倒数第二位开始是否有连续偶数个1即可。
    public boolean isOneBitCharacter1(int[] bits) {
        if(bits.length == 1) return true;
        int i = bits.length-2;
        int count = 0;
        while(i >= 0 && bits[i] == 1){
            i--;
            count++;
        }
        if(count % 2 == 0) return true;
        return false;
    }
}
