package org.kukharev;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Indexer {
    private final Map<String, List<String>> index = new ConcurrentHashMap<>();

    public void indexPage(String url, String content) {
        String[] words = content.split("\\W+");
        for (String word : words) {
            index.computeIfAbsent(word.toLowerCase(), k -> new ArrayList<>()).add(url);
        }
    }

    public List<String> search(String query) {
        return index.getOrDefault(query.toLowerCase(), Collections.emptyList());
    }
}

