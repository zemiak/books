package com.zemiak.books.service;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Cache;
import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
@DependsOn("Storage")
public class Statistics {
    @Inject
    private Collection col;
    
    @Inject
    private Configuration conf;
    
    private int authors;
    private int books;
    private int authorsDocumented;
    private int authorsTagged;
    
    private static final String BOOKS = "statistics.books";
    private static final String AUTHORS = "statistics.authors";
    private static final String AUTHORS_DOCUMENTED = "statistics.authors_documented";
    private static final String AUTHORS_TAGGED = "statistics.authors_tagged";

    public int getAuthors() {
        return authors;
    }

    public int getBooks() {
        return books;
    }

    public int getAuthorsDocumented() {
        return authorsDocumented;
    }

    public int getAuthorsTagged() {
        return authorsTagged;
    }

    @PostConstruct
    void readData() {
        if (conf.isRefreshData()) {
            refreshStatistics();
        } else {
            readCachedStatistics();
        }
    }

    private void refreshStatistics() {
        books = col.getBooks().size();
        
        authors = 0;
        for (Author a: col.getAuthors()){
            authors++;
            
            if (a.getTagString() != null && ! a.getTagString().isEmpty()) {
                authorsTagged++;
            }
            
            if (a.getWebPages() != null && ! a.getWebPages().isEmpty()) {
                authorsDocumented++;
            }
        }
        
        col.persist(new Cache(BOOKS, String.valueOf(books)));
        col.persist(new Cache(AUTHORS, String.valueOf(authors)));
        col.persist(new Cache(AUTHORS_DOCUMENTED, String.valueOf(authorsDocumented)));
        col.persist(new Cache(AUTHORS_TAGGED, String.valueOf(authorsTagged)));
    }

    private void readCachedStatistics() {
        books = Integer.parseInt(col.getCache(BOOKS).getValue());
        authors = Integer.parseInt(col.getCache(AUTHORS).getValue());
        authorsDocumented = Integer.parseInt(col.getCache(AUTHORS_DOCUMENTED).getValue());
        authorsTagged = Integer.parseInt(col.getCache(AUTHORS_TAGGED).getValue());
    }
}
