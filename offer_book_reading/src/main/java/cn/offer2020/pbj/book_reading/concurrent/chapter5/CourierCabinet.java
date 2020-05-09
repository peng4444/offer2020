package cn.offer2020.pbj.book_reading.concurrent.chapter5;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CourierCabinet
 * @Author: pbj
 * @Date: 2020/5/9 09:18
 * @Description: TODO [全面理解线程间的通信方式](https://www.cnblogs.com/yzjbk/p/12853063.html)
 * 一个日常发生在我们身边的场景。现在网购越来越方便，我们与快递之间有一个快递小哥在关联，
 * 快递小哥将包裹放到快递柜，我们去快递柜领取快递：
 */
public class CourierCabinet {
    /**
     * 快递柜容量
     */
    public static final List<String> CABINET = new ArrayList<>(10);


    public static void main(String[] args) {


        new Thread(new Courier()).start();


        new Thread(new Recipient()).start();


    }
}
