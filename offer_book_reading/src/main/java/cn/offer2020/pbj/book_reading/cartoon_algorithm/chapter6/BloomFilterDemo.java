package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter6;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Hashing;

/**
 * @ClassName: BloomFilterDemo
 * @Author: pbj
 * @Date: 2020/6/8 15:45
 * @Description: TODO [布隆过滤器](https://www.cnblogs.com/CodeBear/p/10911177.html#top)
 */
public class BloomFilterDemo {
    //我们定义了一个布隆过滤器，有两个重要的参数，分别是我们预计要插入多少数据，我们所期望的误判率，误判率不能为0。
    //我向布隆过滤器插入了0-1000000，然后用1000000-2000000来测试误判率。
    private static int size = 1000000;//预计要插入多少数据

    private static double fpp = 0.01;//期望的误判率

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    @Test
    public void TestDemo() {
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }


    static final int expectedInsertions = 1000;//要插入多少数据
//    static final double fpp = 0.01;//期望的误判率

    //bit数组长度
    private static long numBits;

    //hash函数数量
    private static int numHashFunctions;

    static {
        numBits = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
    }

    @Test
    public  void TestDemo1() {
        Jedis jedis = new Jedis("localhost", 6379);
        for (int i = 0; i < 1000; i++) {
            long[] indexArray = getIndexArray(String.valueOf(i));
            for (long index : indexArray) {
                jedis.setbit("codebear:bloom", index, true);
            }
        }
        int num = 0;
        for (int i = 1000; i < 2000; i++) {
            long[] indexArray = getIndexArray(String.valueOf(i));
            for (long index : indexArray) {
                if (!jedis.getbit("codebear:bloom", index)) {
                    System.out.println(i + "一定不存在");
                    num++;
                    break;
                }
            }
        }
        System.out.println("一定不存在的有" + num + "个");
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexArray(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    private static long hash(String key) {
        return Hashing.MURMUR_HASH.hash(key);
    }

    //计算hash函数个数
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算bit数组长度
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }
}
