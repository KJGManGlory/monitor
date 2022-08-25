package com.lizza.prometheus.monitor;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MonitorTests {

    /**
     * 模拟
     */
    @Test
    public void test1() throws Exception {
        // 用户 id
        int[] ids = IntStream.range(10000, 10006).toArray();

        // 模拟用户访问博客次数
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            int id = ids[i % 6];
            executorService.submit(() -> {
                while (true) {
                    int count = random.nextInt(100);
                    Monitor.recordMany(Joiner.on("_").join("user_visit_blog", id), Integer.valueOf(count).doubleValue());
                    System.out.println("user: " + id + ", count: " + count);
                    try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
                }
            });
        }

        Thread.currentThread().join();
    }
}
