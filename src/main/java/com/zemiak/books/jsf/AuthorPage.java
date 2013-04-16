package com.zemiak.books.jsf;

import com.zemiak.books.boundary.Collection;
import com.zemiak.books.domain.Author;
import com.zemiak.books.domain.Book;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

@ManagedBean
public class AuthorPage {
    @ManagedProperty("#{param.id}")
    private int id;
    
    @Inject
    private Collection col;
    
    private Author author;
    
    @PostConstruct
    public void init() {
        author = col.getAuthor(id);
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Book> getBooks() {
        List<Book> books = author.getBooks();
        
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        return books;
    }
    
    public String getLetter() {
        return author.getName().substring(0, 1).toUpperCase();
    }
}
