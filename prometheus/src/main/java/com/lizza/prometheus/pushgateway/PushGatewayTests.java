package com.lizza.prometheus.pushgateway;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

import java.util.Random;

public class PushGatewayTests {

    public static void main(String[] args) throws Exception {
        PushGateway pushGateway = new PushGateway("localhost:9091");

        Counter counter = Counter.build()
                                  .name("blog_visit")
                                  .labelNames("blog_id")
                                  .help("counter_blog_visit")
                                  .register();

        Gauge gauge = Gauge.build()
                              .name("blog_fans")
                              .help("gauge_blog_fans")
                              .register();

        //粉丝数先预设50
        gauge.inc(50);
        Random rnd = new Random();
        while (true) {
            //随机生成1个blogId
            int blogId = rnd.nextInt(100000);
            //该blogId的访问量+1
            counter.labels(blogId + "").inc();
            //模拟粉丝数的变化
            if (blogId % 2 == 0) {
                gauge.inc();
            } else {
                gauge.dec();
            }
            //利用网关采集数据
            pushGateway.push(counter, "job-counter-test");
            pushGateway.push(gauge, "job-gauge-test");

            //辅助输出日志
            System.out.println("blogId:" + blogId);
            Thread.sleep(5000);
        }
    }
}
