package cn.offer2020.pbj.book_reading.effective_java3.chapter2.item8;
/**
 * Java9垃圾回收机制
 *
 * 在java9中已经将finalize终结方法方法废弃
 *
 * 占有非堆资源的对象实例，类应该提供一个方法以明确释放这些资源，如果合适的话他们也应该实现AutoCloseable接口。
 *
 * java.lang.ref.Cleaner和java.lang.ref.PhantomReference提供更灵活和有效的方式，在对象无法再访问时释放资源。
 * ---------------------
 * 作者：烟火HL
 * 来源：CSDN
 * 原文：https://blog.csdn.net/sinat_22594643/article/details/80512525
 */

//import java.lang.ref.Cleaner;
//
//// An autocloseable class using a cleaner as a safety net (Page 32)
//public class Room implements AutoCloseable {
//    private static final Cleaner cleaner = Cleaner.create();
//
//    // Resource that requires cleaning. Must not refer to Room!
//    private static class State implements Runnable {
//        int numJunkPiles; // Number of junk piles in this room
//
//        State(int numJunkPiles) {
//            this.numJunkPiles = numJunkPiles;
//        }
//
//        // Invoked by close method or cleaner
//        @Override public void run() {
//            System.out.println("Cleaning room");
//            numJunkPiles = 0;
//        }
//    }
//
//    // The state of this room, shared with our cleanable
//    private final State state;
//
//    // Our cleanable. Cleans the room when it’s eligible for gc
//    private final Cleaner.Cleanable cleanable;
//
//    public Room(int numJunkPiles) {
//        state = new State(numJunkPiles);
//        cleanable = cleaner.register(this, state);
//    }
//
//    @Override public void close() {
//        cleanable.clean();
//    }
//}
