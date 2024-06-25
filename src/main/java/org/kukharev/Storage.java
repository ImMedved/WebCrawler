package org.kukharev;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private final Map<String, String> pages = new ConcurrentHashMap<>();

    public void savePage(String url, String content) {
        pages.put(url, content);
    }

    public String getPage(String url) {
        return pages.get(url);
    }
}

