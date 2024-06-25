package org.kukharev;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final WebCrawler webCrawler;
    private final long interval;

    public Scheduler(WebCrawler webCrawler, long interval) {
        this.webCrawler = webCrawler;
        this.interval = interval;
    }

    public void start() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            List<String> urls = getUrlsToCrawl();
            webCrawler.startCrawling(urls);
        }, 0, interval, TimeUnit.MILLISECONDS);
    }

    private List<String> getUrlsToCrawl() {
        return new ArrayList<>();
    }
}
