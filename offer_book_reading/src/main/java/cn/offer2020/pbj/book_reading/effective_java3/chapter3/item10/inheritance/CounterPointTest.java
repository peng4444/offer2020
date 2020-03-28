//package cn.offer2020.pbj.book_reading.effective_java3.chapter3.item10.inheritance;
//
//
//import cn.offer2020.pbj.book_reading.effective_java3.chapter3.item10.Point;
//
//import java.util.Set;
//
//// Test program that uses CounterPoint as Point
//public class CounterPointTest {
//    // Initialize unitCircle to contain all Points on the unit circle  (Page 43)
//    // java 8 以后的功能
//    private static final Set<Point> unitCircle = Set.of(  //jdk9
//            new Point( 1,  0), new Point( 0,  1),
//            new Point(-1,  0), new Point( 0, -1));
//
//    public static boolean onUnitCircle(Point p) {
//        return unitCircle.contains(p);
//    }
//
//    public static void main(String[] args) {
//        Point p1 = new Point(1,  0);
//        Point p2 = new CounterPoint(1,  0);
//
//        // Prints true
//        System.out.println(onUnitCircle(p1));
//
//        // Should print true, but doesn't if Point uses getClass-based equals
//        System.out.println(onUnitCircle(p2));
//    }
//}
