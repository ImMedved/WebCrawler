package org.kukharev;

import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private final Indexer indexer;
    private final Storage storage;

    public Search(Indexer indexer, Storage storage) {
        this.indexer = indexer;
        this.storage = storage;
    }

    public List<SearchResult> search(String query) {
        List<String> urls = indexer.search(query);
        return urls.stream().map(url -> {
            String content = storage.getPage(url);
            String snippet = content.length() > 200 ? content.substring(0, 200) : content;
            return new SearchResult(url, snippet);
        }).collect(Collectors.toList());
    }
}

class SearchResult {
    private final String url;
    private final String snippet;

    public SearchResult(String url, String snippet) {
        this.url = url;
        this.snippet = snippet;
    }
}
