package cn.offer2020.pbj.book_reading.concurrent.chapter5;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @ClassName: Piple
 * @Author: pbj
 * @Date: 2020/4/12 16:42
 * @Description: TODO 管道
 * 执行流程
 * 1. 线程ReaderThread开始执⾏，
 * 2. 线程ReaderThread使⽤管道reader.read()进⼊”阻塞“，
 * 3. 线程WriterThread开始执⾏，
 * 4. 线程WriterThread⽤writer.write("test")往管道写⼊字符串，
 * 5. 线程WriterThread使⽤writer.close()结束管道写⼊，并执⾏完毕，
 * 6. 线程ReaderThread接受到管道输出的字符串并打印，
 * 7. 线程ReaderThread执⾏完毕。
 */
public class Piple {
    static class ReaderThread implements Runnable {
        private PipedReader reader;

        public ReaderThread(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {
        private PipedWriter writer;

        public WriterThread(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
            try {
                writer.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader); // 这⾥注意⼀定要连接，才能通信
        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();
    }
}
