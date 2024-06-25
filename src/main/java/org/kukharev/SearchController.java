package org.kukharev;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    private final Search search;

    public SearchController(Search search) {
        this.search = search;
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String query) {
        return search.search(query);
    }
}
