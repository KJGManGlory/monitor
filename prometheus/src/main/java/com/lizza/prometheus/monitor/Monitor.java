package com.lizza.prometheus.monitor;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.PushGateway;

/**
 * 监控器
 */
public class Monitor {

    private static final PushGateway PUSH_GATEWAY = new PushGateway("localhost:9091");

    private static final Counter COUNTER = Counter.build()
                             .name("custom_metric")
                             .help("custom help")
                             .labelNames("user_id")
                             .register();
    /**
     * 记录单个值
     *
     * @param label 指标名称
     */
    public static void recordOne(String label) {
        try {
            Counter counter = Counter.build().register();
            counter.labels(label).inc();
            PUSH_GATEWAY.push(counter, "custom-monitor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 记录多个值
     *
     * @param label 指标名称
     */
    public static void recordMany(String label, double count) {
        try {
            COUNTER.labels(label).inc(count);
            PUSH_GATEWAY.push(COUNTER, "custom-monitor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
