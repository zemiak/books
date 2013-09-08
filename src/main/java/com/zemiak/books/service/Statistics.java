package com.zemiak.books.service;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class Statistics {
    @Inject
    private Collection col;

    @Inject
    private Configuration conf;
    
    private Map<String, String> cache = new HashMap<>();

    public static final String BOOKS = "statistics.books";
    public static final String AUTHORS = "statistics.authors";
    public static final String AUTHORS_DOCUMENTED = "statistics.authors_documented";
    public static final String AUTHORS_TAGGED = "statistics.authors_tagged";

    public String getAuthors() {
        return cache.get(AUTHORS);
    }

    public String getBooks() {
        return cache.get(BOOKS);
    }

    public String getAuthorsDocumented() {
        return cache.get(AUTHORS_DOCUMENTED);
    }

    public String getAuthorsTagged() {
        return cache.get(AUTHORS_TAGGED);
    }

    public void readStatistics() {
        int books = 0, authors = 0, authorsTagged = 0, authorsDocumented = 0;

        books = col.getBooks().size();

        for (Author a: col.getAuthors()){
            authors++;

            if (null != a.getTags() && !a.getTags().isEmpty()) {
                authorsTagged++;
            }

            if (a.getWebPages() != null && ! a.getWebPages().isEmpty()) {
                authorsDocumented++;
            }
        }
        
        cache.put(BOOKS, String.valueOf(books));
        cache.put(AUTHORS, String.valueOf(authors));
        cache.put(AUTHORS_TAGGED, String.valueOf(authorsTagged));
        cache.put(AUTHORS_DOCUMENTED, String.valueOf(authorsDocumented));
    }
}
