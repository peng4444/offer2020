package cn.offer2020.pbj.demo.jianzhi_offer;

/**
 * @ClassName: ReplaceSpace
 * @Author: pbj
 * @Date: 2020/1/15 21:46
 * @Description: TODO 替换空格
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {

    public String replaceSpace(String string) {
        StringBuffer stringBuffer = new StringBuffer();
        int len = string.length() - 1;
        for (int i = len; i >= 0; i--) {
            if (string.charAt(i) == ' ') {
                stringBuffer.append("%20");
            } else {
                stringBuffer.append(string.charAt(i));
            }
        }
        return stringBuffer.toString();
    }
}
