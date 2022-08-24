package com.lizza.prometheus.exporter;

import com.lizza.prometheus.collector.CustomCollector;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class Exporter {

    public static void main(String[] args) throws IOException {
        new CustomCollector().register();
        HTTPServer server = new HTTPServer("192.168.1.2", 8899);
    }
}
