package com.lizza.prometheus.collector;

import com.google.common.collect.Lists;
import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomCollector extends Collector {

    @Override
    public List<MetricFamilySamples> collect() {
//        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();
//        Random random = new Random();
//        List<GaugeMetricFamily> list = IntStream.generate(() -> random.nextInt(100)).limit(10)
//                                                   .boxed()
//                                                   .map(e -> new GaugeMetricFamily("custom_gauge", "help", e))
//                                                   .collect(Collectors.toList());
//        mfs.addAll(list);
//        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();

        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();

        String metricName = "my_guage_1";

        // Your code to get metrics

        MetricFamilySamples.Sample sample = new MetricFamilySamples.Sample(metricName, Arrays.asList("l1"), Arrays.asList("v1"), 4);
        MetricFamilySamples.Sample sample2 = new MetricFamilySamples.Sample(metricName, Arrays.asList("l1", "l2"), Arrays.asList("v1", "v2"), 3);

        MetricFamilySamples samples = new MetricFamilySamples(metricName, Type.COUNTER, "help", Arrays.asList(sample, sample2));

        mfs.add(samples);
        return mfs;
    }
}
