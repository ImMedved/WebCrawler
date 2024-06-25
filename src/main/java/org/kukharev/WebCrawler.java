package org.kukharev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebCrawler {
    private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();
    private final Queue<String> urlsToCrawl = new ConcurrentLinkedQueue<>();
    private final Storage storage;
    private final Indexer indexer;

    public WebCrawler(Storage storage, Indexer indexer) {
        this.storage = storage;
        this.indexer = indexer;
    }

    public void startCrawling(List<String> initialUrls) {
        urlsToCrawl.addAll(initialUrls);
        while (!urlsToCrawl.isEmpty()) {
            String url = urlsToCrawl.poll();
            if (url != null && !visitedUrls.contains(url)) {
                visitedUrls.add(url);
                crawl(url);
            }
        }
    }

    private void crawl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String content = doc.text();
            storage.savePage(url, content);
            indexer.indexPage(url, content);
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String nextUrl = link.attr("abs:href");
                if (!visitedUrls.contains(nextUrl)) {
                    urlsToCrawl.add(nextUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}